/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.watchlist;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.random.RandomUtil;

import java.util.ArrayList;
import java.util.Optional;

public class WatchList {
    private ArrayList<Stock> stocks;
    private String title;
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public WatchList(){
        this.stocks = new ArrayList<>();
        this.id = RandomUtil.randomNumberString(10);
    }
    public WatchList(String title){
        this.stocks = new ArrayList<>();
        this.title = title;
        this.id = RandomUtil.randomNumberString(10);
    }
    public WatchList(ArrayList<Stock> stocks, String title) {
        this.stocks = stocks;
        this.title = title;
        this.id = RandomUtil.randomNumberString(10);
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
