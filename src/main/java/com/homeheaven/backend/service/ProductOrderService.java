package com.homeheaven.backend.service;

import com.homeheaven.backend.dtos.ProductOrderDTO;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.repository.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    public ProductOrder addProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    public List<ProductOrderDTO> getOrdersBySellerId(Long sellerId) {
        List<ProductOrder> productOrders = productOrderRepository.findBySellerId(sellerId);
        return productOrders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<Object[]> getRecommendedProducts() {
        return productOrderRepository.getRecommendedProducts();
    }

    public ProductOrderDTO convertToDTO(ProductOrder productOrder) {
        return new ProductOrderDTO(
                productOrder.getProductOrderId(),
                productOrder.getProduct().getProductId(),
                productOrder.getQuantity(),
                productOrder.getPrice()
        );
    }
}
