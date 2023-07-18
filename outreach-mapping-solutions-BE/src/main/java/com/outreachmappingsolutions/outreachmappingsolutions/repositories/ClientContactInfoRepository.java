package com.outreachmappingsolutions.outreachmappingsolutions.repositories;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientContactInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientContactInfoRepository extends CrudRepository<ClientContactInfo, Integer> {
}
