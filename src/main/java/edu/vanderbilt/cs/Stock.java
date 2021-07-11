/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs;

public class Stock {
    private double currentPrice;
    private String symbol;
    private String businessName;
    private String industry;
    private double percentPriceChangeToday;
    private String marketSentiment;

    public double getPercentPriceChangeToday() {
        return percentPriceChangeToday;
    }

    public void setPercentPriceChangeToday(double percentPriceChangeToday) {
        this.percentPriceChangeToday = percentPriceChangeToday;
    }

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
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
