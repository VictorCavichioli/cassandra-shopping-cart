package com.cassandra.rampup.CassandraUserTypes;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserDefinedType("order_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderType {
    
    @CassandraType(type = Name.UUID)
    @Column("ord_id")
    private UUID ordId;

    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "product_offering_type")
    @Column("ord_products")
    private Set<ProductOfferingType> products;

    @CassandraType(type = Name.UDT, userTypeName = "address_type")
    @Column("ord_address")
    private AddressType address;

    @CassandraType(type = Name.DOUBLE)
    @Column("ord_final_price")
    private Double finalPrice;
}
