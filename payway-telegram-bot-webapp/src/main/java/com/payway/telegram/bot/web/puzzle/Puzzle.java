/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.puzzle;

import java.util.List;

/**
 * Puzzle
 *
 * @author Sergey Kichenko
 * @created 15.12.2015
 */
public interface Puzzle {

    List<String> getOptions();

    String getDescription();

    String getAnswer();

    boolean check(final String answer);
}
