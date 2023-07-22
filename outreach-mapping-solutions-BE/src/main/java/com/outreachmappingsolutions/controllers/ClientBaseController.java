package com.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.services.ClientBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientBase")
public class ClientBaseController {

    @Autowired
    private ClientBaseService clientBaseService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewClient(@RequestBody ClientBase clientBase){
        return clientBaseService.createNewClient(clientBase);
    }

    @GetMapping("/returnAll")
    public ResponseEntity<?> returnAllClients(){
        return clientBaseService.returnAllClients();
    }

    @GetMapping("/return/{clientId}")
    public ResponseEntity<?> returnClientById(@PathVariable Integer clientId){
        return clientBaseService.returnClientById(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable Integer clientId, @RequestBody ClientBase updateClientBase){
        return clientBaseService.updateClient(clientId, updateClientBase);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId){
        return clientBaseService.deleteClient(clientId);
    }
}
