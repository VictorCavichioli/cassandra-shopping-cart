package com.cassandra.rampup.CassandraUserTypes;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@UserDefinedType("role_type")
@Data
@AllArgsConstructor
public class RoleType {

    @CassandraType(type = Name.UUID)
    private UUID roleId;

    @CassandraType(type = Name.TEXT)
    private String role;
}
