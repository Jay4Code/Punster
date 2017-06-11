package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class Data {

    @JSONField(name="online_time")
    public String online_time;

    @JSONField(name="type")
    public String type;

    @JSONField(name="group")
    public Group group;

    @JSONField(name="display_time")
    public String display_time;

    @JSONField(name="comments")
    public List<Comments> comments;

    @JSONField(name="ad")
    public Ad ad;

    @JSONField(name="data")
    public List<HotData> hotData;

    @JSONField(name="show_type")
    public String show_type;
}
