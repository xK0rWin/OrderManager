package com.k0rwin.ordermanager;

import com.k0rwin.ordermanager.entity.Coke;
import com.k0rwin.ordermanager.entity.Order;
import com.k0rwin.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestData {

    @Autowired
    OrderRepository orderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initTestData() {
        Order order = new Order(LocalDateTime.now(), true);
        Coke coke = new Coke(order);
        order.getDrinks().add(coke);
        orderRepository.save(order);
    }
}
