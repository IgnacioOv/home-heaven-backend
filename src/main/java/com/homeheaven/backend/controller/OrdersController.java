package com.homeheaven.backend.controller;


import com.homeheaven.backend.entity.Order;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestParam Long clientId, @RequestBody List<ProductOrder> productOrders) {
        Order newOrder = orderService.addOrder(clientId, productOrders);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

}
