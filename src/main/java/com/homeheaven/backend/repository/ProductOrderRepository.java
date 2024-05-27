package com.homeheaven.backend.repository;
import com.homeheaven.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homeheaven.backend.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query ("SELECT quantity * price AS total FROM product_order")
    double calcularSubtotal(long productId);



}
