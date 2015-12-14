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
 * SendVoice
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendVoice extends AbstractSendContent {

    @JsonProperty("voice")
    private String voice;

    @JsonProperty("duration")
    private Integer duration;

    public SendVoice(Integer chatId, Integer replayToMessageId, String voice, Integer duration, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Voice);
        this.voice = voice;
        this.duration = duration;
    }
}
