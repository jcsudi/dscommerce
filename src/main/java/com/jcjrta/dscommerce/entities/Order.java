package com.jcjrta.dscommerce.entities;

import jakarta.persistence.*;
import jdk.jfr.Label;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant momemt;
    private OrderStatus staus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();
    public Order(){
    }

    public Order(Long id, Instant momemt, OrderStatus staus) {
        this.id = id;
        this.momemt = momemt;
        this.staus = staus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMomemt() {
        return momemt;
    }

    public void setMomemt(Instant momemt) {
        this.momemt = momemt;
    }

    public OrderStatus getStaus() {
        return staus;
    }

    public void setStaus(OrderStatus staus) {
        this.staus = staus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
