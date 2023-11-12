package com.k0rwin.ordermanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_entity")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks = new ArrayList<>();
    @Column(name = "time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "active")
    private boolean active;

    public Order() {
    }

    public Order(LocalDateTime dateTime, boolean active) {
        this.dateTime = dateTime;
        this.active = active;
    }
}
