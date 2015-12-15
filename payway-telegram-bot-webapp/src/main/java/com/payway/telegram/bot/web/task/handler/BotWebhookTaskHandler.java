/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.task.handler;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.payway.telegram.bot.api.model.ReplyKeyboard;
import com.payway.telegram.bot.api.model.ReplyKeyboardHide;
import com.payway.telegram.bot.api.model.ReplyKeyboardMarkup;
import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.model.requests.ChatActionType;
import com.payway.telegram.bot.api.model.requests.ParseMode;
import com.payway.telegram.bot.api.model.requests.SendChatAction;
import com.payway.telegram.bot.api.model.requests.SendDocument;
import com.payway.telegram.bot.api.model.requests.SendMessage;
import com.payway.telegram.bot.api.model.requests.SendPhoto;
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import com.payway.telegram.bot.web.core.UserBotSession;
import com.payway.telegram.bot.web.puzzle.ChainPuzzle;
import com.payway.telegram.bot.web.puzzle.Puzzle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * BotWebhookTaskHandler
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class BotWebhookTaskHandler implements Runnable {

    @Getter
    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    private BotApiRequestService service;

    @Resource(name = "app.Sessions")
    private Map<Integer, UserBotSession> sessions;

    private Update update;

    private ReplyKeyboard getActionsCustomKeyboard() {

        final List<List<String>> keyboard = new ArrayList<>();
        keyboard.add(new ImmutableList.Builder<String>()
                .add(UserBotSession.Command.Start.getName())
                .add(UserBotSession.Command.Quit.getName())
                .add(UserBotSession.Command.Puzzle.getName())
                .add(UserBotSession.Command.Photo.getName())
                .add(UserBotSession.Command.Document.getName())
                 .add(UserBotSession.Command.Actions.getName())
                .build());

        return new ReplyKeyboardMarkup(keyboard, Boolean.FALSE, null, null);
    }

    private ReplyKeyboard getPuzzleCustomKeyboard(final Puzzle puzzle) {
        final List<List<String>> keyboard = new ArrayList<>();
        keyboard.add(puzzle.getOptions());
        return new ReplyKeyboardMarkup(keyboard, Boolean.TRUE, null, null);
    }

    @Override
    public void run() {

        if (getUpdate() == null
                || getUpdate().getMessage() == null
                || getUpdate().getMessage().getFrom() == null
                || getUpdate().getMessage().getFrom().getId() == null
                || getUpdate().getMessage().getText() == null) {
            log.error("Invalid update object params");
            return;
        }

        try {

            final Integer msgId = getUpdate().getMessage().getMessageId();
            final Integer userId = getUpdate().getMessage().getFrom().getId();
            final String firstName = getUpdate().getMessage().getFrom().getFirstName();

            //session
            UserBotSession state = getSessions().get(userId);
            if (state == null) {
                state = getSessions().put(userId,
                        new UserBotSession(
                                userId,
                                UserBotSession.Command.Start
                        )
                );
            }

            if (UserBotSession.Command.Start.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {

                final String msg = "Hi *%s*, I will be glad to communicate with you!\n Please choose action from list (Send */actions* to see all or use custom keyboard).";
                getService().sendChatAction(new SendChatAction(userId, ChatActionType.Typing));
                getService().sendMessage(
                        new SendMessage(
                                userId,
                                null,
                                String.format(msg, firstName),
                                ParseMode.Markdown,
                                null,
                                getActionsCustomKeyboard()
                        )
                );

                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Start));
            } else if (UserBotSession.Command.Quit.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {

                final String msg = "Bye, was glad to communicate with you!";
                getSessions().remove(userId);
                getService().sendMessage(
                        new SendMessage(
                                userId,
                                null,
                                msg,
                                null,
                                null,
                                getActionsCustomKeyboard()
                        )
                );
                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Start));
            } else if (UserBotSession.Command.Actions.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {

                final String msg = "Supported action list are: %s";
                final List<String> actions = ((ReplyKeyboardMarkup) getActionsCustomKeyboard()).getKeyboard().get(0);

                getSessions().remove(userId);
                getService().sendMessage(
                        new SendMessage(
                                userId,
                                null,
                                String.format(msg, StringUtils.join(actions, ",")),
                                null,
                                null,
                                getActionsCustomKeyboard()
                        )
                );
                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Actions));
            } else if (UserBotSession.Command.Photo.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {

                getService().sendChatAction(new SendChatAction(userId, ChatActionType.UploadPhoto));
                getService().sendPhoto(
                        new SendPhoto(userId, null, "This is a cat", null, new ReplyKeyboardHide(Boolean.TRUE, null)),
                        getApplicationContext().getResource("classpath:com/payway/images/photo.jpg")
                );
                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Photo));
            } else if (UserBotSession.Command.Document.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {

                getService().sendChatAction(new SendChatAction(userId, ChatActionType.UploadDocument));
                getService().sendDocument(
                        new SendDocument(userId, null, null, new ReplyKeyboardHide(Boolean.TRUE, null)),
                        getApplicationContext().getResource("classpath:com/payway/documents/document.pdf")
                );
                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Document));
            } else if (UserBotSession.Command.Puzzle.getName().toLowerCase().equals(getUpdate().getMessage().getText().toLowerCase())) {
                final Puzzle puzzle = new ChainPuzzle();
                getService().sendChatAction(new SendChatAction(userId, ChatActionType.Typing));
                getService().sendMessage(
                        new SendMessage(
                                userId,
                                null,
                                puzzle.getDescription(),
                                null,
                                null,
                                getPuzzleCustomKeyboard(puzzle))
                );
                getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Puzzle));
            } else {
                if (UserBotSession.Command.Puzzle == state.getCommand()) {
                    final Puzzle puzzle = new ChainPuzzle();
                    if (Objects.equal(getUpdate().getMessage().getText().toLowerCase(), puzzle.getAnswer().toLowerCase())) {
                        getService().sendMessage(new SendMessage(userId, null, "*Congratulations you guessed!*", ParseMode.Markdown, null, getActionsCustomKeyboard()));
                        getSessions().put(userId, new UserBotSession(userId, UserBotSession.Command.Empty));
                    } else {
                        getService().sendMessage(new SendMessage(userId, null, "Wrong answer, try again!", null, null, null));
                    }
                } else {
                    final String msg = "Sorry *%s*, I don't understand you...\n Please choose action from list (Send */actions* to see all or use custom keyboard).";
                    getService().sendChatAction(new SendChatAction(userId, ChatActionType.Typing));
                    getService().sendMessage(new SendMessage(userId, null, String.format(msg, firstName), ParseMode.Markdown, null, getActionsCustomKeyboard()));
                }
            }
        } catch (Exception ex) {
            log.error("Internal server Error", ex);
        }
    }
}
