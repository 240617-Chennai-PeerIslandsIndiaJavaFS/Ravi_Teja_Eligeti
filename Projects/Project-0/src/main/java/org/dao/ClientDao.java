package org.dao;

import org.models.Client;

import java.util.List;

public interface ClientDao {
    public boolean addClient(Client client);

    public Client getClientById(int id);

    public List<Client> fetchAllClients();

    public int updateClient(Client client);


}
