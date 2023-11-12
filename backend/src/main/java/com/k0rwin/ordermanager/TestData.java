package com.k0rwin.ordermanager;

import com.k0rwin.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TestData {

    @Autowired
    OrderRepository orderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initTestData() {
//        Order order = new Order(1, LocalDateTime.now(), true);
//        Coke coke = new Coke(order, 2);
//        orderRepository.save(order);
    }
}
