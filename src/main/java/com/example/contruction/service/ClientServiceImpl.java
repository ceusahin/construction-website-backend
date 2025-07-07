package com.example.contruction.service;

import com.example.contruction.entity.Client;
import com.example.contruction.exception.NotFoundException;
import com.example.contruction.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        client.setRegisterDate(LocalDate.now());
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Müşteri bulunamadı: " + id));
    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Müşteri bulunamadı: " + id));

        existingClient.setName(updatedClient.getName());
        existingClient.setPhoneNumber(updatedClient.getPhoneNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setRegisterDate(updatedClient.getRegisterDate());
        existingClient.setCreatedAt(updatedClient.getCreatedAt());
        existingClient.setUpdatedAt(updatedClient.getUpdatedAt());

        existingClient.setUpdatedAt(LocalDateTime.now());

        return clientRepository.save(existingClient);    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Silinecek müşteri bulunamadı: " + id);
        }
        clientRepository.deleteById(id);
    }
}
