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

@Table("customer_by_user")
@Data
public class CustomerByUser {

    @PrimaryKeyColumn(name = "cus_type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String cusType;

    @PrimaryKey("use_id")
    @CassandraType(type = Name.UUID)
    private UUID useId;

    @PrimaryKey("cus_id")
    @CassandraType(type = Name.UUID)
    private UUID cusId;

    @CassandraType(type = Name.TEXT)
    @Column("cus_name")
    private String cusName;

    @CassandraType(type = Name.TEXT)
    @Column("cus_document_number")
    private String documentNumber;

    @CassandraType(type = Name.TEXT)
    @Column("cus_creadit_score")
    private String creditScore;
}
