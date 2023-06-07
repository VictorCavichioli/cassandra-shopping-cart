package com.cassandra.rampup.service;

import java.util.ArrayList;
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
import com.cassandra.rampup.model.Customer;
import com.cassandra.rampup.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Customer insert (Customer customer){

        customer.setId(UUID.randomUUID());
        
        return customerRepository.save(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public ResponseEntity<Customer> updateCustomer(UUID customerId, Map<String, Object> fieldsToUpdate) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        fieldsToUpdate.forEach((key, value) -> {
            switch (key) {
                case "costumerName":
                    customer.setCostumerName((String) value);
                    break;
                case "customerDocumentNumber":
                    customer.setCustomerDocumentNumber((String) value);
                    break;
                case "customerStatus":
                    customer.setCustomerStatus((String) value);
                    break;
                case "customerCreditScore":
                    customer.setCustomerCreditScore((String) value);
                    break;
                case "addresses":
                    Set<AddressType> existingAddresses = customer.getAddresses();
                    if (existingAddresses == null) {
                        existingAddresses = new HashSet<>();
                    }
                    Set<AddressType> newAddresses = objectMapper.convertValue(value, new TypeReference<Set<AddressType>>() {});
                    for(AddressType addressType: newAddresses){
                        addressType.setId(UUID.randomUUID());
                    }
                    existingAddresses.addAll(newAddresses);
                    customer.setAddresses(existingAddresses);
                    break;
                case "customerOrders":
                    List<UUID> newOrders = objectMapper.convertValue(value, new TypeReference<List<UUID>>() {});
                    List<UUID> existingOrders = customer.getCustomerOrders();
                    if (existingOrders == null) {
                        existingOrders = new ArrayList<>();
                    }
                    existingOrders.addAll(newOrders);
                    customer.setCustomerOrders(existingOrders);
                    break;
                default:
            }
        });

        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    public Customer getById(UUID id){
        return customerRepository.findById(id).get();
    }

    public Customer deleteAddressById(UUID customerId, UUID addressId){
        Customer customer = customerRepository.findById(customerId).get();
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
