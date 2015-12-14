/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.task.handler;

import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BotWebhookTaskHandler
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
public class BotWebhookTaskHandler implements Runnable {

    private Update update;

    @Autowired
    private BotApiRequestService service;

    @Override
    public void run() {
        //getUpdate().getMessage().
    }
}
