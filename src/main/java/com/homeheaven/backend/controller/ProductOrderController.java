package com.homeheaven.backend.controller;

import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController

@RequestMapping("productOrder")
public class ProductOrderController {
    private ProductOrderService productOrderService;

    @PostMapping("/add")
    public ResponseEntity<ProductOrder> addProductOrder(@RequestBody ProductOrder productOrder) {
        ProductOrder newProduct = productOrderService.addProductOrder(productOrder);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

}
