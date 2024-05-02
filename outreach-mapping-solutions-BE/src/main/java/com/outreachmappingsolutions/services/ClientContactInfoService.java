package com.outreachmappingsolutions.services;

import com.outreachmappingsolutions.dtos.ClientContactInfoDTO;
import com.outreachmappingsolutions.mappers.ClientMapper;
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

    private static final String NO_CONTACT_INFO_FOUND = "No client contact information matching your criteria was found.";
    private static final String RETURN_CONTACT_INFO_FAILED = "Something went wrong. Failed to retrieve client contact information. Try again.";
    private static final String CONTACT_INFO_UPDATE_FAILED = "Failed to update client contact information.";

    private final ClientContactInfoRepository clientContactInfoRepository;
    private final ClientMapper clientMapper;

    public ClientContactInfoService(ClientContactInfoRepository clientContactInfoRepository, ClientMapper clientMapper) {
        this.clientContactInfoRepository = clientContactInfoRepository;
        this.clientMapper = clientMapper;
    }

    public ResponseEntity<?> returnAllClientContactInfo() {
        try {
            List<ClientContactInfo> allClientContactInfo = (List<ClientContactInfo>) clientContactInfoRepository.findAll();
            if (allClientContactInfo.isEmpty()) {
                return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
            } else {
                List<ClientContactInfoDTO> allClientContactInfoDTOs = allClientContactInfo.stream()
                        .map(clientContactInfo -> clientMapper.mapDTOFromClientContactInfo(clientContactInfo))
                        .toList();

                return new ResponseEntity<>(allClientContactInfoDTOs, HttpStatus.OK);
            }
        } catch(Exception e){
            return new ResponseEntity<>(RETURN_CONTACT_INFO_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> returnClientContactInfoByClientId(Integer clientId) {
        try {
            Optional<ClientContactInfo> returnedOptionalClientContactInfo = clientContactInfoRepository.findByClientId(clientId);
            if (returnedOptionalClientContactInfo.isEmpty()) {
                return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientContactInfo returnedClientContactInfo = returnedOptionalClientContactInfo.get();
                ClientContactInfoDTO returnedClientContactInfoDTO = clientMapper.mapDTOFromClientContactInfo(returnedClientContactInfo);

                return new ResponseEntity<>(returnedClientContactInfoDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(RETURN_CONTACT_INFO_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateClientContactInfo(Integer clientId, ClientContactInfoDTO clientContactInfoToUpdate) {
        try {
            Optional<ClientContactInfo> returnedOptionalClientContactInfo = clientContactInfoRepository.findByClientId(clientId);
            if (returnedOptionalClientContactInfo.isEmpty()) {
                return new ResponseEntity<>(NO_CONTACT_INFO_FOUND, HttpStatus.NOT_FOUND);
            } else {
                ClientContactInfo updatedClientContactInfo = returnedOptionalClientContactInfo.get();
                clientMapper.updateClientContactInfoFromDTO(clientContactInfoToUpdate, updatedClientContactInfo);
                clientContactInfoRepository.save(updatedClientContactInfo);

                ClientContactInfoDTO updatedClientContactInfoDTO = clientMapper.mapDTOFromClientContactInfo(updatedClientContactInfo);

                return new ResponseEntity<>(updatedClientContactInfoDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(CONTACT_INFO_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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