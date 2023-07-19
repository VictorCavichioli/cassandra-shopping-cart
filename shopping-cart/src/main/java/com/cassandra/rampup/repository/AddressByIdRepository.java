package com.cassandra.rampup.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.cassandra.rampup.model.AddressById;

public interface AddressByIdRepository extends CassandraRepository<AddressById, UUID> {
    
}

