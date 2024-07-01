package com.homeheaven.backend.service;

import com.homeheaven.backend.dtos.AddOrderDto;
import com.homeheaven.backend.dtos.OrderDTO;
import com.homeheaven.backend.dtos.ProductOrderDTO;
import com.homeheaven.backend.entity.Order;
import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.entity.ProductOrder;
import com.homeheaven.backend.exceptions.InsufficientStockException;
import com.homeheaven.backend.exceptions.ProductNotFoundException;
import com.homeheaven.backend.repository.OrderRepository;
import com.homeheaven.backend.repository.ProductOrderRepository;
import com.homeheaven.backend.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;
    private final ProductOrderRepository productOrderRepository;

    @Transactional
    public ResponseEntity<Object> addOrder(AddOrderDto addOrderDto) {
        try {
            List<ProductOrder> productOrders = addOrderDto.getProductOrders();
            Order order = new Order();
            order.setBuyerId(addOrderDto.getBuyerId());
            double total = 0.0;
            for (ProductOrder productOrder : productOrders) {
                Product product = productsRepository.findById(productOrder.getProduct().getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                if (product.getStock() < productOrder.getQuantity()) {
                    throw new InsufficientStockException("Not enough stock for product: " + product.getProductName());
                }
                order = orderRepository.save(order);
                int newStock = product.getStock() - productOrder.getQuantity();
                product.setStock(newStock);
                productsRepository.save(product);

                productOrder.setOrder(order);
                productOrder.setPrice(product.getPrice());
                total += productOrder.getQuantity() * product.getPrice();
            }
            productOrderRepository.saveAll(productOrders);
            order.setTotal(total);
            orderRepository.save(order);

            return new ResponseEntity<>(convertToDTO(order, productOrders), HttpStatus.OK);

        } catch (ProductNotFoundException | InsufficientStockException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<OrderDTO> getOrdersByBuyerId(Long buyerId) {
        List<Order> orders = orderRepository.findByBuyerId(buyerId);
        return orders.stream()
                .map(order -> convertToDTO(order, productOrderRepository.findByOrderId(order.getOrderId())))
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order, List<ProductOrder> productOrders) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setBuyerId(order.getBuyerId());
        orderDTO.setTotal(order.getTotal());

        List<ProductOrderDTO> productOrderDTOs = productOrders.stream().map(po -> new ProductOrderDTO(
                po.getProductOrderId(),
                po.getProduct().getProductId(),
                po.getQuantity(),
                po.getPrice()
        )).collect(Collectors.toList());

        orderDTO.setProductOrders(productOrderDTOs);

        return orderDTO;
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<ProductOrder> productOrders = productOrderRepository.findByOrderId(orderId);
        return convertToDTO(order, productOrders);
    }
}
