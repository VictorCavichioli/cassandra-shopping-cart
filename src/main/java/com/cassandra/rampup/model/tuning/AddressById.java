package com.cassandra.rampup.model.tuning;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table("address_by_id")
@Data
public class AddressById {

    @PrimaryKeyColumn(name = "add_state", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String state;

    @PrimaryKey("add_cus_id")
    @CassandraType(type = Name.UUID)
    private UUID id;

    @PrimaryKeyColumn(name = "add_zip_code", type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.TEXT)
    private String zipCode;

    @CassandraType(type = Name.TEXT)
    @Column("add_street")
    private String street;

    @CassandraType(type = Name.INT)
    @Column("add_number")
    private Integer number;

    @CassandraType(type = Name.TEXT)
    @Column("add_country")
    private String country;

}
