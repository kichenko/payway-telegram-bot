/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.service.requests.impl;

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
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import com.payway.telegram.bot.api.service.requests.exception.BotApiServiceException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * BotApiRequestServiceImpl
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Service
public class BotApiRequestServiceImpl implements BotApiRequestService {

    @Override
    public User getMe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendMessage(SendMessage sendMessage) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message forwardMessage(ForwardMessage forwardMessage) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendPhoto(SendPhoto sendPhoto) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendAudio(SendAudio sendAudio) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendDocument(SendDocument sendDocument) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendVideo(SendVideo sendVideo) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendVoice(SendVoice sendVoice) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message sendLocation(SendLocation sendLocation) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendChatAction(SendChatAction sendChatAction) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserProfilePhotos getUserProfilePhotos(GetUserProfilePhotos getUserProfilePhotos) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File getFile(GetFile getFile) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Update> getUpdates(GetUpdates getUpdates) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWebhook(String url, File file) throws BotApiServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
