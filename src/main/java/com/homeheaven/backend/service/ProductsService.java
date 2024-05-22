package com.homeheaven.backend.service;


import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;


    public Product addProduct(Product product) {
        return productsRepository.save(product);
    }


    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public void deleteProduct(int productId) {
        productsRepository.deleteById(productId);
    }


    public Product getProductById(int productId) {
        return productsRepository.findById(productId).orElse(null);
    }
}
