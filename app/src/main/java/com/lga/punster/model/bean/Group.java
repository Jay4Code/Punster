package com.lga.punster.model.bean;

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

    @JSONField(name="360p_video")
    public PVideo p360;

    @JSONField(name="mp4_url")
    public String mp4_url;

    @JSONField(name="720p_video")
    public PVideo p720;

    @JSONField(name="duration")
    public String duration;

    @JSONField(name="480p_video")
    public PVideo p480;

    @JSONField(name="keywords")
    public String keywords;

    @JSONField(name="danmaku_attrs")
    public DanmakuAttrs danmaku_attrs;

    @JSONField(name="m3u8_url")
    public String m3u8_url;

    @JSONField(name="large_cover")
    public LargeCover large_cover;

    @JSONField(name="title")
    public String title;

    @JSONField(name="video_height")
    public int video_height;

    @JSONField(name="cover_image_uri")
    public String cover_image_uri;

    @JSONField(name="publish_time")
    public String publish_time;

    @JSONField(name="play_count")
    public int play_count;

    @JSONField(name="medium_cover")
    public MediumCover medium_cover;

    @JSONField(name="video_width")
    public int video_width;

    @JSONField(name="flash_url")
    public String flash_url;

    @JSONField(name="video_id")
    public String video_id;

    @JSONField(name="uri")
    public String uri;

    @JSONField(name="is_public_url")
    public int is_public_url;

    @JSONField(name="origin_video")
    public PVideo origin_video;

    @JSONField(name="cover_image_url")
    public String cover_image_url;

    @JSONField(name="is_video")
    public int is_video;
}
