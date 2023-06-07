package com.cassandra.rampup.model;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import lombok.Data;

@Table(value = "role")
@Data
public class Role {

    @Id
    @PrimaryKeyColumn(name = "rol_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID roleId;

    @Column("rol_id")
    @CassandraType(type = Name.TEXT)
    private String role;
}
