/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.puzzle;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;

/**
 * ChainPuzzle
 *
 * @author Sergey Kichenko
 * @created 15.12.2015
 */
public final class ChainPuzzle implements Puzzle {

    @Override
    public List<String> getOptions() {
        return new ImmutableList.Builder<String>()
                .add("Цепь")
                .add("Собака")
                .add("Кошка")
                .build();
    }

    @Override
    public String getDescription() {
        return "Не лает, не кусает, а к будке привязана";
    }

    @Override
    public String getAnswer() {
        return "Цепь";
    }

    @Override
    public boolean check(String answer) {
        return Objects.equals(getAnswer().toLowerCase(), answer.toLowerCase());
    }
}
