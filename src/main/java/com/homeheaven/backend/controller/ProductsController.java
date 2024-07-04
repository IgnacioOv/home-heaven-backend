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
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        try {
            Product newProduct = productsService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<Object> editProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productsService.editProduct(productId, productDetails);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        try {
            Iterable<Product> products = productsService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        try {
            productsService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) {
        try {
            Product product = productsService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable String category) {
        try {
            Iterable<Product> products = productsService.getProductsByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<Object> searchProducts(@PathVariable String param) {
        try {
            Iterable<Product> products = productsService.searchProducts(param);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<Object> getAllProductsWithStock() {
        try {
            Iterable<Product> products = productsService.getAllProductsWithStock();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

