package com.outreachmappingsolutions.outreachmappingsolutions.repositories;

import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientBaseRepository extends CrudRepository<ClientBase, Integer> {
}
