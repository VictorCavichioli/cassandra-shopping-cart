package com.cassandra.rampup.CassandraUserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@UserDefinedType("customer_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType {

    @CassandraType(type = Name.UUID)
    @Column("cus_id")
    private UUID id;

    @CassandraType(type = Name.UUID)
    @Column("use_id")
    private UUID userId;

    @CassandraType(type = Name.TEXT)
    @Column("cus_name")
    private String name;
}
