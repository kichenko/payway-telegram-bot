/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ChatType
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
public enum ChatType {

    Private("private"),
    Group("group"),
    SuperGroup("supergroup"),
    Channel("channel");

    private final String name;

    private ChatType(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
