package com.cassandra.rampup.model;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table("user_by_email")
@Data
public class UserByEmail {

    @PrimaryKeyColumn(name = "use_authority", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String useAuthority;

    @PrimaryKey("use_email")
    @CassandraType(type = Name.TEXT)
    private String useEmail;

    @PrimaryKey("use_id")
    @CassandraType(type = Name.UUID)
    private UUID useId;

    @CassandraType(type = Name.TEXT)
    @Column("use_pass")
    private String usePass;

}
