package com.cassandra.rampup.model;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import com.cassandra.rampup.CassandraUserTypes.RoleType;
import lombok.Data;

@Table(value = "user")
@Data
public class User {

    @Id
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.UUID)
    private UUID userId;

    @Column("cus_id")
    @CassandraType(type = Name.UUID)
    private UUID customerId;

    @Column("use_email")
    @CassandraType(type = Name.TEXT)
    private String userEmail;

    @Column("use_pass")
    @CassandraType(type = Name.TEXT)
    private String userPassword;

    @Column("use_authoritie")
    @CassandraType(type = Name.SET, typeArguments = { Name.UDT }, userTypeName = "role_type")
    private Set<RoleType> userAuthorities;

}
