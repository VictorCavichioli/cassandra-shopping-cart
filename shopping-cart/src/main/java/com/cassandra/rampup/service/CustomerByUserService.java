package com.cassandra.rampup.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cassandra.rampup.CassandraUserTypes.AddressType;
import com.cassandra.rampup.CassandraUserTypes.OrderType;
import com.cassandra.rampup.model.CustomerByUser;
import com.cassandra.rampup.repository.CustomerByUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerByUserService {

    @Autowired
    private CustomerByUserRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public CustomerByUser insert (CustomerByUser customer){

        customer.setCusId(UUID.randomUUID());
        
        return customerRepository.save(customer);
    }

    public List<CustomerByUser> getAll(){
        return customerRepository.findAll();
    }

    public ResponseEntity<CustomerByUser> updateCustomer(UUID customerId, Map<String, Object> fieldsToUpdate) {
        CustomerByUser customer = customerRepository.findByCusId(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        fieldsToUpdate.forEach((key, value) -> {
            switch (key) {
                case "name":
                    customer.setName((String) value);
                    break;
                case "customerDocumentNumber":
                    customer.setDocumentNumber((String) value);
                    break;
                case "type":
                    customer.setType((String) value);
                    break;
                case "customerCreditScore":
                    customer.setCreditScore((String) value);
                    break;
                case "addresses":
                    Set<AddressType> existingAddresses = customer.getAddresses();
                    if (existingAddresses == null) {
                        existingAddresses = new HashSet<>();
                    }
                    Set<AddressType> newAddresses = objectMapper.convertValue(value, new TypeReference<Set<AddressType>>() {});
                    existingAddresses.addAll(newAddresses);
                    customer.setAddresses(existingAddresses);
                    break;
                case "orders":
                    Set<OrderType> existingOrders = customer.getOrders();
                    if (existingOrders == null) {
                        existingOrders = new HashSet<>();
                    }
                    Set<OrderType> newOrders = objectMapper.convertValue(value, new TypeReference<Set<OrderType>>() {});
                    existingOrders.addAll(newOrders);
                    customer.setOrders(existingOrders);
                    break;
                default:
            }
        });

        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    public CustomerByUser getById(UUID id){
        return customerRepository.findById(id).get();
    }

    public CustomerByUser deleteAddressById(UUID customerId, UUID addressId){
        CustomerByUser customer = customerRepository.findById(customerId).get();
        Set<AddressType> addresses = customer.getAddresses();
        Iterator<AddressType> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            AddressType address = iterator.next();
            if (address.getId().equals(addressId)) {
                iterator.remove();
                break;
            }
        }
        customer.setAddresses(addresses);
        return customer;
    }

    public void deleteCustomer(UUID customerId){
        customerRepository.deleteById(customerId);
    }

}
