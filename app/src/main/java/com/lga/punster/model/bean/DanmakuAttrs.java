package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/9.
 */

public class DanmakuAttrs {

    @JSONField(name="allow_show_danmaku")
    public int allow_show_danmaku;

    @JSONField(name="allow_send_danmaku")
    public int allow_send_danmaku;
}
