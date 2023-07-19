package com.outreachmappingsolutions.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.outreachmappingsolutions.services.ClientDemographicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientDemographics")
public class ClientDemographicsController {

    @Autowired
    private ClientDemographicsService clientDemographicsService;

    @GetMapping("/returnAll")
    public ResponseEntity<?> returnAllClientDemographics(){
        return clientDemographicsService.returnAllClientDemographics();
    }

    @GetMapping("/return/{clientId}")
    public ResponseEntity<?> returnClientDemographicsById(@PathVariable Integer clientId){
        return clientDemographicsService.returnClientDemographicsById(clientId);
    }

    @PostMapping("/add/{clientId}")
    public ResponseEntity<?> addClientDemographicsToDatabase(@PathVariable Integer clientId,
                                                             @RequestBody ClientDemographics clientDemographics){
        return clientDemographicsService.addClientDemographicsToDatabase(clientId, clientDemographics);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> deleteClientDemographics(@PathVariable Integer clientId){
        return clientDemographicsService.deleteClientDemographics(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClientDemographics(@PathVariable Integer clientId,
                                                      @RequestBody ClientDemographics clientDemographics){
        return clientDemographicsService.updateClientDemographics(clientId, clientDemographics);
    }
}
