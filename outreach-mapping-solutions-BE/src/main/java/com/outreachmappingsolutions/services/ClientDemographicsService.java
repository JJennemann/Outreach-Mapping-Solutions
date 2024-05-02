package com.outreachmappingsolutions.services;


import com.outreachmappingsolutions.dtos.ClientDemographicsDTO;
import com.outreachmappingsolutions.mappers.ClientMapper;
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

    private static final String NO_DEMOS_FOUND = "No client demographics matching your criteria were found.";
    private static final String RETURN_DEMOS_FAILED = "Something went wrong. Failed to retrieve client demographics. Try again.";
    private static final String DEMOS_UPDATE_FAILED = "Failed to update client demographics.";



    private final ClientDemographicsRepository clientDemographicsRepository;
    private final ClientMapper clientMapper;

    public ClientDemographicsService(ClientDemographicsRepository clientDemographicsRepository, ClientMapper clientMapper) {
        this.clientDemographicsRepository = clientDemographicsRepository;
        this.clientMapper = clientMapper;
    }

    public ResponseEntity<?> returnAllClientDemographics() {
        try {
            List<ClientDemographics> allClientDemos = (List<ClientDemographics>) clientDemographicsRepository.findAll();
            if (allClientDemos.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                List<ClientDemographicsDTO> allClientDemographicDTOs = allClientDemos.stream()
                        .map(clientDemo -> new ClientDemographicsDTO(clientDemo))
                        .toList();

                return new ResponseEntity<>(allClientDemographicDTOs, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(RETURN_DEMOS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> returnClientDemographicsByClientId(Integer clientId) {
        try {
            Optional<ClientDemographics> returnedOptionalClientDemo = clientDemographicsRepository.findByClientId(clientId);
            if (returnedOptionalClientDemo.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientDemographics returnedClientDemo = returnedOptionalClientDemo.get();
                ClientDemographicsDTO returnedClientDemoDTO = new ClientDemographicsDTO(returnedClientDemo);

                return new ResponseEntity<>(returnedClientDemoDTO, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(RETURN_DEMOS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateClientDemographics(Integer clientId, ClientDemographicsDTO updatedClientDemoDTO) {
        try {
            Optional<ClientDemographics> returnedOptionalClientDemo = clientDemographicsRepository.findByClientId(clientId);
            if (returnedOptionalClientDemo.isEmpty()) {
                return new ResponseEntity<>(NO_DEMOS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientDemographics clientDemoToUpdate = returnedOptionalClientDemo.get();
                clientMapper.updateClientDemographicsFromDTO(updatedClientDemoDTO, clientDemoToUpdate);
                clientDemographicsRepository.save(clientDemoToUpdate);

                ClientDemographicsDTO clientDemoUpdatedDTO = new ClientDemographicsDTO(clientDemoToUpdate);

                return new ResponseEntity<>(clientDemoUpdatedDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(DEMOS_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
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
