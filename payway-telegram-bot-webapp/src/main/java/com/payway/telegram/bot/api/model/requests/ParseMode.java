/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ParseMode
 *
 * @author Sergey Kichenko
 * @created 15.12.2015
 */
public enum ParseMode {

    Markdown("Markdown");

    private final String name;

    private ParseMode(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
