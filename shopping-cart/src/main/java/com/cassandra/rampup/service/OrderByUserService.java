package com.cassandra.rampup.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.cassandra.rampup.CassandraUserTypes.OrderType;
import com.cassandra.rampup.CassandraUserTypes.ProductOfferingType;
import com.cassandra.rampup.model.CustomerByUser;
import com.cassandra.rampup.model.OrderByCustomer;
import com.cassandra.rampup.model.ProductOffering;
import com.cassandra.rampup.repository.OrderByUserRepository;

@Service
public class OrderByUserService {

    @Autowired
    private OrderByUserRepository orderByUserRepository;

    @Autowired
    private CustomerByUserService customerByUserService;

    @Autowired
    private ProductOfferingService productOfferingService;

    public ResponseEntity<OrderByCustomer> insert(OrderByCustomer orderByCustomer) {
        orderByCustomer.setOrdId(UUID.randomUUID());
        CustomerByUser customerByUser = customerByUserService.getById(orderByCustomer.getCusId());
        if (customerByUser == null) {
            return ResponseEntity.notFound().build();
        }
        orderByCustomer.setInstantDate(LocalDate.now());
    
        calculateFinalPrice(orderByCustomer);
    
        orderByUserRepository.save(orderByCustomer);
        updateCustomerWithNewOrder(customerByUser, orderByCustomer);
    
        return ResponseEntity.ok(orderByCustomer);
    }
    
    private void calculateFinalPrice(OrderByCustomer orderByUser) {
        Set<ProductOfferingType> orderByUserProducts = orderByUser.getProducts();
        Double finalPrice = 0.0;
        for (ProductOfferingType productOfferingType : orderByUserProducts) {
            ProductOffering productOffering = productOfferingService.getById(productOfferingType.getId());
            if (productOffering == null) {
                throw new IllegalArgumentException("Product offering not found: " + productOfferingType.getId());
            }
            productOfferingType.setName(productOffering.getName());
            productOfferingType.setPrice(productOffering.getPrice());
            finalPrice += (productOfferingType.getPrice() - productOffering.getDiscount());
        }
        orderByUser.setFinalPrice(finalPrice);
    }
    
    private void updateCustomerWithNewOrder(CustomerByUser customerByUser, OrderByCustomer orderByUser) {
        OrderType newOrder = new OrderType(
            orderByUser.getOrdId(), 
            orderByUser.getProducts(), 
            orderByUser.getAddress(), 
            orderByUser.getFinalPrice());

        Map<String, Object> customerOrders = new HashMap<>();
        customerOrders.put("orders", newOrder);
    
        customerByUserService.updateCustomer(customerByUser.getCusId(), customerOrders);
    }
    

    public List<OrderByCustomer> getAll(){
        return orderByUserRepository.findAll();
    }

    public OrderByCustomer getById(UUID id){
        return orderByUserRepository.findById(id).get();
    }

}
