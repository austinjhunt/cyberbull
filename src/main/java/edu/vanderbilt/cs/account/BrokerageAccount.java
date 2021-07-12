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

package edu.vanderbilt.cs.account;

import edu.vanderbilt.cs.Activity;
import edu.vanderbilt.cs.Portfolio;
import edu.vanderbilt.cs.WatchList;
import edu.vanderbilt.cs.account.commander.AccountCommandExecutor;

import java.util.List;

public class BrokerageAccount implements Account{
    private double corePosition;
    private Portfolio portfolio;
    private Activity activity;
    private List<WatchList> watchLists;
    private String title;
    private String description;
    private AccountCommandExecutor commander;
    public BrokerageAccount(String title, String description){
         this.title = title;
         this.description = description;
         this.commander = new AccountCommandExecutor();
    }

    public double getBalance() {
        return this.corePosition;
    }

    public void updateBalance(double delta){
        this.corePosition += delta;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public List<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void addWatchList(WatchList newWatchList){
        this.watchLists.add(newWatchList);
    }
}