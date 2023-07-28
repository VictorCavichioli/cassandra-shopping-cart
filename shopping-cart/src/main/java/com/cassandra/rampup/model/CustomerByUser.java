package com.cassandra.rampup.model;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.cassandra.rampup.CassandraUserTypes.AddressType;
import com.cassandra.rampup.CassandraUserTypes.OrderType;

import lombok.Data;

@Table("customer_by_user")
@Data
public class CustomerByUser {

    @PrimaryKeyColumn(name = "cus_type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String type;

    @PrimaryKeyColumn(name = "use_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.UUID)
    private UUID useId;

    @PrimaryKeyColumn(name = "cus_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.UUID)
    private UUID cusId;

    @CassandraType(type = Name.TEXT)
    @Column("cus_name")
    private String name;

    @CassandraType(type = Name.TEXT)
    @Column("cus_document_number")
    private String documentNumber;

    @CassandraType(type = Name.TEXT)
    @Column("cus_creadit_score")
    private String creditScore;

    @Column("cus_addresses")
    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "address_type")
    private Set<AddressType> addresses;

    @Column("cus_orders")
    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "order_type")
    private Set<OrderType> orders;
}
