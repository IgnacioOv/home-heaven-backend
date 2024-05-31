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
    public ResponseEntity<List<ProductOrderDTO>> getOrdersBySellerId(@PathVariable Long sellerId) {
        List<ProductOrderDTO> productOrders = productOrderService.getOrdersBySellerId(sellerId);
        return new ResponseEntity<>(productOrders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductOrderDTO> addProductOrder(@RequestBody ProductOrder productOrder) {
        ProductOrder newProductOrder = productOrderService.addProductOrder(productOrder);
        ProductOrderDTO productOrderDTO = productOrderService.convertToDTO(newProductOrder);
        return new ResponseEntity<>(productOrderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/recommended")
    public List<Object[]> getRecommendedProducts() {
        return productOrderService.getRecommendedProducts();
    }
}
