package com.lga.punster.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class Comments {

    @JSONField(name="platform")
    public String platform;

    @JSONField(name="avatar_url")
    public String avatar_url;

    @JSONField(name="text")
    public String text;

    @JSONField(name="digg_count")
    public String digg_count;

    @JSONField(name="status")
    public String status;

    @JSONField(name="share_type")
    public String share_type;

    @JSONField(name="user_digg")
    public String user_digg;

    @JSONField(name="group_id")
    public String group_id;

    @JSONField(name="user_verified")
    public boolean user_verified;

    @JSONField(name="is_digg")
    public String is_digg;

    @JSONField(name="bury_count")
    public String bury_count;

    @JSONField(name="user_profile_url")
    public String user_profile_url;

    @JSONField(name="id")
    public String id;

    @JSONField(name="user_name")
    public String user_name;

    @JSONField(name="platform_id")
    public String platform_id;

    @JSONField(name="user_bury")
    public String user_bury;

    @JSONField(name="user_profile_image_url")
    public String user_profile_image_url;

    @JSONField(name="description")
    public String description;

    @JSONField(name="share_url")
    public String share_url;

    @JSONField(name="create_time")
    public String create_time;

    @JSONField(name="user_id")
    public String user_id;

    @JSONField(name="is_pro_user")
    public boolean is_pro_user;

    @JSONField(name="comment_id")
    public String comment_id;
}
