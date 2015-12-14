/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payway.telegram.bot.api.model.ReplyKeyboard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SendLocation
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
public class SendLocation extends AbstractSendContent {

    @JsonProperty("latitude")
    private Float latitude;

    @JsonProperty("longitude")
    private Float longitude;

    public SendLocation(Integer chatId, Integer replayToMessageId, Float latitude, Float longitude, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Location);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
