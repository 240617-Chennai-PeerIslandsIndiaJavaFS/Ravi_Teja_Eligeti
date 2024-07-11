package org.dao;

import org.models.Message;

import java.util.List;

public interface MessageDao {
    public int sendMessage(Message message);

    public List<Message> getMyMessages(int id);

    public int updateStatus(int id);
}
