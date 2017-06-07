package com.lga.punster;


import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Jay on 2017/6/7.
 */

public class FastJsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;

    private Class<T> mClass;

    public FastJsonRequest(String url, Class<T> clazz, Listener<T> listener,
                           Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public FastJsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClass = clazz;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            // 输出json文本到文件
//            GlobeUtil.log2File(jsonString.toString(), new File(Environment.getExternalStorageDirectory().getPath(), "log_punster.txt"));
            return Response.success(JSON.parseObject(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
