/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.service.requests;

import com.payway.telegram.bot.api.model.File;
import com.payway.telegram.bot.api.model.Message;
import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.model.User;
import com.payway.telegram.bot.api.model.UserProfilePhotos;
import com.payway.telegram.bot.api.model.requests.ForwardMessage;
import com.payway.telegram.bot.api.model.requests.GetFile;
import com.payway.telegram.bot.api.model.requests.GetUpdates;
import com.payway.telegram.bot.api.model.requests.GetUserProfilePhotos;
import com.payway.telegram.bot.api.model.requests.SendAudio;
import com.payway.telegram.bot.api.model.requests.SendChatAction;
import com.payway.telegram.bot.api.model.requests.SendDocument;
import com.payway.telegram.bot.api.model.requests.SendLocation;
import com.payway.telegram.bot.api.model.requests.SendMessage;
import com.payway.telegram.bot.api.model.requests.SendPhoto;
import com.payway.telegram.bot.api.model.requests.SendVideo;
import com.payway.telegram.bot.api.model.requests.SendVoice;
import com.payway.telegram.bot.api.service.requests.exception.BotApiServiceException;
import java.util.List;

/**
 * BotApiRequestService
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
public interface BotApiRequestService {

    User getMe() throws BotApiServiceException;

    Message sendMessage(final SendMessage sendMessage) throws BotApiServiceException;

    Message forwardMessage(final ForwardMessage forwardMessage) throws BotApiServiceException;

    Message sendPhoto(final SendPhoto sendPhoto) throws BotApiServiceException;

    Message sendAudio(final SendAudio sendAudio) throws BotApiServiceException;

    Message sendDocument(final SendDocument sendDocument) throws BotApiServiceException;

    Message sendVideo(final SendVideo sendVideo) throws BotApiServiceException;

    Message sendVoice(final SendVoice sendVoice) throws BotApiServiceException;

    Message sendLocation(final SendLocation sendLocation) throws BotApiServiceException;

    Boolean sendChatAction(final SendChatAction sendChatAction) throws BotApiServiceException;

    UserProfilePhotos getUserProfilePhotos(final GetUserProfilePhotos getUserProfilePhotos) throws BotApiServiceException;

    File getFile(final GetFile getFile) throws BotApiServiceException;

    List<Update> getUpdates(final GetUpdates getUpdates) throws BotApiServiceException;

    void setWebhook(final String url, final File file) throws BotApiServiceException;
}
