package com.cassandra.rampup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cassandra.rampup.model.CustomerByUser;
import com.cassandra.rampup.service.CustomerByUserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerByUserService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerByUser createCustomer(@RequestBody CustomerByUser customer) {
        return customerService.insert(customer);
    }

    @GetMapping()
    public List<CustomerByUser> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerByUser getById(@PathVariable UUID id){
        return customerService.getById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerByUser> updateCustomer(@PathVariable UUID id, @RequestBody Map<String, Object> fieldsToUpdate) {
        return customerService.updateCustomer(id, fieldsToUpdate);
    }

    @DeleteMapping("/{customerId}/address/{addressId}")
    public CustomerByUser deleteAddressByCustomer(@PathVariable UUID customerId, @PathVariable UUID addressId){
        return customerService.deleteAddressById(customerId, addressId);
    }

}
