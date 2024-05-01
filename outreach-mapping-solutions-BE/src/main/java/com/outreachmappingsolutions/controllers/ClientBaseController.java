package com.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.dtos.CreateNewClientBaseDTO;
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
    public ResponseEntity<?> createNewClient(@RequestBody CreateNewClientBaseDTO newClientBaseDTO){
        return clientBaseService.createNewClient(newClientBaseDTO);
    }

    @GetMapping("/return/{clientId}")
    public ResponseEntity<?> returnClientById(@PathVariable Integer clientId){
        return clientBaseService.returnClientById(clientId);
    }

    @GetMapping("/returnAll")
    public ResponseEntity<?> returnAllClients(){
        return clientBaseService.returnAllClients();
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
