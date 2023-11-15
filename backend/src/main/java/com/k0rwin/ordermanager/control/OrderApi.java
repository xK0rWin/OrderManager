package com.k0rwin.ordermanager.control;

import com.k0rwin.ordermanager.entity.Drink;
import com.k0rwin.ordermanager.entity.Meal;
import com.k0rwin.ordermanager.entity.Order;
import com.k0rwin.ordermanager.repository.OrderRepository;
import com.k0rwin.ordermanager.util.ClassScanner;
import com.k0rwin.ordermanager.util.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private HashMap<String, Double> meals = new HashMap<String, Double>();
    private HashMap<String, Double> drinks = new HashMap<String, Double>();
    private final SseEmitter emitter = new SseEmitter(-1L);
    @Autowired
    OrderRepository orderRepository;

    @EventListener(ApplicationReadyEvent.class)
    protected void init() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Class<?>> entityClasses = ClassScanner.getClasses("com.k0rwin.ordermanager.entity");

        for (Class<?> clazz : entityClasses) {
            if (clazz.getSuperclass().equals(Meal.class)) {
                Meal meal = (Meal) clazz.getDeclaredConstructor().newInstance();
                meals.put(clazz.getSimpleName(), meal.getPrice());
            } else if (clazz.getSuperclass().equals(Drink.class)) {
                Drink drink = (Drink) clazz.getDeclaredConstructor().newInstance();
                drinks.put(clazz.getSimpleName(), drink.getPrice());
            }
        }
    }

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        return emitter;
    }

    public void sendSseEvent(String message) {
        try {
            emitter.send(SseEmitter.event().data(message));
        } catch (Exception e) {
            emitter.complete();
        }
    }

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
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> postOrder(@RequestBody Order order) {
        order.setStatus(OrderStatusEnum.OPEN);
        order.setDateTime(LocalDateTime.now());
        Order entity = orderRepository.save(order);
        sendSseEvent("new order created");
        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @PathVariable OrderStatusEnum status) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            order.get().setStatus(status);
            orderRepository.save(order.get());
            sendSseEvent("order status updated");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        List<Order> openOrders = orderRepository.findAll().stream().filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).toList();

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
        List<Order> openOrders = orderRepository.findAll().stream().filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).toList();

        for (Order order : openOrders) {
            for (Drink drink : order.getDrinks()) {
                //put the new Drink if not exists else add value + 1
                summary.compute(drink.getIdentifier(), (k, v) -> (v == null) ? drink.getAmount() : v + drink.getAmount());
            }
        }

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("/mealonly")
    public ResponseEntity<List<Order>> getOrdersMealOnly() {
        List<Order> openOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED)
                .filter(order -> !order.getMeals().isEmpty())
                .peek(order -> order.getDrinks().clear())
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/drinkonly")
    public ResponseEntity<List<Order>> getOrdersDrinkOnly() {
        List<Order> openOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED)
                .filter(order -> !order.getDrinks().isEmpty())
                .peek(order -> order.getMeals().clear())
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/list/meals")
    public ResponseEntity<HashMap<String, Double>> getAllMealNames() {
        return new ResponseEntity<>(this.meals, HttpStatus.OK);
    }

    @GetMapping("/list/drinks")
    public ResponseEntity<HashMap<String, Double>> getAllDrinkNames() {
        return new ResponseEntity<>(this.drinks, HttpStatus.OK);
    }

    @GetMapping("/waiter/{name}")
    public ResponseEntity<List<Order>> getOrdersByWaiter(@PathVariable String name) {

        List<Order> openOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED)
                .filter(order -> order.getWaiter().equals(name))
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    //TODO endpoint to remove items from order? -> add "served" property to item (later)
}
