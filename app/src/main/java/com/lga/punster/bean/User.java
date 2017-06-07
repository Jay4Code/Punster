package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class User {

    @JSONField(name="is_following")
    public boolean is_following;

    @JSONField(name="followings")
    public String followings;

    @JSONField(name="followers")
    public String followers;

    @JSONField(name="avatar_url")
    public String avatar_url;

    @JSONField(name="ugc_count")
    public String ugc_count;

    @JSONField(name="name")
    public String name;

    @JSONField(name="user_verified")
    public boolean user_verified;

    @JSONField(name="user_id")
    public String user_id;

    @JSONField(name="is_pro_user")
    public boolean is_pro_user;

    @JSONField(name="large_avatar_url")
    private String large_avatar_url;

    @JSONField(name="is_follower")
    private boolean is_follower;

    @JSONField(name="id")
    private String id;

    @JSONField(name="point")
    private String point;

    @JSONField(name="description")
    private String description;

    @JSONField(name="pro_user_desc")
    private String pro_user_desc;

    @JSONField(name="create_time")
    private String create_time;

    @JSONField(name="gender")
    private String gender;

    @JSONField(name="screen_name")
    private String screen_name;

    @Override
    public String toString() {
        return "User{" +
                "is_following=" + is_following +
                ", followings=" + followings +
                ", followers=" + followers +
                ", avatar_url='" + avatar_url + '\'' +
                ", ugc_count=" + ugc_count +
                ", name='" + name + '\'' +
                ", user_verified=" + user_verified +
                ", user_id='" + user_id + '\'' +
                ", is_pro_user=" + is_pro_user +
                ", large_avatar_url='" + large_avatar_url + '\'' +
                ", is_follower=" + is_follower +
                ", id=" + id +
                ", point=" + point +
                ", description='" + description + '\'' +
                ", pro_user_desc='" + pro_user_desc + '\'' +
                ", create_time=" + create_time +
                ", gender=" + gender +
                ", screen_name='" + screen_name + '\'' +
                '}';
    }
}
