package com.cassandra.rampup.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassandra.rampup.model.ProductOffering;
import com.cassandra.rampup.service.ProductOfferingService;

@RestController
@RequestMapping("/api/product-offering")
public class ProductOfferingController {

    @Autowired
    private ProductOfferingService productOfferingService;

    @PostMapping
    public ProductOffering insert(@RequestBody ProductOffering productOffering){
        return productOfferingService.insert(productOffering);
    }

    @GetMapping
    public List<ProductOffering> getAll(){
        return productOfferingService.getAll();
    }

    @GetMapping("/{id}")
    public ProductOffering getById(@PathVariable UUID id){
        return productOfferingService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        productOfferingService.delete(id);
    }
}
