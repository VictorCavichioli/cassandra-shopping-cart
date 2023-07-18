package com.cassandra.rampup.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.cassandra.rampup.CassandraUserTypes.ProductOfferingType;
import com.cassandra.rampup.model.Customer;
import com.cassandra.rampup.model.Order;
import com.cassandra.rampup.model.ProductOffering;
import com.cassandra.rampup.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductOfferingService productOfferingService;

    public ResponseEntity<Order> insert(Order order) {
        order.setOrderOfferingId(UUID.randomUUID());
        Customer customer = customerService.getById(order.getCustomerId());
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        order.setOrderOfferingInstanDate(LocalDate.now());
    
        calculateFinalPrice(order);
    
        orderRepository.save(order);
        updateCustomerWithNewOrder(customer, order);
    
        return ResponseEntity.ok(order);
    }
    
    private void calculateFinalPrice(Order order) {
        Set<ProductOfferingType> orderProducts = order.getOrderProducts();
        Double finalPrice = 0.0;
        for (ProductOfferingType productOfferingType : orderProducts) {
            ProductOffering productOffering = productOfferingService.getById(productOfferingType.getId());
            if (productOffering == null) {
                throw new IllegalArgumentException("Product offering not found: " + productOfferingType.getId());
            }
            productOfferingType.setName(productOffering.getProductOfferingName());
            productOfferingType.setPrice(productOffering.getProductOfferingPrice());
            finalPrice += productOfferingType.getPrice();
        }
        finalPrice = finalPrice - order.getOrderOfferingDiscount();
        order.setOrderOfferingTotalPrice(finalPrice);
    }
    
    private void updateCustomerWithNewOrder(Customer customer, Order order) {
        List<UUID> newCustomerOrder = new ArrayList<>();
        newCustomerOrder.add(order.getOrderOfferingId());
        Map<String, Object> customerOrders = new HashMap<>();
        customerOrders.put("customerOrders", newCustomerOrder);
    
        customerService.updateCustomer(customer.getId(), customerOrders);
    }
    

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(UUID id){
        return orderRepository.findById(id).get();
    }

}
