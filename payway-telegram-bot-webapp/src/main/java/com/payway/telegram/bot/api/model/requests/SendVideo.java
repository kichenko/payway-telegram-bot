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
 * SendVideo
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendVideo extends AbstractSendContent {

    @JsonProperty("video")
    private String video;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("caption")
    private String caption;

    public SendVideo(Integer chatId, Integer replayToMessageId, String caption, String video, Integer duration, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Video);
        this.video = video;
        this.duration = duration;
        this.caption = caption;
    }

}
