package com.lga.punster.model.bean;

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
}
