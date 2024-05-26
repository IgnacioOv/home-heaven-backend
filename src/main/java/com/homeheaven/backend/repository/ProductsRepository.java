package com.homeheaven.backend.repository;


import com.homeheaven.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    Iterable<Product> getProductsByCategory(String category);


    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    Iterable<Product> searchProducts(String param);
}
