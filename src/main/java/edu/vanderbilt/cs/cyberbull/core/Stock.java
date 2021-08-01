/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

/*
A stock is a type of security, representing ownership of a publicly-traded company; stocks offer
a way for investors to profit off of a publicly-traded company's growth (long positions),
or possibly its failure (short positions). A share is a unit of the company's stock, and fractional shares
are allowed. Ex: you cannot afford one full share of a stock because the price per share is $1000, so you buy
0.1 shares for $100.
 */
public class Stock {
    private double currentPrice;
    private String symbol;
    private String businessName;
    private String industry;
    private double percentPriceChangeToday;
    private String marketSentiment;
    private yahoofinance.Stock ticker;

    public double getPercentPriceChangeToday() {
        return percentPriceChangeToday;
    }

    public void setPercentPriceChangeToday(double percentPriceChangeToday) {
        this.percentPriceChangeToday = percentPriceChangeToday;
    }

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice(){
        double price = 0;
        try {
            this.ticker = YahooFinance.get(symbol);
            price = ticker.getQuote().getPrice().doubleValue();
        } catch(IOException e){
            System.out.println("invalid symbol for YF ticker");
            System.out.println(e.getMessage());
        }
        return price;
    }

    public StockQuote getQuote(){
        try {
            return YahooFinance.get(symbol).getQuote();
        } catch(IOException e){
            return null;
        }
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarketSentiment() {
        return marketSentiment;
    }

    public void setMarketSentiment(String marketSentiment) {
        this.marketSentiment = marketSentiment;
    }

    public static int compareCurrentPrice(Stock s1, Stock s2){
        return Double.compare(s1.getCurrentPrice(), s2.getCurrentPrice());
    }

    public static int comparePercentPriceChangeToday(Stock s1, Stock s2){
        return Double.compare(s1.getPercentPriceChangeToday(), s2.getPercentPriceChangeToday());
    }

}
