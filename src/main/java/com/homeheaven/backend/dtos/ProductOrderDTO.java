package com.homeheaven.backend.dtos;

import lombok.Data;

@Data
public class ProductOrderDTO {
    private long productOrderId;
    private long productId;
    private int quantity;
    private float price;
}
