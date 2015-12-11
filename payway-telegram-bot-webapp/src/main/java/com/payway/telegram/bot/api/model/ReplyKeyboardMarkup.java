/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ReplyKeyboardHide
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyKeyboardMarkup extends AbstractApiObject implements ReplyKeyboard {

    @JsonProperty("keyboard")
    private List<List<String>> keyboard;

    @JsonProperty("resize_keyboard")
    private Boolean resizeKeyboard;

    @JsonProperty("one_time_keyboard")
    private Boolean oneTimeKeyboad;

    @JsonProperty("selective")
    private Boolean selective;
}
