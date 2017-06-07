package com.lga.punster.bean;

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

    @Override
    public String toString() {
        return "Comments{" +
                "platform='" + platform + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", text='" + text + '\'' +
                ", digg_count=" + digg_count +
                ", status=" + status +
                ", share_type=" + share_type +
                ", user_digg=" + user_digg +
                ", group_id=" + group_id +
                ", user_verified=" + user_verified +
                ", is_digg=" + is_digg +
                ", bury_count=" + bury_count +
                ", user_profile_url='" + user_profile_url + '\'' +
                ", id=" + id +
                ", user_name='" + user_name + '\'' +
                ", platform_id='" + platform_id + '\'' +
                ", user_bury=" + user_bury +
                ", user_profile_image_url='" + user_profile_image_url + '\'' +
                ", description='" + description + '\'' +
                ", share_url='" + share_url + '\'' +
                ", create_time=" + create_time +
                ", user_id=" + user_id +
                ", is_pro_user=" + is_pro_user +
                '}';
    }
}
