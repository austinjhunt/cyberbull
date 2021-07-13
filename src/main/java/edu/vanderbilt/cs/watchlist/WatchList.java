/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.watchlist;

import edu.vanderbilt.cs.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WatchList {
    private ArrayList<Stock> stocks = new ArrayList<>();

    public WatchList(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Stock> add(Stock stock){
        this.stocks.add(stock);
        return this.stocks;
    }

    public List<Stock> remove(Stock stock){
        this.stocks.remove(stock);
        return this.stocks;
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
