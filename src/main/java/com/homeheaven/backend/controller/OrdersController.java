package com.homeheaven.backend.controller;

import com.homeheaven.backend.dtos.OrderDTO;
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

    @GetMapping("/{buyerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByBuyerId(@PathVariable Long buyerId) {
        List<OrderDTO> orders = orderService.getOrdersByBuyerId(buyerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestParam Long clientId, @RequestBody List<ProductOrder> productOrders) {
        OrderDTO newOrder = orderService.addOrder(clientId, productOrders);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
