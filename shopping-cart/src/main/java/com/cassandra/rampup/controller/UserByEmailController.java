package com.cassandra.rampup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cassandra.rampup.model.UserByEmail;
import com.cassandra.rampup.service.UserByEmailService;

@RestController
@RequestMapping("/api/user")
public class UserByEmailController {

    @Autowired
    private UserByEmailService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserByEmail insert(@RequestBody UserByEmail user){
        return userService.insert(user);
    }
    
}
