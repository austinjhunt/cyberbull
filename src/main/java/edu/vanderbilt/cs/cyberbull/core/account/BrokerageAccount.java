/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
BrokerageAccount class represents a Brokerage Account, which can have a
portfolio of positions, a set of watch lists, a historical record of activity (buys, sells, updates, deletes, etc.),
and a core position, which is some amount of money that has been transferred into it that has not yet been invested.
We alias "corePosition" as "balance" for Account interface consistency.
 */

package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.commander.ActivityCommander;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchListFactory;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListAddOperation;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListCommander;
import edu.vanderbilt.cs.cyberbull.core.watchlist.commander.WatchListRemoveOperation;

import java.util.ArrayList;
import java.util.List;

public class BrokerageAccount implements Account{
    private double corePosition;
    private Portfolio portfolio;
    private ActivityCommander activityCommander;
    private WatchListCommander watchListCommander;
    private WatchListFactory watchListFactory;
    private List<WatchList> watchLists;
    private String title;
    private String description;
    private String routingNumber;
    private String accountNumber;

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

    public BrokerageAccount(){
        this.title = "";
        this.description = "";
        this.accountNumber = "";
        this.routingNumber = "";
        this.activityCommander = new ActivityCommander();
        this.watchListCommander = new WatchListCommander();
        this.watchLists = new ArrayList<>();
        this.watchListFactory = new WatchListFactory();
    }

    public BrokerageAccount(String title, String description, String routingNumber, String accountNumber){
        this.title = title;
        this.description = description;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.activityCommander = new ActivityCommander();
        this.watchListCommander = new WatchListCommander();
        this.watchLists = new ArrayList<>();
        this.watchListFactory = new WatchListFactory();

    }
    @Override
    public double getBalance() {
        return this.corePosition;
    }

    @Override
    public void setBalance(double balance){
        this.corePosition = balance;
    }
    @Override
    public void updateBalance(double delta){
        this.corePosition += delta;
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


    @Override
    public ActivityCommander getActivity() {
        return activityCommander;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
    public void setActivity(ActivityCommander activityCommander) {
        this.activityCommander = activityCommander;
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

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void addWatchList(WatchList newWatchList){
        this.watchLists.add(newWatchList);
    }
}
