package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class DislikeReason {

    @JSONField(name="id")
    public String id;

    @JSONField(name="type")
    public String type;

    @JSONField(name="title")
    public String title;

    @Override
    public String toString() {
        return "DislikeReason{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
