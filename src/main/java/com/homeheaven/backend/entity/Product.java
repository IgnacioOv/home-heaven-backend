package com.homeheaven.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "category")
    private String category;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private float price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "seller_id")
    private long sellerId;

    @OneToOne
    @JoinColumn(name = "productOrderId", nullable = false)
    private ProductOrder productOrder;

}
