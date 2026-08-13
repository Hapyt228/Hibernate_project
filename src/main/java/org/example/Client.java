package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "registration", nullable = false)
    private LocalDateTime registrationDate;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "CLIENT_COUPONS",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    )
    private List<Coupon> coupons;

    public Client(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Client(String name, String email,Profile profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistration_date() {
        return registrationDate;
    }

    public void setRegistration_date(LocalDateTime registration_date) {
        this.registrationDate = registration_date;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registration_date=" + registrationDate +
                '}';
    }
}
