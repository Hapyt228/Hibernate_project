package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "discount")
    private float discount;

    @CreationTimestamp
    @Column(name = "expiration", nullable = false)
    private LocalDateTime expirationDate;

    @ManyToMany(mappedBy = "coupons")
    private List<Client> clients;

    public Coupon(String code, float discount) {
        this.code = code;
        this.discount = discount;
    }

    public Coupon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public LocalDateTime getExpiration_date() {
        return expirationDate;
    }

    public void setExpiration_date(LocalDateTime expiration_date) {
        this.expirationDate = expiration_date;
    }
}
