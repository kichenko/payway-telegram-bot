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
 * SendAudio
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendAudio extends AbstractSendContent {

    @JsonProperty("audio")
    private String audio;

    @JsonProperty("performer")
    private String performer;

    @JsonProperty("title")
    private String title;

    public SendAudio(Integer chatId, Integer replayToMessageId, String audio, String performer, String title, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Audio);
        this.audio = audio;
        this.performer = performer;
        this.title = title;

    }

}
