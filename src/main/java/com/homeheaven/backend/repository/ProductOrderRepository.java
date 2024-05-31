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
    List<ProductOrder> findBySellerId(Long sellerId);

    @Query("SELECT po.product.productId AS productId, SUM(po.quantity) AS totalQuantity " +
            "FROM ProductOrder po " +
            "WHERE po.product.sellerId = ?1 " +
            "GROUP BY po.product.productId " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTop3ProductsByTotalQuantity(Long sellerId);
}
