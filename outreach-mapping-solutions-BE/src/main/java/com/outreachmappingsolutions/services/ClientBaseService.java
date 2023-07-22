package com.outreachmappingsolutions.services;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



    public ResponseEntity<?> createNewClient(ClientBase clientBase){
        ClientBase newClient = new ClientBase(clientBase.getFirstName(), clientBase.getMiddleName(),
                clientBase.getLastName(), clientBase.getNameDataQuality(), clientBase.getDobMonth(),
                clientBase.getDobDay(), clientBase.getDobYear(), clientBase.getDobDataQuality(),
                clientBase.getFirstThreeSsn(), clientBase.getMiddleTwoSsn(), clientBase.getLastFourSsn(),
                clientBase.getSsnDataQuality());
        ClientDemographics newClientDemographics = new ClientDemographics();
        newClientDemographics.setClient(newClient);
        ClientContactInfo newClientContactInfo = new ClientContactInfo();
        newClientContactInfo.setClient(newClient);

        newClient.setClientDemographics(newClientDemographics);
        newClient.setClientContactInfo(newClientContactInfo);

        clientBaseRepository.save(newClient);

        return new ResponseEntity<>(newClient.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> returnAllClients(){
        List<ClientBase> allClients = (List<ClientBase>) clientBaseRepository.findAll();
        if(allClients.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allClients, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> returnClientById(Integer clientId){
        Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
        if(returnedOptionalClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            ClientBase returnedClient = returnedOptionalClient.get();
            return new ResponseEntity<>(returnedClient, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateClient(Integer clientId, ClientBase updatedClientBase){
        Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
        if(returnedOptionalClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            ClientBase clientToUpdate = returnedOptionalClient.get();
            clientToUpdate.setFirstName(updatedClientBase.getFirstName());
            clientToUpdate.setMiddleName(updatedClientBase.getMiddleName());
            clientToUpdate.setLastName(updatedClientBase.getLastName());
            clientToUpdate.setNameDataQuality(updatedClientBase.getNameDataQuality());

            clientToUpdate.setDobMonth(updatedClientBase.getDobMonth());
            clientToUpdate.setDobDay(updatedClientBase.getDobDay());
            clientToUpdate.setDobYear(updatedClientBase.getDobYear());
            clientToUpdate.setDobDataQuality(updatedClientBase.getDobDataQuality());

            clientToUpdate.setFirstThreeSsn(updatedClientBase.getFirstThreeSsn());
            clientToUpdate.setMiddleTwoSsn(updatedClientBase.getMiddleTwoSsn());
            clientToUpdate.setLastFourSsn(updatedClientBase.getLastFourSsn());
            clientToUpdate.setSsnDataQuality(updatedClientBase.getSsnDataQuality());

            clientBaseRepository.save(clientToUpdate);

            return new ResponseEntity<>(CLIENT_UPDATED_SUCCESS, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteClient(Integer clientId){
        Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
        if(returnedOptionalClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            clientBaseRepository.deleteById(clientId);
            return new ResponseEntity<>(CLIENT_DELETED_SUCCESS, HttpStatus.OK);
        }
    }
}
