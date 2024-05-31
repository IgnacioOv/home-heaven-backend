package com.homeheaven.backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private long orderId;
    private long buyerId;
    private double total;
    private List<ProductOrderDTO> productOrders;
}
