package com.lga.util;

/**
 * Created by Jay on 2017/5/23.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用DiskLruCache来缓存图片
 */
public class CacheUtil {

    private Context mContext;

    /**
     * 记录所有正在下载或等待下载的任务。
     */
    private Set<BitmapWorkerTask> mTaskCollection;

    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
     */
    private LruCache<String, Bitmap> mMemoryCache;


    private LruCache<String, JSONObject> mMemoryJsonObjectCache;

    /**
     * 图片硬盘缓存核心类。
     */
    private DiskLruCache mDiskLruCache;

    /**
     * 线程池下载图片
     */
    private ExecutorService mExecutors;
    private CacheHandler mCacheHandler;


    public CacheUtil(Context context, String fileName) {
        mContext = context;

        mTaskCollection = new HashSet<BitmapWorkerTask>();

        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();

        int cacheSize = maxMemory / 8;
        // 设置图片缓存大小为程序最大可用内存的1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

        mMemoryJsonObjectCache = new LruCache<String, JSONObject>(cacheSize) {

            @Override
            protected int sizeOf(String key, JSONObject jsonObj) {
                return jsonObj.length();
            }
        };

        try {
            // 获取图片缓存路径
            File cacheDir = getDiskCacheDir(context, fileName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            // 创建DiskLruCache实例，初始化缓存数据
            mDiskLruCache = DiskLruCache
                    .open(cacheDir, getAppVersion(context), 1, 20 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mExecutors = Executors.newFixedThreadPool(1);
        mCacheHandler = new CacheHandler(this);
    }

    /**
     * 根据传入的uniqueName获取硬盘缓存的路径地址。
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取当前应用程序的版本号。
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象，
     * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片。
     */
    public void loadBitmap(ImageView imageView, String imageUrl) {
        try {
            Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
            if (bitmap == null) {
//                BitmapWorkerTask task = new BitmapWorkerTask();
//                taskCollection.add(task);
//                task.execute(imageUrl);
                startExecutor(imageUrl);
            } else {
                if (imageView != null && bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将缓存记录同步到journal文件中。
     */
    public void flushCache() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    public void cancelAllTasks() {
        if (mTaskCollection != null) {
            for (BitmapWorkerTask task : mTaskCollection) {
                task.cancel(false);
            }
        }
    }

    public void startExecutor(final String imageUrl) {

        mExecutors.execute(new Runnable() {
            @Override
            public void run() {
                FileDescriptor fileDescriptor = null;
                FileInputStream fileInputStream = null;
                DiskLruCache.Snapshot snapShot = null;
                try {
                    // 生成图片URL对应的key
                    final String key = hashKeyForDisk(imageUrl);
                    // 查找key对应的缓存
                    snapShot = mDiskLruCache.get(key);
                    if (snapShot == null) {
                        // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                        if (editor != null) {
                            OutputStream outputStream = editor.newOutputStream(0);
                            if (downloadUrlToStream(imageUrl, outputStream)) {
                                editor.commit();
                            } else {
                                editor.abort();
                            }
                        }
                        // 缓存被写入后，再次查找key对应的缓存
                        snapShot = mDiskLruCache.get(key);
                    }
                    if (snapShot != null) {
                        fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                        fileDescriptor = fileInputStream.getFD();
                    }
                    // 将缓存数据解析成Bitmap对象
                    Bitmap bitmap = null;
                    if (fileDescriptor != null) {
                        bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    }
                    if (bitmap != null) {
                        // 将Bitmap对象添加到内存缓存当中
                        addBitmapToMemoryCache(imageUrl, bitmap);
                    }
                    mCacheHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            /*ImageView imageView = (ImageView) mViewGroup.findViewWithTag(imageUrl);
                            Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
                            if (imageView != null && bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            }*/
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileDescriptor == null && fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                }

            }
        });
    }

    /**
     * 建立HTTP请求，并获取Bitmap对象。
     *
     * @param urlString 图片的URL地址
     * @return 解析后的Bitmap对象
     */
    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        Log.e("kellyy", "download");

        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        InputStream inputStream = null;
        try {
            //速度相对较快
//            final URL url = new URL(urlString);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.getInputStream();

            //速度最快 new URL(urlString).openStream()


            //速度最慢
//            inputStream = downBitmaps(urlString);
//            if (inputStream == null) {
//                return false;
//            }

            in = new BufferedInputStream(new URL(urlString).openStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key    LruCache的键，这里传入图片的URL地址。
     * @param bitmap LruCache的键，这里传入从网络上下载的Bitmap对象。
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public void addJsonObjectToMemoryCache(String key, JSONObject jsonObj) {
        if (getJsonObjectFromMemoryCache(key) == null) {
            mMemoryJsonObjectCache.put(key, jsonObj);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的Bitmap对象，或者null。
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 使用MD5算法对传入的key进行加密并返回。
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public void clearHandler() {
        if (mCacheHandler != null) {
            mCacheHandler.removeCallbacksAndMessages(null);
        }
    }

    public void clearExecutor() {
        if (mExecutors != null) {
            mExecutors.shutdown();
        }
    }

    public void loadJson(int method, String url) {
        try {
            JSONObject jsonObject = getJsonObjectFromMemoryCache(url);
            if (jsonObject == null) {
//                BitmapWorkerTask task = new BitmapWorkerTask();
//                taskCollection.add(task);
//                task.execute(imageUrl);
                startExecutor(method, url);
            } else {
                mResponseListener.onResponse(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObjectFromMemoryCache(String key) {
        return mMemoryJsonObjectCache.get(key);
    }

    private void startExecutor(int method, final String url) {

        mExecutors.execute(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                FileInputStream fileInputStream = null;
                DiskLruCache.Snapshot snapShot = null;
                try {
                    // 生成json URL对应的key
                    final String key = hashKeyForDisk(url);
                    // 查找key对应的缓存
                    snapShot = mDiskLruCache.get(key);
                    if (snapShot == null) {
                        // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                        if (editor != null) {
                            OutputStream outputStream = editor.newOutputStream(0);
                            if (downloadUrlToStream(url, outputStream)) {
                                editor.commit();
                            } else {
                                editor.abort();
                            }
                        }
                        // 缓存被写入后，再次查找key对应的缓存
                        snapShot = mDiskLruCache.get(key);
                    }
                    if (snapShot != null) {
                        reader = new BufferedReader(new InputStreamReader(snapShot.getInputStream(0)));
                        StringBuffer sb = new StringBuffer();

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        // 将缓存数据解析成JSONObject对象
                        JSONObject jsonObj = new JSONObject(sb.toString());
                        if (jsonObj != null) {
                            // 将jsonObj对象添加到内存缓存当中
                            addJsonObjectToMemoryCache(url, jsonObj);
                            Log.e("kellyy", jsonObj.toString());

                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = jsonObj;
                            mCacheHandler.sendMessage(msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }

            }
        });
    }

    private Listener<JSONObject> mResponseListener;
    public void setonResponseListener(Listener<JSONObject> listener) {
        mResponseListener = listener;
    }

    private ErrorListener mErrorListener;
    public void setOnErrorListener(ErrorListener listener) {
        mErrorListener = listener;
    }

    /**
     * 异步下载图片的任务。
     *
     * @author guolin
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        /**
         * 图片的URL地址
         */
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            FileDescriptor fileDescriptor = null;
            FileInputStream fileInputStream = null;
            DiskLruCache.Snapshot snapShot = null;
            try {
                // 生成图片URL对应的key
                final String key = hashKeyForDisk(imageUrl);
                // 查找key对应的缓存
                snapShot = mDiskLruCache.get(key);
                if (snapShot == null) {
                    // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    // 缓存被写入后，再次查找key对应的缓存
                    snapShot = mDiskLruCache.get(key);
                }
                if (snapShot != null) {
                    fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                    fileDescriptor = fileInputStream.getFD();
                }
                // 将缓存数据解析成Bitmap对象
                Bitmap bitmap = null;
                if (fileDescriptor != null) {
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                }
                if (bitmap != null) {
                    // 将Bitmap对象添加到内存缓存当中
                    addBitmapToMemoryCache(params[0], bitmap);
                }
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileDescriptor == null && fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            /*ImageView imageView = (ImageView) mViewGroup.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }*/
            mTaskCollection.remove(this);
        }

        /**
         * @param urlPath
         */
    }

    private static class CacheHandler<T> extends Handler {
        private WeakReference<T> weak;

        public CacheHandler(T t) {
            weak = new WeakReference<T>(t);
        }

        @Override
        public void handleMessage(Message msg) {
            CacheUtil t = (CacheUtil) weak.get();
            if (t == null) {
                return;
            }

            switch (msg.what) {
                case 1:
                    t.mResponseListener.onResponse((JSONObject) msg.obj);
                    break;
            }
        }
    }

    public interface ErrorListener {
        void onErrorResponse(String error);
    }

    public interface Listener<T> {
        void onResponse(T var1);
    }
}