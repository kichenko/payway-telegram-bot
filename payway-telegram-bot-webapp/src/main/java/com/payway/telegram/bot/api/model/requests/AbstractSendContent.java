/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payway.telegram.bot.api.model.ReplyKeyboard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AbstractSendContent
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractSendContent extends AbstractApiRequestObject {

    @JsonProperty("chat_id")
    protected Integer chatId;

    @JsonProperty("reply_to_message_id")
    protected Integer replayToMessageId;

    @JsonProperty("reply_markup")
    protected ReplyKeyboard replayMarkup;

    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected SendContentType kind;
}
