package com.cassandra.rampup.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.cassandra.rampup.model.ProductOffering;

public interface ProductOfferingRepository extends CassandraRepository<ProductOffering, UUID> {
    
}

