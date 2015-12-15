/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.service.requests.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payway.telegram.bot.api.model.AbstractApiObject;
import com.payway.telegram.bot.api.model.ApiResponse;
import com.payway.telegram.bot.api.model.File;
import com.payway.telegram.bot.api.model.Message;
import com.payway.telegram.bot.api.model.Update;
import com.payway.telegram.bot.api.model.User;
import com.payway.telegram.bot.api.model.UserProfilePhotos;
import com.payway.telegram.bot.api.model.requests.AbstractApiRequestObject;
import com.payway.telegram.bot.api.model.requests.AbstractSendContent;
import com.payway.telegram.bot.api.model.requests.ForwardMessage;
import com.payway.telegram.bot.api.model.requests.GetFile;
import com.payway.telegram.bot.api.model.requests.GetMe;
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
import com.payway.telegram.bot.api.model.requests.SetWebhook;
import com.payway.telegram.bot.api.service.requests.BotApiRequestService;
import com.payway.telegram.bot.api.service.requests.exception.BotApiServiceException;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * BotApiRequestServiceImpl
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Service
public class BotApiRequestServiceImpl implements BotApiRequestService {

    @Value("${app.bot.api.url.method.pattern}")
    private String apiUrlPattern;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String getMethodApiUrl(final Class type) {
        return String.format(getApiUrlPattern(), type.getSimpleName());
    }

    private <K extends AbstractApiObject, T extends List<K>> T parse(final ApiResponse response, final TypeReference<T> typeReference) throws BotApiServiceException {

        T data = null;

        if (response == null) {
            throw new BotApiServiceException("Empty response");
        }

        if (!response.getOk()) {
            throw new BotApiServiceException(String.format("Invalid response with code=%s, description=%s", response.getErrorCode(), response.getDescription()));
        }

        try {
            data = getObjectMapper().readValue(response.getResult(), typeReference);
        } catch (Exception ex) {
            throw new BotApiServiceException("Invalid parse result", ex);
        }

        return data;
    }

    private <T> T parse(final ApiResponse response, final Class<T> type) throws BotApiServiceException {

        T data = null;

        if (response == null) {
            throw new BotApiServiceException("Empty response");
        }

        if (!response.getOk()) {
            throw new BotApiServiceException(String.format("Invalid response with code=%s, description=%s", response.getErrorCode(), response.getDescription()));
        }

        try {
            data = getObjectMapper().readValue(response.getResult(), type);
        } catch (Exception ex) {
            throw new BotApiServiceException("Invalid parse result", ex);
        }

        return data;
    }

    private <T, R extends AbstractApiRequestObject> T sendRequest(final String url, final R request, final Class<T> type) throws BotApiServiceException {

        T dst = null;
        try {
            final ApiResponse rsp = getRestTemplate().postForObject(
                    url,
                    request,
                    ApiResponse.class
            );
            dst = parse(rsp, type);
        } catch (BotApiServiceException basex) {
            throw basex;
        } catch (Exception ex) {
            throw new BotApiServiceException("Internal server error", ex);
        }

        return dst;
    }

    private <K extends AbstractApiObject, T extends List<K>, R extends AbstractApiRequestObject> T sendRequest(final String url, final R request, final TypeReference<T> typeReference) throws BotApiServiceException {

        T dst = null;
        try {
            final ApiResponse rsp = getRestTemplate().postForObject(
                    url,
                    request,
                    ApiResponse.class
            );
            dst = parse(rsp, typeReference);
        } catch (BotApiServiceException basex) {
            throw basex;
        } catch (Exception ex) {
            throw new BotApiServiceException("Internal server error", ex);
        }

        return dst;
    }

    private Message sendContent(final AbstractSendContent content, final Resource resource) throws BotApiServiceException {

        final HttpHeaders headers = new HttpHeaders();
        final MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        parts.add(content.getKind().getName(), resource);
        parts.add("chat_id", Integer.toString(content.getChatId()));

        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);

        return parse(
                getRestTemplate().exchange(
                        getMethodApiUrl(content.getClass()),
                        HttpMethod.POST,
                        requestEntity,
                        ApiResponse.class
                ).getBody(),
                Message.class
        );
    }

    @Override
    public User getMe() throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(GetMe.class), null, User.class);
    }

    @Override
    public Message sendMessage(SendMessage sendMessage) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(SendMessage.class), sendMessage, Message.class);
    }

    @Override
    public Message forwardMessage(ForwardMessage forwardMessage) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(ForwardMessage.class), forwardMessage, Message.class);
    }

    @Override
    public Message sendLocation(SendLocation sendLocation) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(SendLocation.class), sendLocation, Message.class);
    }

    @Override
    public Boolean sendChatAction(SendChatAction sendChatAction) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(SendChatAction.class), sendChatAction, Boolean.class);
    }

    @Override
    public UserProfilePhotos getUserProfilePhotos(GetUserProfilePhotos getUserProfilePhotos) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(GetUserProfilePhotos.class), getUserProfilePhotos, UserProfilePhotos.class);
    }

    @Override
    public File getFile(GetFile getFile) throws BotApiServiceException {
        return sendRequest(getMethodApiUrl(GetFile.class), getFile, File.class);
    }

    @Override
    public List<Update> getUpdates(GetUpdates getUpdates) throws BotApiServiceException {
        return sendRequest(
                getMethodApiUrl(GetUpdates.class),
                getUpdates,
                new TypeReference<List<Update>>() {
                });
    }

    @Override
    public Boolean setWebhook(final SetWebhook setWebhook, final Resource certificate) throws BotApiServiceException {

        final HttpHeaders headers = new HttpHeaders();
        final MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        parts.add("url", setWebhook.getUrl());
        if (certificate != null) {
            parts.add("certificate", certificate);
        }

        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);

        return parse(
                getRestTemplate().exchange(
                        getMethodApiUrl(setWebhook.getClass()),
                        HttpMethod.POST,
                        requestEntity,
                        ApiResponse.class
                ).getBody(),
                Boolean.class
        );
    }

    @Override
    public Message sendPhoto(final SendPhoto sendPhoto, final Resource resource) throws BotApiServiceException {
        return sendContent(sendPhoto, resource);
    }

    @Override
    public Message sendAudio(SendAudio sendAudio, final Resource resource) throws BotApiServiceException {
        return sendContent(sendAudio, resource);
    }

    @Override
    public Message sendDocument(SendDocument sendDocument, final Resource resource) throws BotApiServiceException {
        return sendContent(sendDocument, resource);
    }

    @Override
    public Message sendVideo(SendVideo sendVideo, final Resource resource) throws BotApiServiceException {
        return sendContent(sendVideo, resource);
    }

    @Override
    public Message sendVoice(SendVoice sendVoice, final Resource resource) throws BotApiServiceException {
        return sendContent(sendVoice, resource);
    }
}
