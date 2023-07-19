package com.cassandra.rampup.model;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.Data;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product_offering_by_id")
@Data
public class ProductOffering {

    @PrimaryKeyColumn(name = "pro_category", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String category;

    @PrimaryKey("pro_id")
    @CassandraType(type = Name.UUID)
    private UUID id;

    @CassandraType(type = Name.TEXT)
    @Column("pro_name")
    private String name;

    @CassandraType(type = Name.DOUBLE)
    @Column("pro_price")
    private Double price;

    @CassandraType(type = Name.DOUBLE)
    @Column("pro_discount")
    private Double discount;

    @CassandraType(type = Name.DATE)
    @Column("pro_validate")
    private LocalDate validate;

    @CassandraType(type = Name.TEXT)
    @Column("pro_description")
    private String description;

    @Column("pro_image")
    @CassandraType(type = Name.SET, typeArguments = Name.BLOB)
    private Set<ByteBuffer> images;
    
}
