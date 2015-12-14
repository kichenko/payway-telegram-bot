/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.controller;

import com.payway.telegram.bot.api.model.File;
import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.model.requests.GetUpdates;
import com.payway.telegram.bot.api.model.requests.SendPhoto;
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import com.payway.telegram.bot.web.task.handler.BotWebhookTaskHandler;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private BotApiRequestService service;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "ping", method = RequestMethod.GET, produces = {"text/plain"})
    public void ping(final HttpServletResponse response) throws Exception {

        try {
            
            service.sendPhoto(new SendPhoto(154969762, "154969762", "Hello", null, null));

            final List<Update> update = service.getUpdates(new GetUpdates(0, 10, null));

            /*
             restTemplate.postForObject(
             "http://localhost:8080/payway-telegram-bot/151500598:AAHIaCw9EcsPuA0wEacxTX9SgLBuKj831Tc/webhook",
             update.get(0),
             String.class
             );*/
            //final User user = service.getMe();
            //service.sendChatAction(new SendChatAction(154969762, ChatActionType.UploadVideo));
            //service.getUpdates(new GetUpdates(0, 10, null));
            /*
             final List list0 = new ArrayList<>();
             final List<String> list1 = new ImmutableList.Builder<String>()
             .add("1 - Payway")
             .add("2 - Latigue")
             .build();

             list0.add(list1);

             service.sendMessage(
             new SendMessage(154969762,
             "Hello from bot!",
             null,
             null,
             null,
             new ReplyKeyboardMarkup(
             list0,
             Boolean.TRUE,
             Boolean.TRUE,
             Boolean.TRUE)
             )
             );
             */
        } catch (Exception ex) {
            int k = 900;
        }

        response.getWriter().print("pong [" + new Date() + "]");
        //throw new Exception("fdf");
    }

    @RequestMapping(value = "setwebhook", method = RequestMethod.POST, produces = {"multipart/form-data"})
    public void setWebhook(@RequestParam("url") final String url, final File file) {
        //
    }

    @RequestMapping(value = "setwebhook", method = {RequestMethod.POST, RequestMethod.GET})
    public void setWebhook(@RequestParam("url") final String url) {
        //
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "{token}/webhook", method = RequestMethod.POST, consumes = "application/json")
    public void onWebhook(@PathVariable("token") final String token, final Update update) {

        if (!Objects.equals(getBotApiToken(), token)) {
            log.error("Src token [{}] and url path token [{}] are not equals", getBotApiToken(), token);
            return;
        }

        final BotWebhookTaskHandler handler = getApplicationContext().getBean(BotWebhookTaskHandler.class);
        handler.setUpdate(update);
        executor.execute(handler);
    }
}
