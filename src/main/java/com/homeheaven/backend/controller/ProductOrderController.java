package com.homeheaven.backend.controller;

import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController

@RequestMapping("productOrder")
public class ProductOrderController {

    private ProductOrderService productOrderService;

    @GetMapping("/{sellerId}")
    public ResponseEntity<ProductOrder> getOrdersBySellerId(@PathVariable Long sellerId) {
        ProductOrder productOrder = productOrderService.getOrdersBySellerId(sellerId);
        return new ResponseEntity<>(productOrder, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductOrder> addProductOrder(@RequestBody ProductOrder productOrder) {
        ProductOrder newProduct = productOrderService.addProductOrder(productOrder);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

}
