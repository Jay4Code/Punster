package com.lga.punster.bean;

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

    @Override
    public String toString() {
        return "HotData{" +
                "reason_desc='" + reason_desc + '\'' +
                ", origin_reason='" + origin_reason + '\'' +
                ", user=" + user +
                ", reason=" + reason +
                '}';
    }
}
