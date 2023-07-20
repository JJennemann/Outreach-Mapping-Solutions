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


// Creation and deletion of clientDemographics is cascaded with the clientBase creation and deletion
//    @PostMapping("/create/{clientId}")
//    public ResponseEntity<?> createNewClientContactInfo(@PathVariable Integer clientId,
//                                                        @RequestBody ClientContactInfo clientContactInfo){
//        return clientContactInfoService.createNewClientContactInfo(clientId, clientContactInfo);
//    }
//    @DeleteMapping("/delete/{clientId}")
//    public ResponseEntity<?> deleteClientContactInfo(@PathVariable Integer clientId){
//        return clientContactInfoService.deleteClientContactInfo(clientId);
//    }
}
