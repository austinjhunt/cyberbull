/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;

public class MarketOrderOperation extends OrderOperation {
    private final Account account;
    public MarketOrderOperation(String action, Stock stock, double quantity, Account account){
        super(action, stock, quantity);
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
        double totalValue = this.stock.getCurrentPrice() * this.quantity;
        if (this.account.getBalance() >= totalValue) {
            this.account.updateBalance(totalValue * -1);
        }
    }
    public void executeSell(){
        // sell - add total value of sell order to the account's corePosition
        double totalValue = this.stock.getCurrentPrice() * this.quantity;
        this.account.updateBalance(totalValue);

    }

}
