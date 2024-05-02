package com.outreachmappingsolutions.mappers;

import com.outreachmappingsolutions.dtos.ClientContactInfoDTO;
import com.outreachmappingsolutions.dtos.ClientDemographicsDTO;
import com.outreachmappingsolutions.dtos.CompleteClientEntityDTO;
import com.outreachmappingsolutions.dtos.CreateOrUpdateClientBaseDTO;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T13:46:10-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public CompleteClientEntityDTO clientToClientBaseDTO(ClientBase client) {
        if ( client == null ) {
            return null;
        }

        CompleteClientEntityDTO completeClientEntityDTO = new CompleteClientEntityDTO();

        completeClientEntityDTO.setClientDemographicsDTO( clientDemographicsToClientDemographicsDTO( client.getClientDemographics() ) );
        completeClientEntityDTO.setClientContactInfoDTO( clientContactInfoToClientContactInfoDTO( client.getClientContactInfo() ) );
        completeClientEntityDTO.setId( client.getId() );
        completeClientEntityDTO.setCreatedAt( client.getCreatedAt() );
        completeClientEntityDTO.setLastModified( client.getLastModified() );
        completeClientEntityDTO.setFirstName( client.getFirstName() );
        completeClientEntityDTO.setMiddleName( client.getMiddleName() );
        completeClientEntityDTO.setLastName( client.getLastName() );
        completeClientEntityDTO.setNameDataQuality( client.getNameDataQuality() );
        completeClientEntityDTO.setDobMonth( client.getDobMonth() );
        completeClientEntityDTO.setDobDay( client.getDobDay() );
        completeClientEntityDTO.setDobYear( client.getDobYear() );
        completeClientEntityDTO.setDobDataQuality( client.getDobDataQuality() );
        completeClientEntityDTO.setFirstThreeSsn( client.getFirstThreeSsn() );
        completeClientEntityDTO.setMiddleTwoSsn( client.getMiddleTwoSsn() );
        completeClientEntityDTO.setLastFourSsn( client.getLastFourSsn() );
        completeClientEntityDTO.setSsnDataQuality( client.getSsnDataQuality() );

        return completeClientEntityDTO;
    }

    @Override
    public void createOrUpdateClientBaseFromDTO(CreateOrUpdateClientBaseDTO dto, ClientBase entity) {
        if ( dto == null ) {
            return;
        }

        entity.setFirstName( dto.getFirstName() );
        entity.setMiddleName( dto.getMiddleName() );
        entity.setLastName( dto.getLastName() );
        entity.setNameDataQuality( dto.getNameDataQuality() );
        entity.setDobMonth( dto.getDobMonth() );
        entity.setDobDay( dto.getDobDay() );
        entity.setDobYear( dto.getDobYear() );
        entity.setDobDataQuality( dto.getDobDataQuality() );
        entity.setFirstThreeSsn( dto.getFirstThreeSsn() );
        entity.setMiddleTwoSsn( dto.getMiddleTwoSsn() );
        entity.setLastFourSsn( dto.getLastFourSsn() );
        entity.setSsnDataQuality( dto.getSsnDataQuality() );
    }

    @Override
    public void updateClientDemographicsFromDTO(ClientDemographicsDTO dto, ClientDemographics entity) {
        if ( dto == null ) {
            return;
        }

        entity.setGender( dto.getGender() );
        entity.setRacePrimary( dto.getRacePrimary() );
        entity.setRaceSecondary( dto.getRaceSecondary() );
        entity.setEthnicity( dto.getEthnicity() );
        entity.setVeteranStatus( dto.getVeteranStatus() );
    }

    protected ClientDemographicsDTO clientDemographicsToClientDemographicsDTO(ClientDemographics clientDemographics) {
        if ( clientDemographics == null ) {
            return null;
        }

        ClientDemographicsDTO clientDemographicsDTO = new ClientDemographicsDTO();

        clientDemographicsDTO.setId( clientDemographics.getId() );
        clientDemographicsDTO.setCreatedAt( clientDemographics.getCreatedAt() );
        clientDemographicsDTO.setLastModified( clientDemographics.getLastModified() );
        clientDemographicsDTO.setGender( clientDemographics.getGender() );
        clientDemographicsDTO.setRacePrimary( clientDemographics.getRacePrimary() );
        clientDemographicsDTO.setRaceSecondary( clientDemographics.getRaceSecondary() );
        clientDemographicsDTO.setEthnicity( clientDemographics.getEthnicity() );
        clientDemographicsDTO.setVeteranStatus( clientDemographics.getVeteranStatus() );

        return clientDemographicsDTO;
    }

    protected ClientContactInfoDTO clientContactInfoToClientContactInfoDTO(ClientContactInfo clientContactInfo) {
        if ( clientContactInfo == null ) {
            return null;
        }

        ClientContactInfoDTO clientContactInfoDTO = new ClientContactInfoDTO();

        clientContactInfoDTO.setId( clientContactInfo.getId() );
        clientContactInfoDTO.setCreatedAt( clientContactInfo.getCreatedAt() );
        clientContactInfoDTO.setLastModified( clientContactInfo.getLastModified() );
        clientContactInfoDTO.setPhonePrimary( clientContactInfo.getPhonePrimary() );
        clientContactInfoDTO.setPhoneSecondary( clientContactInfo.getPhoneSecondary() );
        clientContactInfoDTO.setEmail( clientContactInfo.getEmail() );
        clientContactInfoDTO.setIceName( clientContactInfo.getIceName() );
        clientContactInfoDTO.setIceRelationship( clientContactInfo.getIceRelationship() );
        clientContactInfoDTO.setIcePhonePrimary( clientContactInfo.getIcePhonePrimary() );
        clientContactInfoDTO.setIcePhoneSecondary( clientContactInfo.getIcePhoneSecondary() );
        clientContactInfoDTO.setIceEmail( clientContactInfo.getIceEmail() );

        return clientContactInfoDTO;
    }
}
