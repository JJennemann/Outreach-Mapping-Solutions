package com.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.services.ClientDemographicsService;
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
    public ResponseEntity<?> returnClientDemographicsByClientId(@PathVariable Integer clientId){
        return clientDemographicsService.returnClientDemographicsByClientId(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClientDemographics(@PathVariable Integer clientId,
                                                      @RequestBody ClientDemographics clientDemographics){
        return clientDemographicsService.updateClientDemographics(clientId, clientDemographics);
    }


// Creation and deletion of clientDemographics is cascaded with the clientBase creation and deletion
//    @PostMapping("/create/{clientId}")
//    public ResponseEntity<?> createNewClientDemographics(@PathVariable Integer clientId,
//                                                         @RequestBody ClientDemographics clientDemographics){
//        return clientDemographicsService.createNewClientDemographics(clientId, clientDemographics);
//    }

//    @DeleteMapping("/delete/{clientId}")
//    public ResponseEntity<?> deleteClientDemographics(@PathVariable Integer clientId){
//        return clientDemographicsService.deleteClientDemographics(clientId);
//    }


}
