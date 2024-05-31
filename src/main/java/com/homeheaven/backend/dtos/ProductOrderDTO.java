package com.homeheaven.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductOrderDTO {
    private Long productOrderId;
    private Long productId;
    private int quantity;
    private double price;
}
