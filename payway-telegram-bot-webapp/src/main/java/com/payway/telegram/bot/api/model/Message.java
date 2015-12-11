/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message extends AbstractApiObject {

    @JsonProperty("message_id")
    private Integer messageId;

    @JsonProperty("from")
    private User from;

    @JsonProperty("date")
    private Integer date;

    @JsonProperty("chat")
    private Chat chat;

    @JsonProperty("forward_from")
    private User forwardFrom;

    @JsonProperty("forward_date")
    private Integer forwardDate;

    @JsonProperty("text")
    private String text;

    @JsonProperty("audio")
    private Audio audio;

    @JsonProperty("document")
    private Document document;

    @JsonProperty("photo")
    private List<PhotoSize> photo;

    @JsonProperty("sticker")
    private Sticker sticker;

    @JsonProperty("video")
    private Video video;

    @JsonProperty("contact")
    private Contact contact;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("new_chat_participant")
    private User newChatParticipant;

    @JsonProperty("left_chat_participant")
    private User leftChatParticipant;

    @JsonProperty("new_chat_title")
    private String newChatTitle;

    @JsonProperty("new_chat_photo")
    private List<PhotoSize> newChatPhoto;

    @JsonProperty("delete_chat_photo")
    private Boolean deleteChatPhoto;

    @JsonProperty("group_chat_created")
    private Boolean groupchatCreated;

    @JsonProperty("reply_to_message")
    private Message replyToMessage;

    @JsonProperty("voice")
    private Voice voice;
}
