package com.outreachmappingsolutions.outreachmappingsolutions.repositories;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientContactInfoRepository extends CrudRepository<ClientContactInfo, Integer> {

    Optional<ClientContactInfo> findByClientId(Integer clientId);

}
