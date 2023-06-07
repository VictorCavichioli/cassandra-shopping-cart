package com.cassandra.rampup.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import com.cassandra.rampup.CassandraUserTypes.AddressType;
import lombok.Data;

@Table(value = "customer")
@Data
public class Customer {

    @Id
    @PrimaryKeyColumn(name = "cus_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID id;

    @Column("cus_name")
    @CassandraType(type = Name.TEXT)
    private String costumerName;

    @Column("cus_document_number")
    @CassandraType(type = Name.TEXT)
    private String customerDocumentNumber;

    @Column("cus_status")
    @CassandraType(type = Name.TEXT)
    private String customerStatus;

    @Column("cus_credit_score")
    @CassandraType(type = Name.TEXT)
    private String customerCreditScore;

    @Column("cus_addresses")
    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "address_type")
    private Set<AddressType> addresses;

    @Column("cus_orders")
    @CassandraType(type = Name.LIST, typeArguments = { Name.UUID })
    private List<UUID> customerOrders;

}
