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
 * SendMessage
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessage extends AbstractSendContent {

    @JsonProperty("text")
    private String text;

    @JsonProperty("parse_mode")
    private ParseMode parseMode;

    @JsonProperty("disable_web_page_preview")
    private Boolean disableWebPagePreview;

    public SendMessage(Integer chatId, Integer replayToMessageId, String text, ParseMode parseMode, Boolean disableWebPagePreview, ReplyKeyboard replayMarkup) {
        super(chatId, replayToMessageId, replayMarkup, SendContentType.Message);
        this.text = text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }
    
    

}
