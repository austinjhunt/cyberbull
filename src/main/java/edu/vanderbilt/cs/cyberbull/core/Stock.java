/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core;

import edu.vanderbilt.cs.cyberbull.core.stock_history.DailyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.MonthlyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.WeeklyHistoryVisitor;
import org.json.JSONObject;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
        } catch(IOException | NullPointerException e){
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


    public ArrayList<JSONObject> getMonthlyHistory(){
        ArrayList<JSONObject> history = new ArrayList<>();
        MonthlyHistoryVisitor monthlyHistoryVisitor = new MonthlyHistoryVisitor();
        monthlyHistoryVisitor.visit(this).stream().forEachOrdered(
                quote->history.add(buildQuoteJson(quote))
        );
        return history;
    }

    public ArrayList<JSONObject> getWeeklyHistory(){
        ArrayList<JSONObject> history = new ArrayList<>();
        WeeklyHistoryVisitor weeklyHistoryVisitor = new WeeklyHistoryVisitor();
        weeklyHistoryVisitor.visit(this).stream().forEachOrdered(
                quote->history.add(buildQuoteJson(quote))
        );
        return history;
    }

    public ArrayList<JSONObject> getDailyHistory(){
        ArrayList<JSONObject> history = new ArrayList<>();
        DailyHistoryVisitor dailyHistoryVisitor = new DailyHistoryVisitor();
        dailyHistoryVisitor.visit(this).stream().forEachOrdered(
                quote->history.add(buildQuoteJson(quote))
        );
        return history;
    }

    private String getDateStringFromHistoricalQuote(HistoricalQuote quote){
        Calendar date = quote.getDate();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        String dateString = "" + year + "-" + month + "-" + day;
        return dateString;
    }
    private JSONObject buildQuoteJson(HistoricalQuote quote){
        JSONObject datePrices = new JSONObject();
        datePrices.put("date", getDateStringFromHistoricalQuote(quote));
        try{
            datePrices.put("open", quote.getOpen().doubleValue());
        } catch (Exception e){
            datePrices.put("open", 0);
        }
        try{
            datePrices.put("close", quote.getClose().doubleValue());
        } catch (Exception e){
            datePrices.put("close", 0);
        }
        try{
            datePrices.put("high", quote.getHigh().doubleValue());
        } catch (Exception e){
            datePrices.put("high", 0);
        }
        try{
            datePrices.put("low", quote.getLow().doubleValue());
        } catch (Exception e){
            datePrices.put("low", 0);
        }try{
            datePrices.put("volume", quote.getVolume().doubleValue());
        } catch (Exception e){
            datePrices.put("volume", 0);
        }
        return datePrices;
    }
    public static void main(String[] args){
        Stock stock = new Stock("TSLA");
    }


}
