package com.k0rwin.ordermanager.repository;

import com.k0rwin.ordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderById();
    @Query("SELECT o FROM Order o WHERE o.drinkOrder.id = :id")
    Optional<Order> findByMealOrderById(@Param("id") Long id);
    @Query("SELECT o FROM Order o WHERE o.mealOrder.id = :id")
    Optional<Order> findByDrinkOrderById(@Param("id") Long id);
}
