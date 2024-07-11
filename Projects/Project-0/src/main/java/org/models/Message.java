package org.models;

import org.enums.MessageStatus;

public class Message {
    private int message_id;
    private User sender;

    private User receiver;

    private  String subject;

    private String content;

    private MessageStatus status;

    public Message(int message_id, User sender, User receiver, String subject, String content, MessageStatus status) {
        this.message_id = message_id;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.status = status;
    }

    public Message(){}

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}

