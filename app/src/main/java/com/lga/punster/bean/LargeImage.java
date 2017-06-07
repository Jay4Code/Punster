package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class LargeImage {

    @JSONField(name="width")
    private int width;

    @JSONField(name="r_height")
    private int r_height;

    @JSONField(name="r_width")
    private int r_width;

    @JSONField(name="url_list")
    private List<UrlList> url_list;

    @JSONField(name="uri")
    private String uri;

    @JSONField(name="height")
    private int height;
}
