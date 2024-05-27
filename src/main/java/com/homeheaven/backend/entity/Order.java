package com.homeheaven.backend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")

public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "buyer_id")
    private long buyerId;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrder;

}
