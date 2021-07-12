/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.history;

import edu.vanderbilt.cs.Stock;

import java.time.LocalDateTime;
import java.util.HashMap;

/*
StockHistory class uses an underlying hashmap data structure to map historical datetimes to the price of a specific
stock at that datetime.
 */

public class StockHistory {
    private HashMap<LocalDateTime, Double> priceHistory;
    private Stock stock;
    private String frequency;
    public StockHistory(Stock stock, String frequency){
        this.stock = stock;
        this.frequency = frequency;
        this.priceHistory = new HashMap<>();
    }
    public void addRecord(LocalDateTime dateTime, double price){
        this.priceHistory.put(dateTime, price);
    }
}
