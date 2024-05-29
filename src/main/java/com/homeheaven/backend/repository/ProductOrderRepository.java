package com.homeheaven.backend.repository;

import com.homeheaven.backend.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

//    @Query("SELECT po FROM ProductOrder po WHERE po.seller_id = ?1")
//    ProductOrder FindBySellerId(Long sellerId);

    @Query("SELECT po FROM ProductOrder po " +
            "JOIN po.product p " +
            "WHERE p.sellerId = ?1")
    ProductOrder FindBySellerId(Long sellerId);

}
