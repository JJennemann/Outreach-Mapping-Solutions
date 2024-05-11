package com.outreachmappingsolutions.services;

import com.outreachmappingsolutions.dtos.CreateOrUpdateClientBaseDTO;
import com.outreachmappingsolutions.dtos.CompleteClientEntityDTO;
import com.outreachmappingsolutions.mappers.ClientMapper;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientBaseService {

    private static final String CREATE_CLIENT_FAILED = "Failed to create new client.";
    private static final String NO_CLIENTS_FOUND = "No clients matching your criteria were found.";
    private static final String RETURN_CLIENTS_FAILED = "Something went wrong. Failed to retrieve bettor(s). Try again.";
    private static final String CLIENT_DELETED_SUCCESS = "Client was successfully deleted.";
    private static final String CLIENT_DELETED_FAILED = "Failed to delete client.";
    private static final String CLIENT_UPDATE_FAILED = "Failed to update client.";

/// using constructor injection for injecting dependencies vs. field injection(@autowired) - look up the difference to refresh yourself on why we did this. Too much to add as a note.
//    @Autowired
//    private ClientBaseRepository clientBaseRepository;

    private final ClientBaseRepository clientBaseRepository;
    private final ClientMapper clientMapper;

    public ClientBaseService( ClientBaseRepository clientBaseRepository, ClientMapper clientMapper){
        this.clientBaseRepository = clientBaseRepository;
        this.clientMapper = clientMapper;
    }

    public ResponseEntity<?> createNewClient(CreateOrUpdateClientBaseDTO newClientBaseDTO) {
        try {
            ClientBase newClient = new ClientBase();
            clientMapper.createOrUpdateClientBaseFromDTO(newClientBaseDTO, newClient);

            ClientDemographics newClientDemographics = new ClientDemographics();
            newClientDemographics.setClient(newClient);
            ClientContactInfo newClientContactInfo = new ClientContactInfo();
            newClientContactInfo.setClient(newClient);

            newClient.setClientDemographics(newClientDemographics);
            newClient.setClientContactInfo(newClientContactInfo);

            newClient.updateDisplayData();

            clientBaseRepository.save(newClient);

            CreateOrUpdateClientBaseDTO newClientDTO = clientMapper.mapDTOFromClientBase(newClient);


            return new ResponseEntity<>(newClientDTO, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(CREATE_CLIENT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> returnAllClients() {
        try{
            List<ClientBase> allClients = (List<ClientBase>) clientBaseRepository.findAll();
            if (allClients.isEmpty()) {
                return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                List<CompleteClientEntityDTO> allClientDTOs = allClients.stream()
                        .map(returnedClient -> ClientMapper.INSTANCE.clientToClientBaseDTO(returnedClient))
                        .toList();

                return new ResponseEntity<>(allClientDTOs, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(RETURN_CLIENTS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> returnClientById(Integer clientId) {
        try {
            Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
            if (returnedOptionalClient.isEmpty()) {
                return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientBase returnedClient = returnedOptionalClient.get();
                CompleteClientEntityDTO returnedClientDTO = ClientMapper.INSTANCE.clientToClientBaseDTO(returnedClient);

                return new ResponseEntity<>(returnedClientDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(RETURN_CLIENTS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateClient(Integer clientId, CreateOrUpdateClientBaseDTO clientBaseToUpdate){
        try {
            Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
            if (returnedOptionalClient.isEmpty()) {
                return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientBase updatedClientBase = returnedOptionalClient.get();
                clientMapper.createOrUpdateClientBaseFromDTO(clientBaseToUpdate, updatedClientBase);
                clientBaseRepository.save(updatedClientBase);

                CreateOrUpdateClientBaseDTO updatedClientBaseDTO = clientMapper.mapDTOFromClientBase(updatedClientBase);

                return new ResponseEntity<>(updatedClientBaseDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(CLIENT_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteClient(Integer clientId) {
        try {
            Optional<ClientBase> returnedOptionalClient = clientBaseRepository.findById(clientId);
            if (returnedOptionalClient.isEmpty()) {
                return new ResponseEntity<>(NO_CLIENTS_FOUND, HttpStatus.NOT_FOUND);
            } else {
                clientBaseRepository.deleteById(clientId);
                return new ResponseEntity<>(CLIENT_DELETED_SUCCESS, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(CLIENT_DELETED_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
