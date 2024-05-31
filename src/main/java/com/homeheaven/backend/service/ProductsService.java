package com.homeheaven.backend.service;


import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Product addProduct(Product product) {
        return productsRepository.save(product);
    }

    public Product editProduct(Long productId, Product productDetails) {
        Product existingProduct = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setProductDescription(productDetails.getProductDescription());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        existingProduct.setSellerId(productDetails.getSellerId());

        return productsRepository.save(existingProduct);
    }

    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }


    public Iterable<Product> getAllProductsWithStock() {

        Iterable<Product> allProducts = productsRepository.findAll();

        List<Product> productsWithStock = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getStock() > 0) {
                productsWithStock.add(product);
            }
        }

        return productsWithStock;
    }

    public void deleteProduct(Long productId) {
        productsRepository.deleteById(productId);
    }

    public Product getProductById(Long productId) {
        return productsRepository.findById(productId).orElse(null);
    }

    public Iterable<Product> getProductsByCategory(String category) {
        return productsRepository.getProductsByCategory(category);
    }

    public Iterable<Product> searchProducts(String param) {
        return productsRepository.searchProducts(param);
    }
}
