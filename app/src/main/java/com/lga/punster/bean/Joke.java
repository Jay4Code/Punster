package com.lga.punster.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jay on 2017/6/7.
 */

public class Joke {
    @JSONField(name="data")
    public JokeInfo jokeInfo;

    @JSONField(name="message")
    public String message;

    @Override
    public String toString() {
        return "Joke{" +
                "jokeInfo=" + jokeInfo +
                ", message='" + message + '\'' +
                '}';
    }
}
