package com.outreachmappingsolutions.outreachmappingsolutions.services;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.outreachmappingsolutions.repositories.ClientDemographicsRepository;
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

    public ResponseEntity<?> createNewClient(ClientBase clientBase){
        clientBaseRepository.save(clientBase);
        return new ResponseEntity<>(clientBase.getId(), HttpStatus.CREATED);
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
        ClientBase returnedClient = findClientById(clientId);;
        if(returnedClient.getId() == null){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedClient, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateClient(Integer clientId, ClientBase updatedClientBase){
        ClientBase clientToUpdate = findClientById(clientId);
        if(clientToUpdate.getId() == null){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
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

            // setting the clientDemo/clientContactInfo, etc. client here rather than when I create the
            // initial clientDemo, to not have to access clientBaseService or Repo from the clientDemoService
//            updatedClientBase.getClientDemographics().setClient(clientToUpdate);
            clientToUpdate.setClientDemographics(updatedClientBase.getClientDemographics());

//            updatedClientBase.getClientContactInfo().setClient(clientToUpdate);
            clientToUpdate.setClientContactInfo(updatedClientBase.getClientContactInfo());
            clientBaseRepository.save(clientToUpdate);

            return new ResponseEntity<>(CLIENT_UPDATED_SUCCESS, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteClient(Integer clientId){
        ClientBase returnedClient = findClientById(clientId);
        if(returnedClient.getId() == null){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            clientBaseRepository.deleteById(clientId);
            return new ResponseEntity<>(CLIENT_DELETED_SUCCESS, HttpStatus.OK);
        }
    }

    public ClientBase findClientById(Integer clientId){
        Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
        if(returnedOptionalClient.isEmpty()){
            return new ClientBase();
        }else {
            return returnedOptionalClient.get();
        }
    }
}
