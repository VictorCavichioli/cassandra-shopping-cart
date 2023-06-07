package com.cassandra.rampup.CassandraUserTypes;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@UserDefinedType("product_offering_type")
@Data
@AllArgsConstructor
public class ProductOfferingType {

    @CassandraType(type = Name.UUID)
    private UUID productOfferingId;

    @CassandraType(type = Name.TEXT)
    private String productOfferingName;

    @CassandraType(type = Name.DOUBLE)
    private Double productOfferingPrice;
    
}
