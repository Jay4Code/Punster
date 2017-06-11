package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class Gifvideo {

    @JSONField(name="360p_video")
    public PVideo p360;

    @JSONField(name="origin_video")
    public PVideo origin_video;

    @JSONField(name="video_id")
    public String video_id;

    @JSONField(name="720p_video")
    public PVideo p720;

    @JSONField(name="mp4_url")
    public String mp4_url;

    @JSONField(name="video_height")
    public int video_height;

    @JSONField(name="480p_video")
    public PVideo p_480;

    @JSONField(name="cover_image_uri")
    public String cover_image_uri;

    @JSONField(name="duration")
    public double duration;

    @JSONField(name="video_width")
    public int video_width;
}
