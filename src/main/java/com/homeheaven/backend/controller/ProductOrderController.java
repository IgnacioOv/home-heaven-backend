package com.homeheaven.backend.controller;

import com.homeheaven.backend.dtos.ProductOrderDTO;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.service.ProductOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("productOrder")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @GetMapping("/{sellerId}")
    public ResponseEntity<Object> getOrdersBySellerId(@PathVariable Long sellerId) {
        try {
            List<ProductOrderDTO> productOrders = productOrderService.getOrdersBySellerId(sellerId);
            return new ResponseEntity<>(productOrders, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProductOrder(@RequestBody ProductOrder productOrder) {
        try {
            ProductOrder newProductOrder = productOrderService.addProductOrder(productOrder);
            ProductOrderDTO productOrderDTO = productOrderService.convertToDTO(newProductOrder);
            return new ResponseEntity<>(productOrderDTO, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recommended")
    public ResponseEntity<Object> getRecommendedProducts() {
        try {
            List<Object[]> recommended = productOrderService.getRecommendedProducts();
            return new ResponseEntity<>(recommended, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
