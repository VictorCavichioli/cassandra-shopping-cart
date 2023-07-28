package com.cassandra.rampup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cassandra.rampup.model.AddressById;
import com.cassandra.rampup.service.AddressByIdService;

@RestController
@RequestMapping("/api/address")
public class AddressByIdController {

    @Autowired
    private AddressByIdService addressByIdService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressById insert (@RequestBody AddressById addressById){
        return addressByIdService.insert(addressById);
    }
    
    @GetMapping()
    public List<AddressById> getAll(){
        return addressByIdService.getAll();
    }
}
