package com.cassandra.rampup.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cassandra.rampup.model.OrderByCustomer;
import com.cassandra.rampup.service.OrderByUserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderByUserService orderService;

    @PostMapping
    public ResponseEntity<OrderByCustomer> insert(@RequestBody OrderByCustomer order){
        return orderService.insert(order);
    }

    @GetMapping
    public List<OrderByCustomer> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderByCustomer getById(@PathVariable UUID id){
        return orderService.getById(id);
    }

}
