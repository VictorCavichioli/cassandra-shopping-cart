package com.cassandra.rampup.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.cassandra.rampup.model.Customer;

public interface CustomerRepository extends CassandraRepository<Customer, UUID> {
    
}
