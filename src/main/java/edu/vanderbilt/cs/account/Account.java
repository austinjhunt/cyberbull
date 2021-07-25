/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Account interface implemented by BankAccount and BrokerageAccount, two different types of accounts that both
implement their own versions of "balances".
 */

package edu.vanderbilt.cs.account;


import edu.vanderbilt.cs.account.commander.ActivityCommander;

public interface Account {
    double getBalance();
    void updateBalance(double delta);
    ActivityCommander getActivity();
    void setActivity(ActivityCommander activity);

}
