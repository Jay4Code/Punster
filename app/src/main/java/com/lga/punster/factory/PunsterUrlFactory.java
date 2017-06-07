package com.lga.punster.factory;

import com.lga.punster.constant.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Jay on 2017/5/21.
 */

public class PunsterUrlFactory {

    private static final long DAY_Millis = 1000 * 60 * 60 * 24;
    private static StringBuffer mBuffer = new StringBuffer();
    private static long mLoadTime;
    private static long mLastLoadTime = System.currentTimeMillis() - DAY_Millis;

    public static String getJokeUrl() {
        StringBuffer url = new StringBuffer(Constant.JOKE_URL);
        url.append("&").append(getContentType(-102));
        url.append("&").append("message_cursor=-1");
        url.append("&").append("am_longitude=110");
        url.append("&").append("am_latitude=120");
        url.append("&").append(getCityURLCode("北京市"));
        url.append("&").append(getCurrentTime());
        url.append("&").append("count=30");
        url.append("&").append(getLastLoadTime());
        url.append("&").append(getScreenWidth());
        url.append("&").append("do00le_col_mode=0");
        url.append("&").append(getIID());
        url.append("&").append(getDeviceId());
        url.append("&").append(getNetType());
        url.append("&").append(getChannel());
        url.append("&").append("aid=7");
        url.append("&").append("app_name=joke_essay");
        url.append("&").append(getVersionCode());
        url.append("&").append(getVersionName());
        url.append("&").append("device_platform=android");
        url.append("&").append("ssmix=a");
        url.append("&").append(getDeviceType());
        url.append("&").append(getDeviceBrand());
        url.append("&").append(getOSApi());
        url.append("&").append(getOSVersion());
        url.append("&").append(getUUID());
        url.append("&").append(getOpenUDID());
        url.append("&").append(getManifestVersionCode());
        url.append("&").append(getResolution());
        url.append("&").append(getDpi());
        url.append("&").append(getUpdateVersionCode());

        mLastLoadTime = mLoadTime;

        return url.toString();
    }

    /**
     * 从获取 content_type 中获取得到的 list_id 字段值。目前推荐的是-101，视频的是-104，段友秀的是-301，图片的是-103，段子的是-102
     * @param flag
     * @return
     */
    private static StringBuffer getContentType(int flag) {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("content_type=");
        return mBuffer.append(flag);
    }

    /**
     * 城市名，例如：北京市。可为空
     * @param city
     * @return
     */
    private static StringBuffer getCityURLCode(String city) {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("am_city=");
        try {
            mBuffer.append(URLEncoder.encode(city, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            mBuffer.append("");
            e.printStackTrace();
        }
        return mBuffer;
    }

    /**
     * 当前时间 Unix 时间戳，毫秒为单位
     * @return
     */
    public static StringBuffer getCurrentTime() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("am_loc_time=");
        mLoadTime = System.currentTimeMillis();
        mBuffer.append(mLoadTime);
        return mBuffer;
    }

    /**
     * 上次更新时间的 Unix 时间戳，秒为单位
     * @return
     */
    private static StringBuffer getLastLoadTime() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("min_time=");
        mBuffer.append(mLastLoadTime / 1000);
        return mBuffer;
    }

    /**
     * 屏幕宽度，px为单位
     * @return
     */
    private static StringBuffer getScreenWidth() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("screen_width=1920");
        return mBuffer;
    }

    /**
     * 一个长度为10的纯数字字符串，用于标识用户唯一性
     * @return
     */
    private static StringBuffer getIID() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("iid=3216590132");
        return mBuffer;
    }

    /**
     * 设备 id，一个长度为11的纯数字字符串
     * @return
     */
    private static StringBuffer getDeviceId() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("device_id=32613520945");
        return mBuffer;
    }

    /**
     * 网络环境，可取值 wifi
     * @return
     */
    private static StringBuffer getNetType() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("ac=wifi");
        return mBuffer;
    }

    /**
     * 下载渠道，可360、tencent等
     * @return
     */
    private static StringBuffer getChannel() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("channel=360");
        return mBuffer;
    }

    /**
     * 版本号去除小数点，例如625
     * @return
     */
    private static StringBuffer getVersionCode() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("version_code=625");
        return mBuffer;
    }

    /**
     * 版本号，例如6.2.5
     * @return
     */
    private static StringBuffer getVersionName() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("version_name=6.2.5");
        return mBuffer;
    }

    /**
     * 设备型号，例如 xiaomi
     * @return
     */
    private static StringBuffer getDeviceType() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("device_type=xiaomi");
        return mBuffer;
    }

    /**
     * 设备品牌，例如 xiaomi
     * @return
     */
    private static StringBuffer getDeviceBrand() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("device_brand=xiaomi");
        return mBuffer;
    }

    /**
     * 操作系统版本，例如28
     * @return
     */
    private static StringBuffer getOSApi() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("os_api=28");
        return mBuffer;
    }

    /**
     * 操作系统版本号，例如7.1.0
     * @return
     */
    private static StringBuffer getOSVersion() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("os_version=6.10.1");
        return mBuffer;
    }

    /**
     * 用户 id，一个长度为15的纯数字字符串
     * @return
     */
    private static StringBuffer getUUID() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("uuid=326135942187625");
        return mBuffer;
    }

    /**
     * 一个长度为16的数字和小写字母混合字符串
     * @return
     */
    private static StringBuffer getOpenUDID() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("openudid=3dg6s95rhg2a3dg5");
        return mBuffer;
    }

    /**
     * 版本号去除小数点，例如625
     * @return
     */
    private static StringBuffer getManifestVersionCode() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("manifest_version_code=625");
        return mBuffer;
    }

    /**
     * 屏幕宽高，例如 1920*1080
     * @return
     */
    private static StringBuffer getResolution() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("resolution=1920*1080");
        return mBuffer;
    }

    /**
     * 手机 dpi
     * @return
     */
    private static StringBuffer getDpi() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("dpi=320");
        return mBuffer;
    }

    /**
     * 版本号去除小数点后乘10，例如6120
     * @return
     */
    private static StringBuffer getUpdateVersionCode() {
        mBuffer.delete(0, mBuffer.length());
        mBuffer.append("update_version_code=6250");
        return mBuffer;
    }
}
