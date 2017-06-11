package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class HotData {

    @JSONField(name="reason_desc")
    public String reason_desc;

    @JSONField(name="origin_reason")
    public String origin_reason;

    @JSONField(name="user")
    public User user;

    @JSONField(name="reason")
    public String reason;
}
