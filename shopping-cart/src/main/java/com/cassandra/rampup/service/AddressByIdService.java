package com.cassandra.rampup.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandra.rampup.CassandraUserTypes.AddressType;
import com.cassandra.rampup.model.AddressByCustomer;
import com.cassandra.rampup.model.AddressById;
import com.cassandra.rampup.repository.AddressByCustomerRepository;
import com.cassandra.rampup.repository.AddressByIdRepository;

@Service
public class AddressByIdService {

    @Autowired
    private AddressByIdRepository addressByIdRepository;

    @Autowired
    private AddressByCustomerRepository addressByCustomerRepository;

    @Autowired
    private CustomerByUserService customerByUserService;

    public AddressById insert(AddressById addressById){

        addressById.setAddCusId(UUID.randomUUID());
        String fullAddress = addressById.getStreet()
            + ", " + addressById.getNumber()  + ", " 
            + addressById.getState() 
            + ", " + addressById.getCountry() 
            + " - " + addressById.getZipCode();
    
        AddressByCustomer addressByCustomer = new AddressByCustomer(addressById.getCusId(), addressById.getAddCusId(), fullAddress);
        addressByCustomerRepository.save(addressByCustomer);

        AddressType addressType = new AddressType(addressById.getAddCusId(), fullAddress);
        Map<String, Object> customerAddressMap = new HashMap<>();
        customerAddressMap.put("addresses", addressType);
        customerByUserService.updateCustomer(addressById.getCusId(), customerAddressMap);

        return addressByIdRepository.save(addressById);
    }

    public List<AddressById> getAll(){
        return addressByIdRepository.findAll();
    }
}
