package com.homeheaven.backend.controller;


import com.homeheaven.backend.entity.Product;
import com.homeheaven.backend.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductsController {

    private final ProductsService productsService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productsService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<Product> editProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
        Product updatedProduct = productsService.editProduct(productId, productDetails);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productsService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productsService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<Product>> getProductsByCategory(@PathVariable String category) {
        Iterable<Product> products = productsService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<Iterable<Product>> searchProducts(@PathVariable String param) {
        Iterable<Product> products = productsService.searchProducts(param);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/stock")
    public ResponseEntity<Iterable<Product>> getAllProductsWithStock() {
        Iterable<Product> products = productsService.getAllProductsWithStock();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}

