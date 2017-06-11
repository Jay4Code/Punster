package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class LargImage {

    @JSONField(name="width")
    public int width;

    @JSONField(name="r_height")
    public int r_height;

    @JSONField(name="r_width")
    public int r_width;

    @JSONField(name="url_list")
    public List<UrlList> url_list;

    @JSONField(name="uri")
    public String uri;

    @JSONField(name="height")
    public int height;
}
