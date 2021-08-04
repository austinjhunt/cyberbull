/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.stock_history;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Part of the Stock History visitor design pattern implementation to build out a stock's price history over 1 minute
intervals for some period of time.
 */

public class DailyHistoryVisitor implements StockHistoryVisitor {
    public List<HistoricalQuote> visit(Stock stock){
        List<HistoricalQuote> history = new ArrayList<>();
        try {
             yahoofinance.Stock yfinanceStock = YahooFinance.get(stock.getSymbol());
             try{
                 history.addAll(yfinanceStock.getHistory(Interval.DAILY));
             } catch (Exception e){
                 System.out.println("Exception when getting DAILY history");
             }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return history;
    }
    public static void main(String[] args){
        Stock tesla = new Stock("TSLA");
        DailyHistoryVisitor visitor = new DailyHistoryVisitor();
        visitor.visit(tesla);
    }
}
