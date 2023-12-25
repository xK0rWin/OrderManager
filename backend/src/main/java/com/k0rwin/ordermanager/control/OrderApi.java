package com.k0rwin.ordermanager.control;

import com.k0rwin.ordermanager.entity.*;
import com.k0rwin.ordermanager.repository.DrinkOrderRepository;
import com.k0rwin.ordermanager.repository.MealOrderRepository;
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
    @Autowired
    MealOrderRepository mealOrderRepository;
    @Autowired
    DrinkOrderRepository drinkOrderRepository;

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
                .filter(order ->
                        (order.getMealOrder().getStatus() != OrderStatusEnum.DELIVERED && order.getDrinkOrder().getStatus() != OrderStatusEnum.DELIVERED)
                ).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/desc", produces = "application/json")
    public ResponseEntity<List<Order>> getOrdersSortedByStatusDesc() {
        List<MealOrder> openMealOrders = mealOrderRepository.findAllByOrderByStatusDescIdAsc().stream()
                .filter(order ->
                        (order.getStatus() != OrderStatusEnum.DELIVERED)
                ).toList();
        List<DrinkOrder> openDrinkOrders = drinkOrderRepository.findAllByOrderByStatusDescIdAsc().stream()
                .filter(order ->
                        (order.getStatus() != OrderStatusEnum.DELIVERED)
                ).toList();
        Set<Order> openOrders = new HashSet<>();
        openOrders.addAll(openMealOrders.stream().map(mealOrder -> orderRepository.findByMealOrderById(mealOrder.getId()).orElseThrow()).toList());
        openOrders.addAll(openDrinkOrders.stream().map(drinkOrder -> orderRepository.findByDrinkOrderById(drinkOrder.getId()).orElseThrow()).toList());

        return new ResponseEntity<>(List.copyOf(openOrders), HttpStatus.OK);
    }

    @GetMapping(value = "/asc", produces = "application/json")
    public ResponseEntity<List<Order>> getOrdersSortedByStatusAsc() {
        List<MealOrder> openMealOrders = mealOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order ->
                        (order.getStatus() != OrderStatusEnum.DELIVERED)
                ).toList();
        List<DrinkOrder> openDrinkOrders = drinkOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order ->
                        (order.getStatus() != OrderStatusEnum.DELIVERED)
                ).toList();
        Set<Order> openOrders = new HashSet<>();
        openOrders.addAll(openMealOrders.stream().map(mealOrder -> orderRepository.findByMealOrderById(mealOrder.getId()).orElseThrow()).toList());
        openOrders.addAll(openDrinkOrders.stream().map(drinkOrder -> orderRepository.findByDrinkOrderById(drinkOrder.getId()).orElseThrow()).toList());

        return new ResponseEntity<>(List.copyOf(openOrders), HttpStatus.OK);
    }

    @GetMapping(value = "/delivered", produces = "application/json")
    public ResponseEntity<List<Order>> getDeliveredOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order ->
                        (order.getMealOrder().getStatus() == OrderStatusEnum.DELIVERED && order.getDrinkOrder().getStatus() == OrderStatusEnum.DELIVERED)
                ).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/ready", produces = "application/json")
    public ResponseEntity<List<Order>> getReadyOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order ->
                        (order.getMealOrder().getStatus() == OrderStatusEnum.READY || order.getDrinkOrder().getStatus() == OrderStatusEnum.READY)
                ).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/open", produces = "application/json")
    public ResponseEntity<List<Order>> getOpenOrders() {
        List<Order> openOrders = orderRepository.findAllByOrderById().stream()
                .filter(order ->
                        (order.getMealOrder().getStatus() == OrderStatusEnum.OPEN || order.getDrinkOrder().getStatus() == OrderStatusEnum.OPEN)
                ).collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Long> postOrder(@RequestBody Order order) {
        order.getMealOrder().setStatus(OrderStatusEnum.OPEN);
        order.getDrinkOrder().setStatus(OrderStatusEnum.OPEN);
        order.setDateTime(LocalDateTime.now());
        Order entity = orderRepository.save(order);
        sendSseEvent("new order created");
        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/mealstatus/{status}", produces = "application/json")
    public ResponseEntity<Void> updateMealStatus(@PathVariable Long id, @PathVariable OrderStatusEnum status) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            order.get().getMealOrder().setStatus(status);
            orderRepository.save(order.get());
            sendSseEvent("order meal status updated");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/drinkstatus/{status}", produces = "application/json")
    public ResponseEntity<Void> updateDrinkStatus(@PathVariable Long id, @PathVariable OrderStatusEnum status) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            order.get().getDrinkOrder().setStatus(status);
            orderRepository.save(order.get());
            sendSseEvent("order drink status updated");
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
        List<Order> openOrders = orderRepository.findAll().stream().filter(order -> order.getMealOrder().getStatus() != OrderStatusEnum.DELIVERED).toList();

        for (Order order : openOrders) {
            for (Meal meal : order.getMealOrder().getMeals()) {
                //put the new Meal if not exists else add value + 1
                summary.compute(meal.getIdentifier(), (k, v) -> (v == null) ? meal.getAmount() : v + meal.getAmount());
            }
        }

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("/drinks")
    public ResponseEntity<HashMap<String, Integer>> getDrinkSummary() {
        HashMap<String, Integer> summary = new HashMap<>();
        List<Order> openOrders = orderRepository.findAll().stream().filter(order -> order.getDrinkOrder().getStatus() != OrderStatusEnum.DELIVERED).toList();

        for (Order order : openOrders) {
            for (Drink drink : order.getDrinkOrder().getDrinks()) {
                //put the new Drink if not exists else add value + 1
                summary.compute(drink.getIdentifier(), (k, v) -> (v == null) ? drink.getAmount() : v + drink.getAmount());
            }
        }

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("/mealonly/open")
    public ResponseEntity<List<Order>> getOrdersMealOnlyOpen() {
        List<Order> openOrders = mealOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.OPEN)
                .filter(order -> !order.getMeals().isEmpty())
                .map(mealOrder -> orderRepository.findByMealOrderById(mealOrder.getId()).orElseThrow())
                .peek((order -> order.getDrinkOrder().getDrinks().clear()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/drinkonly/open")
    public ResponseEntity<List<Order>> getOrdersDrinkOnlyOpen() {
        List<Order> openOrders = drinkOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.OPEN)
                .filter(order -> !order.getDrinks().isEmpty())
                .map(drinkOrder -> orderRepository.findByDrinkOrderById(drinkOrder.getId()).orElseThrow())
                .peek((order -> order.getMealOrder().getMeals().clear()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/mealonly/ready")
    public ResponseEntity<List<Order>> getOrdersMealOnlyReady() {
        List<Order> openOrders = mealOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.READY)
                .filter(order -> !order.getMeals().isEmpty())
                .map(mealOrder -> orderRepository.findByMealOrderById(mealOrder.getId()).orElseThrow())
                .peek((order -> order.getDrinkOrder().getDrinks().clear()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }

    @GetMapping("/drinkonly/ready")
    public ResponseEntity<List<Order>> getOrdersDrinkOnlyReady() {
        List<Order> openOrders = drinkOrderRepository.findAllByOrderByStatusAscIdAsc().stream()
                .filter(order -> order.getStatus() == OrderStatusEnum.READY)
                .filter(order -> !order.getDrinks().isEmpty())
                .map(drinkOrder -> orderRepository.findByDrinkOrderById(drinkOrder.getId()).orElseThrow())
                .peek((order -> order.getMealOrder().getMeals().clear()))
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
                .filter(order ->
                        (order.getMealOrder().getStatus() != OrderStatusEnum.DELIVERED && order.getDrinkOrder().getStatus() != OrderStatusEnum.DELIVERED)
                )
                .filter(order -> order.getWaiter().equals(name))
                .collect(Collectors.toList());
        return new ResponseEntity<>(openOrders, HttpStatus.OK);
    }
}
