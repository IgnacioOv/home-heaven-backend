package com.homeheaven.backend.repository;

import com.homeheaven.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.productOrders WHERE o.buyerId = ?1")
    List<Order> findByBuyerId(Long buyerId);
}
