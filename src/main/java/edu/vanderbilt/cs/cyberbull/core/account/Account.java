/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Account interface implemented by BankAccount and BrokerageAccount, two different types of accounts that both
implement their own versions of "balances".
 */

package edu.vanderbilt.cs.cyberbull.core.account;


import edu.vanderbilt.cs.cyberbull.core.account.commander.ActivityCommander;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;

import java.util.List;

public interface Account {
    double getBalance();
    void updateBalance(double delta);
    public String getRoutingNumber();
    public String getAccountNumber();
    public String getDescription();
    public String getTitle();
    public void setBalance(double balance);
    public List<WatchList> getWatchlists();
    public void addWatchlist(String watchListName);
    ActivityCommander getActivity();
    void setActivity(ActivityCommander activity);
}
