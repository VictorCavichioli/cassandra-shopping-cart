package com.cassandra.rampup.CassandraUserTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@UserDefinedType("address_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressType {

    @CassandraType(type = Name.UUID)
    @Column("id")
    private UUID id;

    @CassandraType(type = Name.TEXT)
    @Column("address")
    private String address;
    
}