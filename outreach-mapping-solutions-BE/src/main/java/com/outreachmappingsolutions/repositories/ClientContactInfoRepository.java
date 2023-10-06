package com.outreachmappingsolutions.repositories;

import com.outreachmappingsolutions.models.ClientContactInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientContactInfoRepository extends CrudRepository<ClientContactInfo, Integer> {

    Optional<ClientContactInfo> findByClientId(Integer clientId);

}
