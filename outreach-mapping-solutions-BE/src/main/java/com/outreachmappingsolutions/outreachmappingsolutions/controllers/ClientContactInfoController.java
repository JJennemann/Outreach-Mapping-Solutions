package com.outreachmappingsolutions.outreachmappingsolutions.controllers;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.outreachmappingsolutions.services.ClientContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientContactInfo")
public class ClientContactInfoController {

    @Autowired
    private ClientContactInfoService clientContactInfoService;

    @PostMapping("/create/{clientId}")
    public ResponseEntity<?> createNewClientContactInfo(@PathVariable Integer clientId,
                                                        @RequestBody ClientContactInfo clientContactInfo){
        return clientContactInfoService.createNewClientContactInfo(clientId, clientContactInfo);
    }

    @GetMapping("/returnAll")
    public ResponseEntity<?> returnAllClientContactInfo(){
        return clientContactInfoService.returnAllClientContactInfo();
    }

    @GetMapping("/return/{clientId}")
    public ResponseEntity<?> returnClientContactInfoByClientId(@PathVariable Integer clientId){
        return clientContactInfoService.returnClientContactInfoByClientId(clientId);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClientContactInfo(@PathVariable Integer clientId,
                                                     @RequestBody ClientContactInfo clientContactInfo){
        return clientContactInfoService.updateClientContactInfo(clientId, clientContactInfo);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> deleteClientContactInfo(@PathVariable Integer clientId){
        return clientContactInfoService.deleteClientContactInfo(clientId);
    }
}
