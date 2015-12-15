/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.controller;

import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.model.requests.SetWebhook;
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import com.payway.telegram.bot.web.task.handler.BotWebhookTaskHandler;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * TelegramBotController
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class TelegramBotController extends AbstractController {

    @Getter
    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    private TaskExecutor executor;

    @Getter
    @Value("${app.bot.api.token}")
    private String botApiToken;

    @Getter
    @Autowired
    private BotApiRequestService service;

    @RequestMapping(value = "ping", method = RequestMethod.GET, produces = {"text/plain"})
    public void ping(final HttpServletResponse response) throws Exception {
        response.getWriter().print("pong [" + new Date() + "]");
    }

    @RequestMapping(value = "setwebhook", method = RequestMethod.POST)
    public void setWebhook(@RequestParam("url") final String url, @RequestParam("file") MultipartFile file) throws Exception {
        getService().setWebhook(new SetWebhook(url), new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping(value = "setwebhook", method = {RequestMethod.POST, RequestMethod.GET})
    public void setWebhook(@RequestParam("url") final String url) throws Exception {
        getService().setWebhook(new SetWebhook(url), null);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "{token}/webhook", method = RequestMethod.POST, consumes = "application/json")
    public void onWebhook(@PathVariable("token") final String token, @RequestBody final Update update) throws Exception {

        if (!Objects.equals(getBotApiToken(), token)) {
            log.error("Src token [{}] and url path token [{}] are not equals", getBotApiToken(), token);
            return;
        }

        final BotWebhookTaskHandler handler = getApplicationContext().getBean(BotWebhookTaskHandler.class);
        handler.setUpdate(update);
        executor.execute(handler);
    }
}
