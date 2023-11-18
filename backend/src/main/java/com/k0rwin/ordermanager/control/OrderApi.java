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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private HashMap<String, Double> meals = new HashMap<String, Double>();
    private HashMap<String, Double> drinks = new HashMap<String, Double>();
    private final List<SseEmitter> emitters = new ArrayList<>();
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

    @GetMapping(value = "/sse", produces = "text/event-stream")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter(-1L);
        emitters.add(emitter);

        // Remove the emitter when the client disconnects
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    public void sendSseEvent(String message) {
        Iterator<SseEmitter> iterator = emitters.iterator();
        while (iterator.hasNext()) {
            SseEmitter emitter = iterator.next();
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                iterator.remove();
            }
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent())
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/desc", produces = "application/json")
    public ResponseEntity<List<Order>> getOrdersSortedByStatusDesc() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusDescIdAsc().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/asc", produces = "application/json")
    public ResponseEntity<List<Order>> getOrdersSortedByStatusAsc() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() != OrderStatusEnum.DELIVERED).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/delivered", produces = "application/json")
    public ResponseEntity<List<Order>> getDeliveredOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.DELIVERED).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/ready", produces = "application/json")
    public ResponseEntity<List<Order>> getReadyOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.READY).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/open", produces = "application/json")
    public ResponseEntity<List<Order>> getOpenOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.OPEN).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Long> postOrder(@RequestBody Order order) {
        order.setStatus(OrderStatusEnum.OPEN);
        order.setDateTime(LocalDateTime.now());
        Order entity = orderRepository.save(order);
        sendSseEvent("new order created");
        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/{status}", produces = "application/json")
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

    @DeleteMapping(value = "/{id}", produces = "application/json")
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

    @GetMapping("/mealonly/open")
    public ResponseEntity<List<Order>> getOrdersMealOnlyOpen() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.OPEN)
                .filter(order -> !order.getMeals().isEmpty())
                .peek(order -> order.getDrinks().clear())
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/drinkonly/open")
    public ResponseEntity<List<Order>> getOrdersDrinkOnlyOpen() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.OPEN)
                .filter(order -> !order.getDrinks().isEmpty())
                .peek(order -> order.getMeals().clear())
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/mealonly/ready")
    public ResponseEntity<List<Order>> getOrdersMealOnlyReady() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.READY)
                .filter(order -> !order.getMeals().isEmpty())
                .peek(order -> order.getDrinks().clear())
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/drinkonly/ready")
    public ResponseEntity<List<Order>> getOrdersDrinkOnlyReady() {
        List<Order> openOrders = orderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.READY)
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
}
