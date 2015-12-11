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
public class SendVideo extends AbstractApiRequestObject {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("video")
    private String video;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("reply_to_message_id")
    private Integer replayToMessageId;

    @JsonProperty("reply_markup")
    private ReplyKeyboard replayMarkup;
}
