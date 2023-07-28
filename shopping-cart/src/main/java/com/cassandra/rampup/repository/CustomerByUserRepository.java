package com.cassandra.rampup.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandra.rampup.model.CustomerByUser;

public interface CustomerByUserRepository extends CassandraRepository<CustomerByUser, UUID> {
    @Query("SELECT * FROM customer_by_user WHERE cus_id=?0")
    Optional<CustomerByUser> findByCusId(UUID cusId);
}

