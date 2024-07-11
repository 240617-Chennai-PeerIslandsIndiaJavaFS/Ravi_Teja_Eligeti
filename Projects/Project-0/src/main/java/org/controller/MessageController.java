package org.controller;

import org.models.Message;
import org.service.MessageService;

import java.util.List;

public class MessageController {

    public int sendMessage(Message message){
        return new MessageService().sendMessage(message);
    }

    public List<Message> myMessages(int id){
        return new MessageService().getMyMessages(id);
    }

    public int updateMessage(int id){
        return new MessageService().updateMessage(id);
    }
}
