package com.k0rwin.ordermanager.control;

import com.k0rwin.ordermanager.entity.Drink;
import com.k0rwin.ordermanager.entity.Meal;
import com.k0rwin.ordermanager.entity.Order;
import com.k0rwin.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent())
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> openOrders = orderRepository.findAll().stream()
                .filter(Order::isActive).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> postOrder(@RequestBody Order order) {
        order.setActive(true);
        order.setDateTime(LocalDateTime.now());
        Order entity = orderRepository.save(order);
        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/meals")
    public ResponseEntity<HashMap<String, Integer>> getMealSummary() {
        HashMap<String, Integer> summary = new HashMap<>();
        List<Order> openOrders = orderRepository.findAll().stream().filter(Order::isActive).toList();

        for (Order order : openOrders) {
            for (Meal meal : order.getMeals()) {
                //put the new Meal if not exists else add value + 1
                summary.compute(meal.getIdentifier(), (k, v) -> (v == null) ? meal.getAmount() : v + meal.getAmount());
            }
        }

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("/drinks")
    public ResponseEntity<HashMap<String, Integer>> getDrinkSummary() {
        HashMap<String, Integer> summary = new HashMap<>();
        List<Order> openOrders = orderRepository.findAll().stream().filter(Order::isActive).toList();

        for (Order order : openOrders) {
            for (Drink drink : order.getDrinks()) {
                //put the new Drink if not exists else add value + 1
                summary.compute(drink.getIdentifier(), (k, v) -> (v == null) ? drink.getAmount() : v + drink.getAmount());
            }
        }

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
    //TODO endpoint to remove items from order? -> add "served" property to item (later)
}
