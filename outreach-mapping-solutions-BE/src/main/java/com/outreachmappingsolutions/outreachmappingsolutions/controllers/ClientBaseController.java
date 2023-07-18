package com.outreachmappingsolutions.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.outreachmappingsolutions.services.ClientBaseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientBase")
public class ClientBaseController {

    @Autowired
    private ClientBaseService clientBaseService;

    @GetMapping("/returnAllClients")
    public ResponseEntity<?> returnAllClients(){
        return clientBaseService.returnAllClients();
    }

    @GetMapping("/returnClient/{id}")
    public ResponseEntity<?> returnClientById(@PathVariable Integer id){
        return clientBaseService.returnClientById(id);
    }

    @PostMapping("/addNewClient")
    public ResponseEntity<?> addToClientToDatabase(@RequestBody ClientBase clientBase){
        return clientBaseService.addClientToDatabase(clientBase);
    }

    @PostMapping("/deleteClient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id){
        return clientBaseService.deleteClient(id);
    }


}
