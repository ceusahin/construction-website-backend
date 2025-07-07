package com.example.contruction.controller;

import com.example.contruction.entity.Client;
import com.example.contruction.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
        get     -->     List<Client> getAllClients();
        create  -->     Client createClient(Client client);
        get     -->     Client getClientById(Long id);
        put     -->     Client updateClient(Long id, Client updatedClient);
        delete  -->     void deleteClient(Long id);
     */

    @GetMapping("/")
    public List<Client> getAlLClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/create")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/update/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        return clientService.updateClient(id, updatedClient);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }



}
