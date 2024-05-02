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

    // for returning entire client object, with demo and contact info subobjects
    @Mapping(source = "clientDemographics", target = "clientDemographicsDTO")
    @Mapping(source = "clientContactInfo", target = "clientContactInfoDTO")
    CompleteClientEntityDTO clientToClientBaseDTO(ClientBase client);

    // allows for the subobjects to get client ID in DTOs
    @Mapping(source = "client.id", target = "clientId")
    ClientDemographicsDTO demographicsToDemographicsDTO(ClientDemographics clientDemographics);
    @Mapping(source = "client.id", target = "clientId")
    ClientContactInfoDTO contactInfoToContactInfoDTO(ClientContactInfo clientContactInfo);

    // allows for updating objects
    @Mapping (target="clientDemographics", ignore = true)
    @Mapping (target="clientContactInfo", ignore = true)
    void updateClientFromDTO(CreateOrUpdateClientBaseDTO dto, @MappingTarget ClientBase entity);
}
