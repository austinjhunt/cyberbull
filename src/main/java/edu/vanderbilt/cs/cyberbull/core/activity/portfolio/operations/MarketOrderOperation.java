/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.portfolio.operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
A market order executes immediately when submitted (or immediately when the market opens
if the order is submitted in off hours). Riskier that limit order. No constraints on the
money you actually end up spending. If you execute a market order at 8AM on Monday morning
for a 10 shares of a $100 share price, and that share price jumps to 200 before 9:30, you just spent
twice what you were aiming for.
 */
public class MarketOrderOperation extends OrderOperation {
    public MarketOrderOperation(String action, Stock stock, double quantity, Account account){
        super(action, stock, quantity, account);
        this.setAction(
                "[MARKET ORDER]: " + this.action
        );
    }
    public void execute() throws InsufficientFundsException {
        try {
            if (this.action.contains("buy")) {
                System.out.println("Buying!");
                this.executeBuy();
            } else if (this.action.contains("sell")) {
                System.out.println("Selling!");
                this.executeSell();
            }
            this.dateTime = LocalDateTime.now();
        } catch (InsufficientFundsException e){
            throw new InsufficientFundsException("You do not have sufficient funds for this order");
        }
    }
    public void executeBuy() throws InsufficientFundsException {
        if (this.account.getCorePosition() >= this.transactionTotal) {
            this.account.updateCorePosition(this.transactionTotal * -1);
        } else {
            throw new InsufficientFundsException("You do not have sufficient funds in your core position for this " +
                    "order");
        }
    }
    public void executeSell(){
        // sell - add total value of sell order to the account's corePosition
        this.account.updateCorePosition(this.transactionTotal);
    }

}
