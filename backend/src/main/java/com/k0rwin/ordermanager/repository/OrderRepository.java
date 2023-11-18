package com.k0rwin.ordermanager.repository;

import com.k0rwin.ordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderById();
    List<Order> findAllByOrderByStatusDescIdAsc();
    List<Order> findAllByOrderByStatusAscIdAsc();
}
