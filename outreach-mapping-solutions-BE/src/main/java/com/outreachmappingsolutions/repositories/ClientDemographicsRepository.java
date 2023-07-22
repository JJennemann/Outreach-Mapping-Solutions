package com.outreachmappingsolutions.repositories;


import com.outreachmappingsolutions.models.ClientDemographics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDemographicsRepository extends CrudRepository <ClientDemographics, Integer> {

    Optional<ClientDemographics> findByClientId(Integer clientId);

}
