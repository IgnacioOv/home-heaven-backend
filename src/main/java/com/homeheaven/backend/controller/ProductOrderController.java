package com.homeheaven.backend.controller;
import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.OrderService;
import com.homeheaven.backend.service.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
