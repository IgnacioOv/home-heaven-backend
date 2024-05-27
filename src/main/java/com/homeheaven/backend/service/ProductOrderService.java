package com.homeheaven.backend.service;

import com.homeheaven.backend.entity.ProductOrder;

import com.homeheaven.backend.repository.ProductOrderRepository;

public class ProductOrderService {

    private ProductOrderRepository productOrderRepository;

    public ProductOrder addProductOrder(ProductOrder productOrder){
        return productOrderRepository.save(productOrder);
    }



}
