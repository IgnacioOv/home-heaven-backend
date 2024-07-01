package com.homeheaven.backend.dtos;


import com.homeheaven.backend.entity.ProductOrder;
import lombok.Data;

import java.util.List;

@Data
public class AddOrderDto {
    int buyerId;
    List<ProductOrder> productOrders;
}
