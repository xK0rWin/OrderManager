package com.k0rwin.ordermanager.repository;

import com.k0rwin.ordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
