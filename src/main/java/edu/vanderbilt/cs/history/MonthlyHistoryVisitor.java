/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.history;

/*
Part of the Stock History visitor design pattern implementation to build out a stock's price history over 1 month
intervals for some period of time.
 */

import edu.vanderbilt.cs.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonthlyHistoryVisitor implements StockHistoryVisitor {
    public List<HistoricalQuote> visit(Stock stock){
        List<HistoricalQuote> history = new ArrayList<>();
        try {
            yahoofinance.Stock yfinanceStock = YahooFinance.get(stock.getSymbol());
            history.addAll(yfinanceStock.getHistory(Interval.MONTHLY));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return history;
    }
    public static void main(String[] args){
        Stock tesla = new Stock("TSLA");
        MonthlyHistoryVisitor visitor = new MonthlyHistoryVisitor();
        visitor.visit(tesla);
    }
}