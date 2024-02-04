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
@Table(name = "drink_order")
public class DrinkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drink> drinks = new ArrayList<>();
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @Column(name = "special_info")
    private String specialInfo;

    public DrinkOrder() {
    }

    public DrinkOrder(OrderStatusEnum status) {
        this.status = status;
    }
}
