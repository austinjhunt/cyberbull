/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
BrokerageAccount class represents a Brokerage Account, which can have a
portfolio of positions, a set of watch lists, a historical record of activity (buys, sells, updates, deletes, etc.),
and a core position, which is some amount of money that has been transferred into it that has not yet been invested.
 */

package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountType;
import edu.vanderbilt.cs.cyberbull.core.portfolio.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.position.Position;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchListFactory;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListAddOperation;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListCommander;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListRemoveOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrokerageAccount implements Account{
    private double corePosition; // non invested cash in account
    private double balance; // sum of core position and investments
    private Portfolio portfolio;
    private WatchListCommander watchListCommander;
    private WatchListFactory watchListFactory;
    private List<WatchList> watchLists;
    private String title;
    private String description;
    private String routingNumber;
    private String accountNumber;

    public BrokerageAccount(){
        this.title = "";
        this.description = "";
        this.accountNumber = "";
        this.routingNumber = "";
        this.watchListCommander = new WatchListCommander();
        this.watchLists = new ArrayList<>();
        this.watchListFactory = new WatchListFactory();
        this.portfolio = new Portfolio(this);
        this.corePosition = 0;
        this.balance = 0;
    }
    public BrokerageAccount(String title, String description, String routingNumber, String accountNumber){
        this.title = title;
        this.description = description;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.watchListCommander = new WatchListCommander();
        this.watchLists = new ArrayList<>();
        this.watchListFactory = new WatchListFactory();
        this.portfolio = new Portfolio(this);
        this.corePosition = 0;
        this.balance = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    @Override
    public double getBalance() {
        return this.corePosition + getPortfolio().getPositions().stream().map(Position::getCurrentValue).reduce(
                0.0, Double::sum
        ); // uninvested + invested
    }
    @Override
    public void setBalance(double balance){
        this.balance = balance;
    }
    @Override
    public void updateBalance(double delta){
        this.balance += delta;
    }

    @Override
    public void setCorePosition(double value) {
        corePosition = value;
    }

    @Override
    public void updateCorePosition(double delta) {
        corePosition += delta;
    }

    @Override
    public double getCorePosition() {
       return corePosition;
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
    public String getTitle() {
        return this.title;
    }
    public Portfolio getPortfolio() {
        return portfolio;
    }
    public List<WatchList> getWatchlists() {
        return watchLists;
    }
    @Override
    public void addWatchlist(String watchListName) {
        this.watchLists.add(
                this.watchListFactory.createWatchList(watchListName)
        );
    }
    public void addWatchlist(WatchList watchList) {
        this.watchLists.add(watchList);
    }
    public boolean addStockToWatchList(Stock stock, WatchList watchList){
        // watchlist is a reference to an object already stored in this.watchLists
        return this.watchListCommander.executeOperation(
                new WatchListAddOperation(stock,watchList), true
        );
    }
    public boolean removeStockFromWatchlist(Stock stock, WatchList watchList){
        // watchlist is a reference to an object already stored in this.watchLists
        return this.watchListCommander.executeOperation(
                new WatchListRemoveOperation(stock, watchList), true
        );
    }
    public Optional<WatchList> getWatchListById(String watchListId){
        return this.watchLists.stream().filter(
                watchList -> watchList.getId().equals(watchListId)
        ).findFirst();
    }
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    public void addWatchList(WatchList newWatchList){
        this.watchLists.add(newWatchList);
    }
    public String toString(){
        return AccountType.BROKERAGE + " Account #" + getAccountNumber();
    }
}
