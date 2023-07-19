package com.cassandra.rampup.CassandraUserTypes;

import java.nio.ByteBuffer;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@UserDefinedType("product_offering_type")
@Data
@AllArgsConstructor
public class ProductOfferingType {

    @CassandraType(type = Name.UUID)
    @Column("id")
    private UUID id;

    @CassandraType(type = Name.TEXT)
    @Column("name")
    private String name;

    @CassandraType(type = Name.DOUBLE)
    @Column("price")
    private Double price;

    @CassandraType(type = Name.BLOB)
    @Column("image")
    private ByteBuffer image;
    
}