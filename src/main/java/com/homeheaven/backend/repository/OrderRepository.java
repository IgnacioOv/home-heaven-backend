package com.homeheaven.backend.repository;


import com.homeheaven.backend.entity.Order;
import com.homeheaven.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM orders o WHERE o.buyer_id = ?1")
    Order getOrderByUserId(int userId);

   // @Query("SELECT os FROM orders os WHERE os.seller_id = ?1")
   // Order getOrderBySellerId(int sellerId);


}
