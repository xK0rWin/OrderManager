package com.k0rwin.ordermanager.control;

import com.k0rwin.ordermanager.entity.Drink;
import com.k0rwin.ordermanager.entity.Meal;
import com.k0rwin.ordermanager.entity.Order;
import com.k0rwin.ordermanager.entity.statistics.Statistics;
import com.k0rwin.ordermanager.repository.DrinkOrderRepository;
import com.k0rwin.ordermanager.repository.MealOrderRepository;
import com.k0rwin.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistics")
public class StatisticsApi {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MealOrderRepository mealOrderRepository;
    @Autowired
    DrinkOrderRepository drinkOrderRepository;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Statistics> getOrder(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Order> ordersOfDay = orderRepository.findAll().stream()
                .filter(
                        //from 6am to 6am of next day counts as orders for the date
                        order -> order.getDateTime().minusHours(6).toLocalDate().toEpochDay() == date.toEpochDay()
                ).toList();

        HashMap<String, Integer> salesPerItem = new HashMap<>();
        HashMap<String, Double> revenuePerItem = new HashMap<>();
        HashMap<String, Double> revenuePerWaiter = new HashMap<>();
        HashMap<String, Integer> ordersPerWaiter = new HashMap<>();

        for (Order order : ordersOfDay) {
            for (Meal meal : order.getMealOrder().getMeals()) {
                if (!salesPerItem.containsKey(meal.getIdentifier())) {
                    salesPerItem.put(meal.getIdentifier(), 0);
                    revenuePerItem.put(meal.getIdentifier(), 0.0);
                }
                salesPerItem.put(meal.getIdentifier(), salesPerItem.get(meal.getIdentifier()) + meal.getAmount());
                revenuePerItem.put(meal.getIdentifier(), salesPerItem.get(meal.getIdentifier()) + (meal.getAmount() * meal.getPrice()));
            }

            for (Drink drink : order.getDrinkOrder().getDrinks()) {
                if (!salesPerItem.containsKey(drink.getIdentifier())) {
                    salesPerItem.put(drink.getIdentifier(), 0);
                    revenuePerItem.put(drink.getIdentifier(), 0.0);
                }
                salesPerItem.put(drink.getIdentifier(), salesPerItem.get(drink.getIdentifier()) + drink.getAmount());
                revenuePerItem.put(drink.getIdentifier(), salesPerItem.get(drink.getIdentifier()) + (drink.getAmount() * drink.getPrice()));
            }

            if (!ordersPerWaiter.containsKey(order.getWaiter())) {
                ordersPerWaiter.put(order.getWaiter(), 0);
            }
            ordersPerWaiter.put(order.getWaiter(), ordersPerWaiter.get(order.getWaiter()) + 1);

            if (!revenuePerWaiter.containsKey(order.getWaiter())) {
                revenuePerWaiter.put(order.getWaiter(), 0.0);
            }
            revenuePerWaiter.put(order.getWaiter(), ordersPerWaiter.get(order.getWaiter()) + Order.getTotal(order));
        }

        Statistics statistics = new Statistics(salesPerItem, revenuePerItem, revenuePerWaiter, ordersPerWaiter);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
