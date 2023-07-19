package com.outreachmappingsolutions.outreachmappingsolutions.services;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.outreachmappingsolutions.repositories.ClientBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClientBaseService {

    private static final String NO_CLIENTS_FOUND = "No clients matching your criteria were found";
//    private static final String CLIENT_ADDED_SUCCESS = "Client was successfully added to the database";
    private static final String CLIENT_DELETED_SUCCESS = "Client was successfully deleted";
    private static final String CLIENT_UPDATED_SUCCESS = "Client was successfully updated";

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    public ResponseEntity<?> returnAllClients(){
        List<ClientBase> allClients = (List<ClientBase>) clientBaseRepository.findAll();
        if(allClients.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allClients, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> returnClientById(Integer clientId){
        Optional<ClientBase> returnedClient = findOptionalClientById(clientId);;
        if(returnedClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedClient, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addClientToDatabase(ClientBase clientBase){
        clientBaseRepository.save(clientBase);
        return new ResponseEntity<>(clientBase.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteClient(Integer clientId){
        Optional<ClientBase> returnedClient = findOptionalClientById(clientId);
        if(returnedClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            clientBaseRepository.deleteById(clientId);
            return new ResponseEntity<>(CLIENT_DELETED_SUCCESS, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateClient(Integer clientId, ClientBase clientBase){
        Optional<ClientBase> returnedClient = findOptionalClientById(clientId);
        if(returnedClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            ClientBase clientToUpdate = returnedClient.get();
            clientToUpdate.setFirstName(clientBase.getFirstName());
            clientToUpdate.setMiddleName(clientBase.getMiddleName());
            clientToUpdate.setLastName(clientBase.getLastName());
            clientToUpdate.setNameDataQuality(clientBase.getNameDataQuality());

            clientToUpdate.setDobMonth(clientBase.getDobMonth());
            clientToUpdate.setDobDay(clientBase.getDobDay());
            clientToUpdate.setDobYear(clientBase.getDobYear());
            clientToUpdate.setDobDataQuality(clientBase.getDobDataQuality());

            clientToUpdate.setFirstThreeSsn(clientBase.getFirstThreeSsn());
            clientToUpdate.setMiddleTwoSsn(clientBase.getMiddleTwoSsn());
            clientToUpdate.setLastFourSsn(clientBase.getLastFourSsn());
            clientToUpdate.setSsnDataQuality(clientBase.getSsnDataQuality());

            clientBase.getClientDemographics().setClient(clientToUpdate);
            clientToUpdate.setClientDemographics(clientBase.getClientDemographics());

            saveClientToDatabase(clientToUpdate);
            return new ResponseEntity<>(CLIENT_UPDATED_SUCCESS, HttpStatus.OK);
        }
    }

    public Optional<ClientBase> findOptionalClientById(Integer clientId){
        Optional<ClientBase> returnedClient = clientBaseRepository.findById(clientId);
        return returnedClient;
    }

    public void saveClientToDatabase(ClientBase clientBase){
        clientBaseRepository.save(clientBase);
    }
}
