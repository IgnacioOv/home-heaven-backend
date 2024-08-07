package com.homeheaven.backend.controller;

import com.homeheaven.backend.dtos.AddOrderDto;
import com.homeheaven.backend.dtos.OrderDTO;
import com.homeheaven.backend.exceptions.InsufficientStockException;
import com.homeheaven.backend.exceptions.ProductNotFoundException;
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
    public ResponseEntity<Object> getOrdersByBuyerId(@PathVariable Long buyerId) {

        try {
            List<OrderDTO> orders = orderService.getOrdersByBuyerId(buyerId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addOrder(@RequestBody AddOrderDto addOrderDto) {
        try {
            return orderService.addOrder(addOrderDto);
        } catch (ProductNotFoundException | InsufficientStockException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDTO order = orderService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


}
