/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserBotSession
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBotSession {

    public enum Command {

        Empty(""),
        Start("/start"),
        Quit("/quit"),
        Photo("/photo"),
        Document("/document"),
        Puzzle("/puzzle"),
        Actions("/actions");

        private final String name;

        private Command(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private Integer userId;
    private Command command;

}
