package com.k0rwin.ordermanager.control;

import com.k0rwin.ordermanager.entity.Meal;
import com.k0rwin.ordermanager.entity.Order;
import com.k0rwin.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return new ResponseEntity<>(orderRepository.findAll().stream()
                .filter(Order::isActive).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> postOrder(@RequestBody Order order) {
        order.setActive(true);
        order.setDateTime(LocalDateTime.now());
        orderRepository.save(order);
        return ResponseEntity.ok().build();
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

    //TODO: get a summary of all open meals and drinks
    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getMealSummary() {
        List<Order> openOrders =  orderRepository.findAll().stream().filter(Order::isActive).toList();


    }
}
