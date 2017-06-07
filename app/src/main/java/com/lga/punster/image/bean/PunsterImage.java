package com.lga.punster.image.bean;

/**
 * Created by Jay on 2017/5/22.
 */

public class PunsterImage {

    private boolean isGif;
    private String url;
    private int width;
    private int height;

    public PunsterImage() {}

    public boolean isGif() {
        return isGif;
    }

    public void setGif(boolean gif) {
        isGif = gif;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
