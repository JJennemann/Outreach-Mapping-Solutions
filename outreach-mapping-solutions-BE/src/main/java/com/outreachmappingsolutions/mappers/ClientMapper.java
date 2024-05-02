package com.outreachmappingsolutions.mappers;


import com.outreachmappingsolutions.dtos.CompleteClientEntityDTO;
import com.outreachmappingsolutions.dtos.ClientContactInfoDTO;
import com.outreachmappingsolutions.dtos.ClientDemographicsDTO;
import com.outreachmappingsolutions.dtos.CreateOrUpdateClientBaseDTO;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    // for returning entire client object, with demo and contact info nested objects
    @Mapping(source = "clientDemographics", target = "clientDemographicsDTO")
    @Mapping(source = "clientContactInfo", target = "clientContactInfoDTO")
    @Mapping(target = "clientDemographicsDTO.clientId", ignore=true)
    @Mapping(target = "clientContactInfoDTO.clientId", ignore=true)
    CompleteClientEntityDTO clientToClientBaseDTO(ClientBase client);


    // Maps ClientBaseDTOs to ClientBase entities
    @Mapping (target="clientDemographics", ignore = true)
    @Mapping (target="clientContactInfo", ignore = true)
    void createOrUpdateClientBaseFromDTO(CreateOrUpdateClientBaseDTO dto, @MappingTarget ClientBase entity);

    //Maps ClientBase entities to ClientBaseDTOs
    CreateOrUpdateClientBaseDTO mapDTOFromClientBase(ClientBase clientBase);

    // Maps clientDemographicDTOs to clientDemographic entities
    @Mapping(target="client", ignore=true)
    void updateClientDemographicsFromDTO(ClientDemographicsDTO dto, @MappingTarget ClientDemographics entity);

    // Maps clientDemographic entities to ClientDemographicDT0s
    @Mapping(target="clientId", ignore = true)
    ClientDemographicsDTO mapDTOFromClientDemographics(ClientDemographics clientDemographics);

    // Maps clientContactInfoDTOs to ClientContactInfo entities
    @Mapping(target="client", ignore=true)
    void updateClientContactInfoFromDTO(ClientContactInfoDTO dto, @MappingTarget ClientContactInfo entity);

    //Maps ClientContactInfo entities to ClientContactInfoDTOs
    //Maps ClientContactInfo entities to ClientContactInfoDTOs
    @Mapping(target="clientId", ignore=true)
    ClientContactInfoDTO mapDTOFromClientContactInfo(ClientContactInfo clientContactInfo);



}
