/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;

/*
LimitOrder class represents a limit order (an order to buy or sell at a specific price or better); a buy limit order
can only execute at the limit price or lower, where a sell limit order can only execute at the limit price or higher.
 */

public class LimitOrderOperation extends OrderOperation {
    private final double limitPrice;
    private final Account account;
    public LimitOrderOperation(String action, Stock stock, double quantity, double limitPrice, Account account){
        super(action, stock, quantity);
        this.limitPrice = limitPrice;
        this.account = account;
    }

    public void execute(){
        if (this.action == "buy"){
            this.executeBuy();
        } else if (this.action == "sell"){
            this.executeSell();
        }
    }
    public void executeBuy(){
        if (this.stock.getCurrentPrice() < this.limitPrice){
            double totalValue = this.stock.getCurrentPrice() * this.quantity;
            if (this.account.getBalance() >= totalValue) {
                this.account.updateBalance(totalValue * -1);
            }
        }
    }
    public void executeSell(){
        if (this.stock.getCurrentPrice() > this.limitPrice){
            // sell - add total value of sell order to the account's corePosition
            double totalValue = this.stock.getCurrentPrice() * this.quantity;
            this.account.updateBalance(totalValue);

        }
    }
}
