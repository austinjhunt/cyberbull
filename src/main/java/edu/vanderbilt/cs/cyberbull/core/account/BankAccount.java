/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
BankAccount class represents a bank account with an identifying routing and account number, as well
as a balance at any given time that can be increased or decreased via transfers (Transfer class) from other accounts.
 */

package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountType;
import edu.vanderbilt.cs.cyberbull.core.portfolio.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;

import java.util.List;
import java.util.Optional;

public class BankAccount implements Account {
    private String routingNumber;
    private String accountNumber;
    private String description;
    private String title;
    private double balance;

    public BankAccount() {
        this.title = "";
        this.description = "";
        this.accountNumber = "";
        this.routingNumber = "";
    }

    public BankAccount(String title, String description, String routingNumber, String accountNumber) {
        this.title = title;
        this.description = description;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
    }

    @Override
    public void setCorePosition(double delta){
        //no-op
    }
    @Override
    public void updateCorePosition(double delta){
        // no op
    }
    @Override
    public double getCorePosition(){
        return 0; // no op
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void updateBalance(double delta) {
        this.balance += delta;
    }

    @Override
    public String getRoutingNumber() {
        return this.routingNumber;
    }

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<WatchList> getWatchlists() {
        // bank account has no watchlists
        return null;
    }

    @Override
    public void addWatchlist(String watchListName) {
        //no-op
    }
    @Override
    public Optional<WatchList> getWatchListById(String watchListId){
        //no-op
        return null;
    }

    @Override
    public Portfolio getPortfolio(){
        //no-op
        return null;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }


    public String toString(){
        return AccountType.BANK + " Account #" + getAccountNumber();
    }
   }
