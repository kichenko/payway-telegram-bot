/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

/**
 * SendContentType
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
public enum SendContentType {

    Document("document"),
    Photo("photo"),
    Audio("audio"),
    Voice("voice"),
    Location("location"),
    Message("message"),
    Video("video");

    private final String name;

    private SendContentType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
