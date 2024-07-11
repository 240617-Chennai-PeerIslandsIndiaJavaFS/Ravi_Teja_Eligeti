package org.service;

import org.controller.Console;
import org.dao.ClientDao;
import org.dao.ClientDaoImplementation;
import org.models.Client;

import java.util.List;

public class ClientService {
//    ClientDaoImplementation cdao;
    ClientDao cdao;
    Console console;

    public ClientService(ClientDaoImplementation cdao){
        this.cdao=cdao;
    }
    public ClientService(){
        cdao=new ClientDaoImplementation();
        console=new Console();
    }

    public List<Client> fetchAllClients(){
        return cdao.fetchAllClients();
    }

    public boolean addClient(Client client){
        boolean b=cdao.addClient(client);
        if(b){
            System.out.println("🟢✅Client added successfully✅🟢");
            console.adminOptions();
        }
        else{
            System.out.println("Something went wrong🔴❌");
            console.adminOptions();
        }
        return b;
    }

    public int updateClient(Client client){
        return cdao.updateClient(client);
    }

}
