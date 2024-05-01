//package com.outreachmappingsolutions.mappers;
//
//
//import com.outreachmappingsolutions.dtos.ClientBaseDTO;
//import com.outreachmappingsolutions.dtos.ClientContactInfoDTO;
//import com.outreachmappingsolutions.dtos.ClientDemographicsDTO;
//import com.outreachmappingsolutions.models.ClientBase;
//import com.outreachmappingsolutions.models.ClientContactInfo;
//import com.outreachmappingsolutions.models.ClientDemographics;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface ClientMapper {
//
//    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
//
//    @Mapping(source = "clientDemographics", target = "clientDemographicsDTO")
//    @Mapping(source = "clientContactInfo", target = "clientContactInfoDTO")
//    ClientBaseDTO clientToClientBaseDTO(ClientBase client);
//
//    ClientDemographicsDTO demographicsToDemographicsDTO(ClientDemographics clientDemographics);
//
//    ClientContactInfoDTO contactInfoToContactInfoDTO(ClientContactInfo clientContactInfo);
//}
