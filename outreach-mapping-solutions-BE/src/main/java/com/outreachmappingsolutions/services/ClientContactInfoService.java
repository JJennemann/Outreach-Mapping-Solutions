package com.outreachmappingsolutions.services;

import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.repositories.ClientContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientContactInfoService {

    private static final String NO_CONTACT_INFO_FOUND = "No client contact information matching your criteria was found";
    private static final String CONTACT_INFO_UPDATED_SUCCESS = "Client contact information was successfully updated";

    @Autowired
    private ClientContactInfoRepository clientContactInfoRepository;

    public ResponseEntity<?> returnAllClientContactInfo(){
        List<ClientContactInfo> allClientContactInfo = (List<ClientContactInfo>) clientContactInfoRepository.findAll();
        if(allClientContactInfo.isEmpty()){
            return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allClientContactInfo, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> returnClientContactInfoByClientId(Integer clientId){
        Optional<ClientContactInfo> returnedOptionalClientContactInfo = clientContactInfoRepository.findByClientId(clientId);
        if(returnedOptionalClientContactInfo.isEmpty()){
            return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
        } else {
            ClientContactInfo returnedClientContactInfo = returnedOptionalClientContactInfo.get();
            return new ResponseEntity<>(returnedClientContactInfo, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateClientContactInfo(Integer clientId, ClientContactInfo clientContactInfo){
        Optional<ClientContactInfo> returnedOptionalClientContactInfo = clientContactInfoRepository.findByClientId(clientId);
        if(returnedOptionalClientContactInfo.isEmpty()){
            return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
        } else{
            ClientContactInfo returnedClientContactInfo = returnedOptionalClientContactInfo.get();
            returnedClientContactInfo.setPhonePrimary(clientContactInfo.getPhonePrimary());
            returnedClientContactInfo.setPhoneSecondary(clientContactInfo.getPhoneSecondary());
            returnedClientContactInfo.setEmail(clientContactInfo.getEmail());
            returnedClientContactInfo.setIceName(clientContactInfo.getIceName());
            returnedClientContactInfo.setIceRelationship(clientContactInfo.getIceRelationship());
            returnedClientContactInfo.setIcePhonePrimary(clientContactInfo.getIcePhonePrimary());
            returnedClientContactInfo.setIcePhoneSecondary(clientContactInfo.getIcePhoneSecondary());
            returnedClientContactInfo.setIceEmail(clientContactInfo.getIceEmail());
            clientContactInfoRepository.save(returnedClientContactInfo);

            return new ResponseEntity<>(CONTACT_INFO_UPDATED_SUCCESS, HttpStatus.OK);
        }
    }


// Creation and deletion of clientDemographics is cascaded with the clientBase creation and deletion
//    public ResponseEntity<?> createNewClientContactInfo(Integer clientId, ClientContactInfo clientContactInfo){
//        ClientBase returnedClient = clientBaseService.findClientById(clientId);
//        if(returnedClient.getId() == null){
//            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
//        } else {
//            clientContactInfo.setClient(returnedClient);
//            clientContactInfoRepository.save(clientContactInfo);
//
//            return new ResponseEntity<>(clientContactInfo, HttpStatus.OK);
//        }
//    }
//public ResponseEntity<?> deleteClientContactInfo(Integer clientId){
//    ClientContactInfo returnedClientContactInfo = findClientContactInfoByClientId(clientId);
//    if(returnedClientContactInfo.getId() == null){
//        return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
//    } else{
//        Integer clientContactInfoToDeleteId = returnedClientContactInfo.getId();
//        clientContactInfoRepository.deleteById(clientContactInfoToDeleteId);
//        return new ResponseEntity<>(CONTACT_INFO_DELETED_SUCCESS, HttpStatus.OK);
//    }
//}

}
