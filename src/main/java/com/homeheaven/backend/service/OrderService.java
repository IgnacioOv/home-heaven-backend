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
        for(ProductOrder productOrder : productOrders){
            Product item = productsRepository.findById(productOrder.getProduct().getProductId()).orElseThrow(()->new RuntimeException("Producto no encontrado"));
            if (item.getStock() < productOrder.getQuantity()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + item.getProductName());
            }

            // Actualizar el stock del producto
            int newStock = item.getStock() - productOrder.getQuantity();
            item.setStock(newStock);

            // Guardamos los cambios en el item(producto)
            productsRepository.save(item);

            productOrder.setOrder(order);
            productOrder.setPrice(item.getPrice());
            total += productOrder.getQuantity() * item.getPrice();
        }
        productOrderRepository.saveAll(productOrders);
        order.setTotal(total);
        return orderRepository.save(order);
    }


    public List<Order> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
        //add a list of productOrders inside order
    }
}
