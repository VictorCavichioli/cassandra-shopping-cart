package com.cassandra.rampup.model;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("address_by_customer")
@Data
@AllArgsConstructor
public class AddressByCustomer {

    @PrimaryKeyColumn(name = "cus_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID cusId;

    @PrimaryKeyColumn(name = "add_cus_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.UUID)
    private UUID addCusId;

    @CassandraType(type = Name.TEXT)
    @Column("add_cus_address")
    private String addCusAddress;
}
