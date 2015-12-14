/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payway.telegram.bot.api.model.ReplyKeyboard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SendPhoto
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendPhoto extends AbstractSendContent {

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("caption")
    private String caption;

    public SendPhoto(Integer chatId, Integer replayToMessageId, String caption, String photo, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Photo);
        this.photo = photo;
        this.caption = caption;
    }

}
