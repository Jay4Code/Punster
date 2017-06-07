package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class Group {

    @JSONField(name="large_image_list")
    public List<LargeImageList> large_image_list;

    @JSONField(name="is_multi_image")
    public int is_multi_image;

    @JSONField(name="thumb_image_list")
    public List<ThumbImageList> thumb_image_list;

    @JSONField(name="media_type")
    public String media_type;

    @JSONField(name="neihan_hot_end_time")
    public String neihan_hot_end_time;

    @JSONField(name="share_count")
    public String share_count;

    @JSONField(name="share_type")
    public String share_type;

    @JSONField(name="dislike_reason")
    public List<DislikeReason> dislike_reason;

    @JSONField(name="type")
    public String type;

    @JSONField(name="online_time")
    public String online_time;

    @JSONField(name="id")
    public String id;

    @JSONField(name="user_repin")
    public String user_repin;

    @JSONField(name="go_detail_count")
    public String go_detail_count;

    @JSONField(name="share_url")
    public String share_url;

    @JSONField(name="status_desc")
    public String status_desc;

    @JSONField(name="category_visible")
    public boolean category_visible;

    @JSONField(name="is_anonymous")
    public boolean is_anonymous;

    @JSONField(name="favorite_count")
    public String favorite_count;

    @JSONField(name="neihan_hot_start_time")
    public String neihan_hot_start_time;

    @JSONField(name="has_hot_comments")
    public String has_hot_comments;

    @JSONField(name="activity")
    public PActivity activity;

    @JSONField(name="comment_count")
    public String comment_count;

    @JSONField(name="is_can_share")
    public String is_can_share;

    @JSONField(name="text")
    public String text;

    @JSONField(name="digg_count")
    public String digg_count;

    @JSONField(name="is_neihan_hot")
    public boolean is_neihan_hot;

    @JSONField(name="status")
    public String status;

    @JSONField(name="user_digg")
    public String user_digg;

    @JSONField(name="group_id")
    public String group_id;

    @JSONField(name="category_id")
    public String category_id;

    @JSONField(name="label")
    public String label;

    @JSONField(name="quick_comment")
    public boolean quick_comment;

    @JSONField(name="bury_count")
    public String bury_count;

    @JSONField(name="allow_dislike")
    public boolean allow_dislike;

    @JSONField(name="content")
    public String content;

    @JSONField(name="user_bury")
    public String user_bury;

    @JSONField(name="category_name")
    public String category_name;

    @JSONField(name="has_comments")
    public String has_comments;

    @JSONField(name="neihan_hot_link")
    public NeiHanHotLink neihan_hot_link;

    @JSONField(name="create_time")
    public String create_time;

    @JSONField(name="id_str")
    public String id_str;

    @JSONField(name="category_type")
    public String category_type;

    @JSONField(name="repin_count")
    public String repin_count;

    @JSONField(name="display_type")
    public String display_type;

    @JSONField(name="user")
    public User user;

    @JSONField(name="user_favorite")
    public String user_favorite;

    @JSONField(name="max_screen_width_percent")
    public String max_screen_width_percent;

    @JSONField(name="large_image")
    public LargImage large_image;

    @JSONField(name="min_screen_width_percent")
    public double min_screen_width_percent;

    @JSONField(name="image_status")
    public int image_status;

    @JSONField(name="middle_image")
    public MiddlImage middle_image;

    @JSONField(name="is_gif")
    public int is_gif;

    @JSONField(name="gifvideo")
    public Gifvideo gifvideo;

    @Override
    public String toString() {
        return "Group{" +
                "media_type='" + media_type + '\'' +
                ", neihan_hot_end_time='" + neihan_hot_end_time + '\'' +
                ", share_count='" + share_count + '\'' +
                ", share_type='" + share_type + '\'' +
                ", dislike_reason=" + dislike_reason +
                ", type='" + type + '\'' +
                ", online_time='" + online_time + '\'' +
                ", id='" + id + '\'' +
                ", user_repin='" + user_repin + '\'' +
                ", go_detail_count='" + go_detail_count + '\'' +
                ", share_url='" + share_url + '\'' +
                ", status_desc='" + status_desc + '\'' +
                ", category_visible=" + category_visible +
                ", is_anonymous=" + is_anonymous +
                ", favorite_count='" + favorite_count + '\'' +
                ", neihan_hot_start_time='" + neihan_hot_start_time + '\'' +
                ", has_hot_comments='" + has_hot_comments + '\'' +
                ", activity=" + activity +
                ", comment_count='" + comment_count + '\'' +
                ", is_can_share='" + is_can_share + '\'' +
                ", text='" + text + '\'' +
                ", digg_count='" + digg_count + '\'' +
                ", is_neihan_hot=" + is_neihan_hot +
                ", status='" + status + '\'' +
                ", user_digg='" + user_digg + '\'' +
                ", group_id='" + group_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", label='" + label + '\'' +
                ", quick_comment=" + quick_comment +
                ", bury_count='" + bury_count + '\'' +
                ", allow_dislike=" + allow_dislike +
                ", content='" + content + '\'' +
                ", user_bury='" + user_bury + '\'' +
                ", category_name='" + category_name + '\'' +
                ", has_comments='" + has_comments + '\'' +
                ", neihan_hot_link=" + neihan_hot_link +
                ", create_time='" + create_time + '\'' +
                ", id_str='" + id_str + '\'' +
                ", category_type='" + category_type + '\'' +
                ", repin_count='" + repin_count + '\'' +
                ", display_type='" + display_type + '\'' +
                ", user=" + user +
                ", user_favorite='" + user_favorite + '\'' +
                ", max_screen_width_percent=" + max_screen_width_percent +
                ", large_image=" + large_image +
                ", min_screen_width_percent=" + min_screen_width_percent +
                ", image_status=" + image_status +
                ", middle_image=" + middle_image +
                '}';
    }
}
