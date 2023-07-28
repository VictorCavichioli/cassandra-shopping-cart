package com.cassandra.rampup.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cassandra.rampup.model.UserByEmail;
import com.cassandra.rampup.repository.UserByEmailRepository;

@Service
public class UserByEmailService {
    
    @Autowired
    private UserByEmailRepository userRepository;

    public UserByEmail insert (UserByEmail user){

        user.setUseId(UUID.randomUUID());
        
        return userRepository.save(user);
    }
}
