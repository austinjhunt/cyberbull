/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
LimitOrder class represents a limit order (an order to buy or sell at a specific price or better); a buy limit order
can only execute at the limit price or lower, where a sell limit order can only execute at the limit price or higher.
Cost basis = transaction total
cost basis per share = currentSharePrice
Cost basis = transaction total
cost basis per share = currentSharePrice
 */



public class LimitOrderOperation extends OrderOperation {
    private final double limitPrice;
    private final Account account;
    private double transactionTotal;
    protected LocalDateTime dateTime;
    private double currentSharePrice;
    public LimitOrderOperation(String action, Stock stock, double quantity, double limitPrice, Account account){
        super(action, stock, quantity);
        this.limitPrice = limitPrice;
        this.account = account;
        this.currentSharePrice = this.stock.getCurrentPrice();
        this.transactionTotal =  this.currentSharePrice * this.quantity;
    }
    public double getCurrentSharePrice(){
        return currentSharePrice;
    }
    public double getTransactionTotal(){
        return transactionTotal;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public String getDateTimeFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    public boolean execute(){
        try {
            if (this.action == "buy") {
                this.executeBuy();
            } else if (this.action == "sell") {
                this.executeSell();
            }
            this.dateTime = LocalDateTime.now();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void executeBuy(){
        // a buy depends on your core position! core position must have enough to buy.
        if (this.stock.getCurrentPrice() < this.limitPrice){
            if (this.account.getCorePosition() >= this.transactionTotal) {
                this.account.updateCorePosition(this.transactionTotal * -1);
            }
        }
    }
    public void executeSell(){
        if (this.stock.getCurrentPrice() > this.limitPrice){
            // sell - add total value of sell order to the account's corePosition
            this.account.updateCorePosition(this.transactionTotal);
        }
    }
}
