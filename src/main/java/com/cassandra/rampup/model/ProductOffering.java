package com.cassandra.rampup.model;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import lombok.Data;

@Table(value = "product_offering")
@Data
public class ProductOffering {

    @Id
    @PrimaryKeyColumn(name = "pro_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID productOfferingId;

    @Column("pro_name")
    @CassandraType(type = Name.TEXT)
    private String productOfferingName;

    @Column("pro_price")
    @CassandraType(type = Name.DOUBLE)
    private Double productOfferingPrice;

    @Column("pro_validate")
    @CassandraType(type = Name.DATE)
    private LocalDate productOfferingValidate;

}
