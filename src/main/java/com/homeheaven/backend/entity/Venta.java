package com.homeheaven.backend.entity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Venta {
    private int id;
    private int buyerId;
    private int sellerId;
    private int productId;
    private int quantity;
    private double total;
}
