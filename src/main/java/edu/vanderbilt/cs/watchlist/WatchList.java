/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.watchlist;

import edu.vanderbilt.cs.Stock;

import java.util.ArrayList;
import java.util.Optional;

public class WatchList {
    private ArrayList<Stock> stocks = new ArrayList<>();

    public WatchList(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public boolean add(Stock stock){
        return this.stocks.add(stock);
    }

    public boolean remove(Stock stock){
        return this.stocks.remove(stock);
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public Optional<Stock> getBestPerformingToday(){
        return this.stocks.stream().max(Stock::comparePercentPriceChangeToday);
    }

    public Optional<Stock> getWorstPerformingToday(){
        return this.stocks.stream().min(Stock::comparePercentPriceChangeToday);
    }

    public Optional<Stock> getHighestPriceStock(){
        return this.stocks.stream().max(Stock::compareCurrentPrice);
    }
    public Optional<Stock> getLowestPriceStock(){
        return this.stocks.stream().min(Stock::compareCurrentPrice);
    }

    public boolean contains(Stock stock){
        return this.stocks.contains(stock);
    }

    public double getTotalValue(){
        return this.stocks.stream().map(Stock::getCurrentPrice).reduce(0.0, Double::sum);
    }
}
