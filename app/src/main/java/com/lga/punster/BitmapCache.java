package com.lga.punster;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Jay on 2017/5/21.
 */

public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
//        Log.e("kellyy", "BitmapCache");
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
//                Log.e("kellyy", "sizeOf");
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
//        Log.e("kellyy", "getBitmap");
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
//        Log.e("kellyy", "putBitmap");
        mCache.put(url, bitmap);
    }

}