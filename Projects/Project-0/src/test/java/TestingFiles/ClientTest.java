package TestingFiles;

import org.dao.ClientDao;
import org.dao.ClientDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.models.Client;

public class ClientTest {
    public static ClientDao clientDao;
    public static Client client;

    @BeforeAll
    public static void setData(){
        clientDao=new ClientDaoImplementation();
        client=new Client(8,"testcompany","testname","test@gmail.com","+91 9999988888","Chennai","India");
    }

    @Test
    public void addClienttest(){
        Assertions.assertEquals(true,clientDao.addClient(client));
    }
}
