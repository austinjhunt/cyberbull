/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.order;

import edu.vanderbilt.cs.Stock;
import edu.vanderbilt.cs.account.Account;

public class MarketOrder extends Order {
    private Account account;
    public MarketOrder(String action, Stock stock, double quantity, Account account){
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
