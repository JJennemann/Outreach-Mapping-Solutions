package com.outreachmappingsolutions.mappers;

import com.outreachmappingsolutions.dtos.ClientBaseDTO;
import com.outreachmappingsolutions.dtos.ClientContactInfoDTO;
import com.outreachmappingsolutions.dtos.ClientDemographicsDTO;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T15:01:44-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientBaseDTO clientToClientBaseDTO(ClientBase client) {
        if ( client == null ) {
            return null;
        }

        ClientBaseDTO clientBaseDTO = new ClientBaseDTO();

        clientBaseDTO.setClientDemographicsDTO( demographicsToDemographicsDTO( client.getClientDemographics() ) );
        clientBaseDTO.setClientContactInfoDTO( contactInfoToContactInfoDTO( client.getClientContactInfo() ) );
        clientBaseDTO.setId( client.getId() );
        clientBaseDTO.setCreatedAt( client.getCreatedAt() );
        clientBaseDTO.setLastModified( client.getLastModified() );
        clientBaseDTO.setFirstName( client.getFirstName() );
        clientBaseDTO.setMiddleName( client.getMiddleName() );
        clientBaseDTO.setLastName( client.getLastName() );
        clientBaseDTO.setNameDataQuality( client.getNameDataQuality() );
        clientBaseDTO.setDobMonth( client.getDobMonth() );
        clientBaseDTO.setDobDay( client.getDobDay() );
        clientBaseDTO.setDobYear( client.getDobYear() );
        clientBaseDTO.setDobDataQuality( client.getDobDataQuality() );
        clientBaseDTO.setFirstThreeSsn( client.getFirstThreeSsn() );
        clientBaseDTO.setMiddleTwoSsn( client.getMiddleTwoSsn() );
        clientBaseDTO.setLastFourSsn( client.getLastFourSsn() );
        clientBaseDTO.setSsnDataQuality( client.getSsnDataQuality() );

        return clientBaseDTO;
    }

    @Override
    public ClientDemographicsDTO demographicsToDemographicsDTO(ClientDemographics clientDemographics) {
        if ( clientDemographics == null ) {
            return null;
        }

        ClientDemographicsDTO clientDemographicsDTO = new ClientDemographicsDTO();

        clientDemographicsDTO.setClientId( clientDemographicsClientId( clientDemographics ) );
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

    @Override
    public ClientContactInfoDTO contactInfoToContactInfoDTO(ClientContactInfo clientContactInfo) {
        if ( clientContactInfo == null ) {
            return null;
        }

        ClientContactInfoDTO clientContactInfoDTO = new ClientContactInfoDTO();

        clientContactInfoDTO.setClientId( clientContactInfoClientId( clientContactInfo ) );
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

    private Integer clientDemographicsClientId(ClientDemographics clientDemographics) {
        if ( clientDemographics == null ) {
            return null;
        }
        ClientBase client = clientDemographics.getClient();
        if ( client == null ) {
            return null;
        }
        Integer id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer clientContactInfoClientId(ClientContactInfo clientContactInfo) {
        if ( clientContactInfo == null ) {
            return null;
        }
        ClientBase client = clientContactInfo.getClient();
        if ( client == null ) {
            return null;
        }
        Integer id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
