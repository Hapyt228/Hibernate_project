package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(name = "orders")
        private LocalDateTime orderDate;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @Column(name = "status")
    private String status;

   @ManyToOne
    private Client client;

    public Order(BigDecimal totalAmount, String status) {
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(BigDecimal totalAmount, String status, Client client) {
        this.totalAmount = totalAmount;
        this.status = status;
        this.client = client;
    }

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
