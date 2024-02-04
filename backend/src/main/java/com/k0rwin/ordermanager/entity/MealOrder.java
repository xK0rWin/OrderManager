package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.util.OrderStatusEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "meal_order")
public class MealOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @Column(name = "special_info")
    private String specialInfo;

    public MealOrder() {
    }

    public MealOrder(OrderStatusEnum status) {
        this.status = status;
    }
}
