package com.cassandra.rampup.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.cassandra.rampup.model.UserByEmail;

public interface UserByEmailRepository extends CassandraRepository<UserByEmail, String> {
    
}

