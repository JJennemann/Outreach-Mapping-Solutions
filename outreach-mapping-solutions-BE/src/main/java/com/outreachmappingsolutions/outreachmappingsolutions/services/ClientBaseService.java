package com.outreachmappingsolutions.outreachmappingsolutions.services;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.outreachmappingsolutions.repositories.ClientBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClientBaseService {

    private static final String NO_CLIENTS_FOUND = "No clients matching your criteria were found";
    private static final String CLIENT_ADDED_SUCCESS = "Client was successfully added to the database";
    private static final String Client_DELETED_SUCCESS = "Client was successfully deleted";

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

    public ResponseEntity<?> returnClientById(Integer id){
        Optional<ClientBase> returnedClient = clientBaseRepository.findById(id);
        if(returnedClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedClient, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addClientToDatabase(ClientBase clientBase){
        clientBaseRepository.save(clientBase);
        return new ResponseEntity<>(CLIENT_ADDED_SUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteClient(Integer id){
        Optional<ClientBase> returnedClient = clientBaseRepository.findById(id);
        if(returnedClient.isEmpty()){
            return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
        } else {
            clientBaseRepository.deleteById(id);
            return new ResponseEntity<>(Client_DELETED_SUCCESS, HttpStatus.OK);
        }
    }
}
