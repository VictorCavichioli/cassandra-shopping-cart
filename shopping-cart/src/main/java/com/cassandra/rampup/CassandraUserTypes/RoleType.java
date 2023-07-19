package com.cassandra.rampup.CassandraUserTypes;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("role_type")
public enum RoleType {
    Operator,
    Admin
}