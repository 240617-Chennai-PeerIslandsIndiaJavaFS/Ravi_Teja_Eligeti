package TestingFiles;

import org.dao.ClientDao;
import org.dao.ClientDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.models.Client;
import org.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    public static ClientDao clientDao;

    public static ClientDaoImplementation mockcdao;

    public static ClientService clientService;
    public static Client client;

    public static ArrayList<Client> clients;

    @BeforeAll
    public static void setData(){
        // Add sample Client objects to the list
        Client c1=new Client(1, "Company A", "John Doe", "john.doe@companya.com", "123-456-7890", "New York", "123 Main St");
        Client c2=new Client(2, "Company B", "Jane Smith", "jane.smith@companyb.com", "234-567-8901", "Los Angeles", "456 Elm St");
        Client c3=new Client(3, "Company C", "Jim Brown", "jim.brown@companyc.com", "345-678-9012", "Chicago", "789 Oak St");

        mockcdao=mock();
        clientService=new ClientService(mockcdao);
        clientDao=new ClientDaoImplementation();
        client=new Client(8,"testcompany","testname","test@gmail.com","+91 9999988888","Chennai","India");
    }

    @Test
    public void addClienttest(){
        Assertions.assertEquals(true,clientDao.addClient(client));
    }

    @Test
    public void fetchingClients(){
        when(clientService.fetchAllClients()).thenReturn(clients);
        Assertions.assertIterableEquals(clients,clientService.fetchAllClients());
    }

    @Test
    public void updateClient(){
        when(clientService.updateClient(client)).thenReturn(1);

        Assertions.assertEquals(1,clientService.updateClient(client));
    }
}
