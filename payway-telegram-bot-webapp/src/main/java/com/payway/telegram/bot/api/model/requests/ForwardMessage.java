/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ForwardMessage
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForwardMessage extends AbstractApiRequestObject {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("from_chat_id")
    private Integer fromChatId;

    @JsonProperty("message_id")
    private Integer messageId;
}
