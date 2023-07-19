package com.outreachmappingsolutions.outreachmappingsolutions.repositories;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDemographicsRepository extends CrudRepository <ClientDemographics, Integer> {
}
