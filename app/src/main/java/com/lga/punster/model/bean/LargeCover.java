package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/9.
 */

public class LargeCover {

    @JSONField(name="url_list")
    public List<UrlList> url_list;

    @JSONField(name="uri")
    public String uri;
}
