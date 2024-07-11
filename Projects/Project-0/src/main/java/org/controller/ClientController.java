package org.controller;

import org.models.Client;
import org.service.ClientService;

import java.util.List;

public class ClientController {
    ClientService clientService;

    public List<Client> getAllClients(){
        clientService=new ClientService();
        return clientService.fetchAllClients();
    }
    public void addClient(Client client){
        clientService=new ClientService();
        clientService.addClient(client);
    }

    public int updateClient(Client client){
        return new ClientService().updateClient(client);
    }
}
