/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;

import java.time.LocalDateTime;

/*
LimitOrder class represents a limit order (an order to buy or sell at a specific price or better); a buy limit order
can only execute at the limit price or lower, where a sell limit order can only execute at the limit price or higher.
 */

public class LimitOrderOperation extends OrderOperation {
    private final double limitPrice;
    private final Account account;
    private double transactionTotal;
    protected LocalDateTime dateTime;
    public LimitOrderOperation(String action, Stock stock, double quantity, double limitPrice, Account account){
        super(action, stock, quantity);
        this.limitPrice = limitPrice;
        this.account = account;
        this.transactionTotal = this.stock.getCurrentPrice() * this.quantity;
    }
    public double getTransactionTotal(){
        return transactionTotal;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
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
        if (this.stock.getCurrentPrice() < this.limitPrice){
            if (this.account.getBalance() >= this.transactionTotal) {
                this.account.updateBalance(this.transactionTotal * -1);
            }
        }
    }
    public void executeSell(){
        if (this.stock.getCurrentPrice() > this.limitPrice){
            // sell - add total value of sell order to the account's corePosition
            this.account.updateBalance(this.transactionTotal);
        }
    }
}