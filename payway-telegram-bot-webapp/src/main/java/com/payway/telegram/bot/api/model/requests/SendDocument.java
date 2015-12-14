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
 * SendDocument
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
public class SendDocument extends AbstractSendContent {

    @JsonProperty("document")
    private String document;

    public SendDocument(Integer chatId, Integer replayToMessageId, String document, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Document);
        this.document = document;
    }
}
