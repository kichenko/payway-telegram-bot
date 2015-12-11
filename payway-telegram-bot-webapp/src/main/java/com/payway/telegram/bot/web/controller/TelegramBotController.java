/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.controller;

import com.payway.telegram.bot.api.model.File;
import com.payway.telegram.bot.api.model.Update;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TelegramBotController
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class TelegramBotController {

    @RequestMapping(value = "ping", method = RequestMethod.GET, produces = {"text/plain"})
    public void ping(final HttpServletResponse response) throws IOException {
        response.getWriter().print("pong [" + new Date() + "]");
    }

    @RequestMapping(value = "setwebhook", method = RequestMethod.POST, produces = {"multipart/form-data"})
    public void setWebhook(@RequestParam("url") final String url, final File file) {
        //
    }

    //TODO: get hook
    @RequestMapping(value = "{token}/webhook", method = RequestMethod.POST)
    public void onWebhook(@PathVariable("token") final String token, final Update update) {
        //
    }
}
