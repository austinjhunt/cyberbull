/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Account interface implemented by BankAccount and BrokerageAccount, two different types of accounts that both
implement their own versions of "balances".
 */

package edu.vanderbilt.cs.cyberbull.core.account;


import edu.vanderbilt.cs.cyberbull.core.portfolio.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;

import java.util.List;
import java.util.Optional;

public interface Account {
    public void setBalance(double balance);
    public double getBalance();
    public void updateBalance(double delta);
    public void setCorePosition(double delta);
    public void updateCorePosition(double delta);
    public double getCorePosition();
    public String getRoutingNumber();
    public String getAccountNumber();
    public String getDescription();
    public String getTitle();
    public List<WatchList> getWatchlists();
    public Optional<WatchList> getWatchListById(String watchListId);
    public void addWatchlist(String watchListName);
    public Portfolio getPortfolio();
}
