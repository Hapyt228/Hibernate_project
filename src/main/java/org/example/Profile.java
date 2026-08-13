package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id")
    private Client client;

    public Profile(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public Profile() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
