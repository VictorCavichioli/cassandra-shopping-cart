package com.cassandra.rampup.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import com.cassandra.rampup.CassandraUserTypes.ProductOfferingType;

import lombok.Data;

@Table(value = "order")
@Data
public class Order {

    @Id
    @PrimaryKeyColumn(name = "ord_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID orderOfferingId;

    @Column("ord_instant_date")
    @CassandraType(type = Name.DATE)
    private LocalDate orderOfferingInstanDate;

    @Column("ord_discount")
    @CassandraType(type = Name.DOUBLE)
    private Double orderOfferingDiscount;

    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "product_offering_type")
    private Set<ProductOfferingType> orderProducts;

    @Column("ord_quantity")
    @CassandraType(type = Name.INT)
    private Integer orderOfferingQuantity;

    @Column("ord_total_price")
    @CassandraType(type = Name.DOUBLE)
    private Double orderOfferingTotalPrice;

    @Column("ord_cus_id")
    @CassandraType(type = Name.UUID)
    private UUID customerId;

    @Column("ord_cus_name")
    @CassandraType(type = Name.TEXT)
    private String customerName;

    @Column("ord_add_street")
    @CassandraType(type = Name.TEXT)
    private String addressStreet;

    @Column("ord_add_number")
    @CassandraType(type = Name.INT)
    private Integer addressNumber;

    @Column("ord_add_zipcode")
    @CassandraType(type = Name.TEXT)
    private String addressZipCode;

    @Column("ord_add_country")
    @CassandraType(type = Name.TEXT)
    private String addressCountry;

}
