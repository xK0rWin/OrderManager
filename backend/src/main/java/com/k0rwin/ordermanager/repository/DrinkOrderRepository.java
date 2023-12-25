package com.k0rwin.ordermanager.repository;

import com.k0rwin.ordermanager.entity.DrinkOrder;
import com.k0rwin.ordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkOrderRepository extends JpaRepository<DrinkOrder, Long> {
    List<DrinkOrder> findAllByOrderById();
    List<DrinkOrder> findAllByOrderByStatusDescIdAsc();
    List<DrinkOrder> findAllByOrderByStatusAscIdAsc();
}
