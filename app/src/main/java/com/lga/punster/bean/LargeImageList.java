package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class LargeImageList {

    @JSONField(name="url")
    public String url;

    @JSONField(name="url_list")
    public List<UrlList> url_list;

    @JSONField(name="uri")
    public String uri;

    @JSONField(name="height")
    public int height;

    @JSONField(name="width")
    public int width;

    @JSONField(name="is_gif")
    public boolean is_gif;
}
