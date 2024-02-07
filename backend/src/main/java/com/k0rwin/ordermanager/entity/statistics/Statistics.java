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

    public Statistics() {}

    public Statistics(HashMap<String, Integer> salesPerItem, HashMap<String, Double> revenuePerItem,
                      HashMap<String, Double> revenuePerWaiter, HashMap<String, Integer> ordersPerWaiter) {
        this.salesPerItem = salesPerItem;
        this.revenuePerItem = revenuePerItem;
        this.revenuePerWaiter = revenuePerWaiter;
        this.ordersPerWaiter = ordersPerWaiter;
    }
}
