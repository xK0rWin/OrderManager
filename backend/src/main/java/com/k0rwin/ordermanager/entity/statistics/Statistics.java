package com.k0rwin.ordermanager.entity.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Statistics {

    @JsonProperty(value = "sales_per_item")
    private HashMap<String, Integer> salesPerItem;
    @JsonProperty(value = "rev_per_item")
    private HashMap<String, Double> revenuePerItem;
    @JsonProperty(value = "rev_per_waiter")
    private HashMap<String, Double> revenuePerWaiter;
    @JsonProperty(value = "order_per_waiter")
    private HashMap<String, Integer> ordersPerWaiter;
    @JsonProperty(value = "total_rev")
    private double totalRevenue;
    @JsonProperty(value = "meal_sales")
    private int mealSales;
    @JsonProperty(value = "drink_sales")
    private int drinkSales;

    public Statistics() {}

    public Statistics(HashMap<String, Integer> salesPerItem, HashMap<String, Double> revenuePerItem,
                      HashMap<String, Double> revenuePerWaiter, HashMap<String, Integer> ordersPerWaiter,
                      double totalRevenue, int mealSales, int drinkSales) {
        this.salesPerItem = salesPerItem;
        this.revenuePerItem = revenuePerItem;
        this.revenuePerWaiter = revenuePerWaiter;
        this.ordersPerWaiter = ordersPerWaiter;
        this.totalRevenue = totalRevenue;
        this.mealSales = mealSales;
        this.drinkSales = drinkSales;
    }
}
