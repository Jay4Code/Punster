package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Jay on 2017/6/7.
 */

public class Ad {

    @JSONField(name="avatar_url")
    public String avatar_url;

    @JSONField(name="appleid")
    public String appleid;

    @JSONField(name="package")
    public String _package;

    @JSONField(name="ab_show_style")
    public String ab_show_style;

    @JSONField(name="type")
    public String type;

    @JSONField(name="open_url")
    public String open_url;

    @JSONField(name="id")
    public String id;

    @JSONField(name="title")
    public String title;

    @JSONField(name="is_ad")
    public String is_ad;

    @JSONField(name="download_url")
    public String download_url;

    @JSONField(name="display_image_height")
    public String display_image_height;

    @JSONField(name="filter_words")
    public List<FilterWord> filter_word;

    @JSONField(name="track_url")
    public String track_url;

    @JSONField(name="ipa_url")
    public String ipa_url;

    @JSONField(name="button_text")
    public String button_text;

    @JSONField(name="display_image_width")
    public String display_image_width;

    @JSONField(name="log_extra")
    public LogExtra log_extra;

    @JSONField(name="click_delay")
    public String click_delay;

    @JSONField(name="web_url")
    public String web_url;

    @JSONField(name="label")
    public String label;

    @JSONField(name="avatar_name")
    public String avatar_name;

    @JSONField(name="source")
    public String source;

    @JSONField(name="ad_id")
    public String ad_id;

    @JSONField(name="gif_url")
    public String gif_url;

    @JSONField(name="end_time")
    public String end_time;

    @JSONField(name="track_url_list")
    public List<String> track_url_list;

    @JSONField(name="display_info")
    public String display_info;

    @JSONField(name="display_type")
    public String display_type;

    @JSONField(name="hide_if_exists")
    public String hide_if_exists;

    @JSONField(name="display_image")
    public String display_image;

    @Override
    public String toString() {
        return "Ad{" +
                "avatar_url='" + avatar_url + '\'' +
                ", appleid='" + appleid + '\'' +
                ", _package='" + _package + '\'' +
                ", ab_show_style=" + ab_show_style +
                ", type='" + type + '\'' +
                ", open_url='" + open_url + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", is_ad=" + is_ad +
                ", download_url='" + download_url + '\'' +
                ", display_image_height=" + display_image_height +
                ", filter_word=" + filter_word +
                ", track_url='" + track_url + '\'' +
                ", ipa_url='" + ipa_url + '\'' +
                ", button_text='" + button_text + '\'' +
                ", display_image_width=" + display_image_width +
                ", log_extra=" + log_extra +
                ", click_delay=" + click_delay +
                ", web_url='" + web_url + '\'' +
                ", label='" + label + '\'' +
                ", avatar_name='" + avatar_name + '\'' +
                ", source='" + source + '\'' +
                ", ad_id=" + ad_id +
                ", gif_url='" + gif_url + '\'' +
                ", end_time=" + end_time +
                ", track_url_list=" + track_url_list +
                ", display_info='" + display_info + '\'' +
                ", display_type=" + display_type +
                ", hide_if_exists=" + hide_if_exists +
                ", display_image='" + display_image + '\'' +
                '}';
    }
}
