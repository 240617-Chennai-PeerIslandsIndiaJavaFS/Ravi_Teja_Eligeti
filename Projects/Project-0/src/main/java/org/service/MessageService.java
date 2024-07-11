package org.service;

import org.dao.MessageDao;
import org.dao.MessageDaoImplementation;
import org.models.Message;

import java.util.List;

public class MessageService {
    MessageDao mdao;

    public MessageService(MessageDaoImplementation mdao){
        this.mdao=mdao;
    }

    public MessageService(){
        mdao=new MessageDaoImplementation();

    }

    public int sendMessage(Message message){
        return  mdao.sendMessage(message);
    }

    public List<Message> getMyMessages(int id){
        return mdao.getMyMessages(id);
    }

    public int updateMessage(int id){
        return mdao.updateStatus(id);
    }
}
