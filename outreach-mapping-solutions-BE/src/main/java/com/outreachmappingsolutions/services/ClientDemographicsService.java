package com.outreachmappingsolutions.services;


import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientDemographicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientDemographicsService {

    private static final String NO_DEMOS_FOUND = "No client demographics matching your criteria were found";
    private static final String NO_CLIENTS_FOUND = "No clients matching your criteria were found";
    private static final String CLIENT_DEMO_ADDED_SUCCESS = "Client demographics were successfully added to the database";
    private static final String CLIENT_DEMO_DELETED_SUCCESS = "Client demographics were successfully deleted";
    private static final String CLIENT_UPDATED_SUCCESS = "Client demographics were successfully updated";


    @Autowired
    private ClientDemographicsRepository clientDemographicsRepository;

    public ResponseEntity<?> returnAllClientDemographics() {
        try {
            List<ClientDemographics> allClientDemos = (List<ClientDemographics>) clientDemographicsRepository.findAll();
            if (allClientDemos.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(allClientDemos, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Failed to retrieve all client demographics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> returnClientDemographicsByClientId(Integer clientId) {
        try {
            Optional<ClientDemographics> returnedOptionalClientDemo = clientDemographicsRepository.findByClientId(clientId);
            if (returnedOptionalClientDemo.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientDemographics returnedClientDemo = returnedOptionalClientDemo.get();
                return new ResponseEntity<>(returnedClientDemo, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Failed to retrieve the client's demographics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateClientDemographics(Integer clientId, ClientDemographics clientDemographics) {
        try {
            Optional<ClientDemographics> returnedOptionalClientDemo = clientDemographicsRepository.findByClientId(clientId);
            if (returnedOptionalClientDemo.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientDemographics returnedClientDemo = returnedOptionalClientDemo.get();

                returnedClientDemo.setGender(clientDemographics.getGender());
                returnedClientDemo.setRacePrimary(clientDemographics.getRacePrimary());
                returnedClientDemo.setRaceSecondary(clientDemographics.getRaceSecondary());
                returnedClientDemo.setEthnicity(clientDemographics.getEthnicity());
                returnedClientDemo.setVeteranStatus(clientDemographics.getVeteranStatus());
                clientDemographicsRepository.save(returnedClientDemo);

                return new ResponseEntity<>(returnedClientDemo, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update the client's demographics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

// Creation and deletion of clientDemographics is cascaded with the clientBase creation and deletion
//    public ResponseEntity<?> deleteClientDemographics(Integer clientId){
//        ClientDemographics returnedClientDemo = findClientDemoByClientId(clientId);
//        if(returnedClientDemo.getId() == null){
//            return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
//        } else{
//            Integer clientDemoToDeleteId = returnedClientDemo.getId();
//            clientDemographicsRepository.deleteById(clientDemoToDeleteId);
//            return new ResponseEntity<>(CLIENT_DEMO_DELETED_SUCCESS, HttpStatus.OK);
//        }
//    }

//    public ResponseEntity<?> createNewClientDemographics(Integer clientId, ClientDemographics clientDemographics){
//        ClientBase returnedClient = clientBaseService.findClientById(clientId);
//        if(returnedClient.getId() == null){
//            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
//        } else{
//            clientDemographics.setClient(returnedClient);
//            clientDemographicsRepository.save(clientDemographics);
//
//            return new ResponseEntity<>(clientDemographics, HttpStatus.OK);
//        }
//    }
