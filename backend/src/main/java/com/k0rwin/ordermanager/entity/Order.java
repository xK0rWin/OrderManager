package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.util.OrderStatusEnum;
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
    @Column(name = "table_nr", nullable = false)
    private Integer tableNumber;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks = new ArrayList<>();
    @Column(name = "time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @Column(name = "waiter", nullable = false)
    private String waiter;

    public Order() {
    }

    public Order(Integer tableNumber ,LocalDateTime dateTime, OrderStatusEnum status, String waiter) {
        this.tableNumber = tableNumber;
        this.dateTime = dateTime;
        this.status = status;
        this.waiter = waiter;
    }
}
