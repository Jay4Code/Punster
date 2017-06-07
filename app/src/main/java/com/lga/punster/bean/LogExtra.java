package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class LogExtra {

    @JSONField(name="convert_id")
    public String convert_id;

    @JSONField(name="rit")
    public String rit;

    @JSONField(name="req_id")
    public String req_id;

    @JSONField(name="ad_price")
    public String ad_price;

    @Override
    public String toString() {
        return "LogExtra{" +
                "convert_id=" + convert_id +
                ", rit=" + rit +
                ", req_id='" + req_id + '\'' +
                ", ad_price='" + ad_price + '\'' +
                '}';
    }
}
