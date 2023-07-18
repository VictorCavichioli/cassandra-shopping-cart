package com.cassandra.rampup.model.tuning;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import com.cassandra.rampup.CassandraUserTypes.AddressType;
import com.cassandra.rampup.CassandraUserTypes.CustomerType;
import com.cassandra.rampup.CassandraUserTypes.ProductOfferingType;

public class OrderByUser {
    
    @PrimaryKeyColumn(name = "use_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID userId;

    @PrimaryKey("ord_id")
    @CassandraType(type = Name.UUID)
    private UUID ordId;

    @CassandraType(type = Name.DATE)
    @Column("ord_instant_date")
    private Date instantDate;

    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "product_offering_type")
    @Column("ord_products")
    private Set<ProductOfferingType> products;

    @CassandraType(type = Name.INT)
    @Column("ord_quantity")
    private Integer quantity;

    @CassandraType(type = Name.UDT, userTypeName = "customer_type")
    @Column("ord_cus")
    private CustomerType customer;

    @CassandraType(type = Name.UDT, userTypeName = "address_type")
    @Column("ord_address")
    private AddressType address;
}
