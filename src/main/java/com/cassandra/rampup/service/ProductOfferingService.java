package com.cassandra.rampup.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandra.rampup.model.ProductOffering;
import com.cassandra.rampup.repository.ProductOfferingRepository;

@Service
public class ProductOfferingService {

    @Autowired
    public ProductOfferingRepository productOfferingRepository;

    public ProductOffering insert(ProductOffering productOffering){
        productOffering.setProductOfferingId(UUID.randomUUID());
        return productOfferingRepository.save(productOffering);
    }

    public List<ProductOffering> getAll(){
        return productOfferingRepository.findAll();
    }

    public ProductOffering getById(UUID id){
        return productOfferingRepository.findById(id).get();
    }
    
    public void delete(UUID id){
        productOfferingRepository.delete(productOfferingRepository.findById(id).get());
    }

    public ResponseEntity<ProductOffering> update(UUID productOfferingId, Map<String, Object> fieldsToUpdate){
        ProductOffering productOffering = productOfferingRepository.findById(productOfferingId).orElse(null);
        if (productOffering == null) {
            return ResponseEntity.notFound().build();
        }

        fieldsToUpdate.forEach((key, value) -> {
            switch (key) {
                case "productOfferingName":
                    productOffering.setProductOfferingName((String) value);
                    break;
                case "productOfferingPrice":
                    productOffering.setProductOfferingPrice((Double) value);
                    break;
                case "productOfferingValidate":
                    productOffering.setProductOfferingValidate((LocalDate) value);
                    break;
                default:
                    // ignore unsupported fields
            }
        });

        productOfferingRepository.save(productOffering);
        return ResponseEntity.ok(productOffering);
    }
}
