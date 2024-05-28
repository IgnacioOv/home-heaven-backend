package com.homeheaven.backend.service;

import com.homeheaven.backend.entity.Order;
import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.repository.OrderRepository;
import com.homeheaven.backend.repository.ProductOrderRepository;
import com.homeheaven.backend.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Getter
@Setter
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;
    private final ProductOrderRepository productOrderRepository;





    @Transactional
    public Order addOrder(long clientId, List<ProductOrder>productOrders){
        Order order = new Order();
        order.setBuyerId(clientId);
        order = orderRepository.save(order);
        double total = 0.0;
        for(ProductOrder product : productOrders){
            Product item = productsRepository.findById(product.getProduct().getProductId()).orElseThrow(()->new RuntimeException("Producto no encontrado"));
            product.setOrder(order);
            product.setPrice(item.getPrice());
            total += product.getQuantity() * item.getPrice();
        }
        productOrderRepository.saveAll(productOrders);
        order.setTotal(total);
        return orderRepository.save(order);
    }


    public List<Order> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }
}
