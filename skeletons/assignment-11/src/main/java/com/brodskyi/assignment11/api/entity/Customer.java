package com.brodskyi.assignment11.api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "discount_rate")
    private BigDecimal discountRate = BigDecimal.ZERO;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    @Transient
    public boolean isVIPCustomer() {

        Timestamp now = Timestamp.from(Instant.now());
        Timestamp oneYearAgo = Timestamp.valueOf(now.toLocalDateTime().minusYears(1));
        System.out.println(oneYearAgo);
        System.out.println(orders.size());

        BigDecimal totalPurchases = orders.stream()
                .filter(order -> {
                    System.out.println(order.getCreatedAt());
                    return order.getCreatedAt().after(oneYearAgo);
                })
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(totalPurchases);

        this.updateDiscountRate(totalPurchases);

        return totalPurchases.compareTo(BigDecimal.valueOf(1000)) > 0;
    }

    public void updateDiscountRate(BigDecimal totalOrderValue) {
        if (totalOrderValue.compareTo(BigDecimal.valueOf(1000)) >= 0 &&
                totalOrderValue.compareTo(BigDecimal.valueOf(5000)) < 0) {
            this.discountRate = BigDecimal.valueOf(0.05);
        } else if (totalOrderValue.compareTo(BigDecimal.valueOf(5000)) >= 0) {
            this.discountRate = BigDecimal.valueOf(0.1);
        } else {
            this.discountRate = BigDecimal.ZERO;
        }
    }

}
