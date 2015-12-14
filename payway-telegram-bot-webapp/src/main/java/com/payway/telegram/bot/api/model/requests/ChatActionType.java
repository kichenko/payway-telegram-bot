/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ChatActionType
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
public enum ChatActionType {

    Typing("typing"),
    UploadPhoto("upload_photo"),
    UploadVideo("upload_video"),
    UploadAudio("upload_audio "),
    UploadDocument("upload_document"),
    FindLocation("find_location");

    private final String name;

    private ChatActionType(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

}
