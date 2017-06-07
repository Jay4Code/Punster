package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class JokeInfo {
    @JSONField(name="data")
    public List<Data> data;

    @JSONField(name="max_time")
    public String max_time;

    @JSONField(name="has_new_message")
    public boolean has_new_message;

    @JSONField(name="has_more")
    public boolean has_more;

    @JSONField(name="min_time")
    public String min_time;

    @JSONField(name="tip")
    public String tip;


    @Override
    public String toString() {
        return "JokeInfo{" +
                "data=" + data +
                ", max_time='" + max_time + '\'' +
                ", has_new_message=" + has_new_message +
                ", has_more=" + has_more +
                ", min_time='" + min_time + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
