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

    @PostMapping("/create/{clientId}")
    public ResponseEntity<?> createNewClientDemographics(@PathVariable Integer clientId,
                                                         @RequestBody ClientDemographics clientDemographics){
        return clientDemographicsService.createNewClientDemographics(clientId, clientDemographics);
    }

    @GetMapping("/returnAll")
    public ResponseEntity<?> returnAllClientDemographics(){
        return clientDemographicsService.returnAllClientDemographics();
    }

    @GetMapping("/return/{clientId}")
    public ResponseEntity<?> returnClientDemographicsByClientId(@PathVariable Integer clientId){
        return clientDemographicsService.returnClientDemographicsByClientId(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClientDemographics(@PathVariable Integer clientId,
                                                      @RequestBody ClientDemographics clientDemographics){
        return clientDemographicsService.updateClientDemographics(clientId, clientDemographics);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> deleteClientDemographics(@PathVariable Integer clientId){
        return clientDemographicsService.deleteClientDemographics(clientId);
    }
}
