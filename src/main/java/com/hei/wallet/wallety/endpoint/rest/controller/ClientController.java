package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.model.Client;
import com.hei.wallet.wallety.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    public List<Client> getAll(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public Client getById(@PathVariable String clientId){
        return clientService.findById(clientId);
    }

    @PutMapping("/clients")
    public List<Client> saveOrUpdateAll(@RequestBody List<Client> clients){
        return clientService.saveOrUpdateAll(clients);
    }
}