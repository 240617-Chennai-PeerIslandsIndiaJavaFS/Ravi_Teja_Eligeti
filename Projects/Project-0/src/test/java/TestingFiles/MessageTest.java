package TestingFiles;

import org.dao.MessageDaoImplementation;
import org.enums.MessageStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.models.Message;
import org.models.User;
import org.service.MessageService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageTest {
    public static MessageDaoImplementation mdao;

    public static MessageService messageService;

    public static List<Message> messages=new ArrayList<>();

    @BeforeAll
    public static void setMock(){

        for (int i = 0; i < 10; i++) {
            String subject = "Sample message subject " + (i + 1);
            String content = "Sample message content for message " + (i + 1);
            User sender = null; // Replace with actual User object if needed
            User receiver = null; // Replace with actual User object if needed
            MessageStatus status = MessageStatus.UNSEEN; // Assuming a default status

            Message message = new Message(0, sender, receiver, subject, content, status);
            messages.add(message);
        }
        mdao=mock();

        messageService=new MessageService(mdao);
    }

    @Test
    public void checkSendingMessage(){
        when(messageService.sendMessage(messages.get(1))).thenReturn(1);
        Assertions.assertEquals(1,messageService.sendMessage(messages.get(1)));
    }
    @Test
    public void checkFetch(){
        when(messageService.getMyMessages(1)).thenReturn(messages);
        Assertions.assertIterableEquals(messages,messageService.getMyMessages(1));
    }

    @Test
    public void testUpdateMessage(){
        when(messageService.updateMessage(1)).thenReturn(1);
        when(messageService.updateMessage(2)).thenReturn(2);

        Assertions.assertEquals(1,messageService.updateMessage(1));
        Assertions.assertEquals(2,messageService.updateMessage(2));
    }

}
