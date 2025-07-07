package com.example.contruction.service;


import com.example.contruction.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client createClient(Client client);
    Client getClientById(Long id);
    Client updateClient(Long id, Client updatedClient);
    void deleteClient(Long id);
}
