package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class FilterWord {

    @JSONField(name="id")
    public String id;

    @JSONField(name="is_selected")
    public boolean is_selected;

    @JSONField(name="name")
    public String name;

    @Override
    public String toString() {
        return "FilterWord{" +
                "id='" + id + '\'' +
                ", is_selected=" + is_selected +
                ", name='" + name + '\'' +
                '}';
    }
}
