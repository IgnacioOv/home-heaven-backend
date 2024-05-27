package com.homeheaven.backend.controller;


import com.homeheaven.backend.entity.Order;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.OrderService;
import com.homeheaven.backend.service.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrderService orderService;
    private final ProductOrderService productOrderService;

//Obtener venta del cliente
    @GetMapping("/client/{userId}")
    public ResponseEntity<Order> getOrderByUserId(@PathVariable int userId) {
        Order order = orderService.getOrderByUserId(userId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestParam Long clientId, @RequestBody List<ProductOrder> productOrders) {
        Order newOrder = orderService.addOrder(clientId, productOrders);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

}
