package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class PVideo {

    @JSONField(name="width")
    private int width;

    @JSONField(name="url_list")
    private List<UrlList> url_list;

    @JSONField(name="uri")
    private String uri;

    @JSONField(name="height")
    private int height;
}
