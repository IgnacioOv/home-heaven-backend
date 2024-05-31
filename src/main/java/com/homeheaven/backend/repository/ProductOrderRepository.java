package com.homeheaven.backend.repository;

import com.homeheaven.backend.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query("SELECT po FROM ProductOrder po WHERE po.order.orderId = ?1")
    List<ProductOrder> findByOrderId(Long orderId);

    @Query("SELECT po FROM ProductOrder po " +
            "JOIN po.product p " +
            "WHERE p.sellerId = ?1")
    ProductOrder FindBySellerId(Long sellerId);

    @Query("SELECT po.product, SUM(po.quantity) AS total_quantity " +
            "FROM ProductOrder po " +
            "GROUP BY po.product " +
            "ORDER BY total_quantity DESC")
    List<Object[]> findTop3ProductsByTotalQuantity();
}
