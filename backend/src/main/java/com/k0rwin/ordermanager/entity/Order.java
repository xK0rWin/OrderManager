package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.util.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private MealOrder mealOrder;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private DrinkOrder drinkOrder;
    @Column(name = "time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "waiter", nullable = false)
    private String waiter;

    public Order() {
    }

    public Order(Integer tableNumber , MealOrder mealOrder, DrinkOrder drinkOrder, LocalDateTime dateTime, String waiter) {
        this.tableNumber = tableNumber;
        this.drinkOrder = drinkOrder;
        this.mealOrder = mealOrder;
        this.dateTime = dateTime;
        this.waiter = waiter;
    }
}
