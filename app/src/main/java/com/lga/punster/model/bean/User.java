package com.lga.punster.model.bean;

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
    public String large_avatar_url;

    @JSONField(name="is_follower")
    public boolean is_follower;

    @JSONField(name="id")
    public String id;

    @JSONField(name="point")
    public String point;

    @JSONField(name="description")
    public String description;

    @JSONField(name="pro_user_desc")
    public String pro_user_desc;

    @JSONField(name="create_time")
    public String create_time;

    @JSONField(name="gender")
    public String gender;

    @JSONField(name="screen_name")
    public String screen_name;
}
