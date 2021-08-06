/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.account.operations;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.account.BrokerageAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
Operation representing the update of a BANK account balance
 */

public class UpdateBalanceOperation implements  AccountOperation {
    private LocalDateTime dateTime;
    private final Account account;
    private final String action;
    private final double newBalance;
    private final double originalBalance;
    public UpdateBalanceOperation(Account target, double balance){
        account = target;
        newBalance = balance;
        originalBalance = account.getBalance();
        action = "Update balance of " + target.toString() +
                " from $" + originalBalance + " to $"  + newBalance;
    }

    public double getOriginalBalance(){
        return originalBalance;
    }
    public double getNewBalance(){
        return newBalance;
    }

    public String getAction(){
        return action;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public String getDateTimeFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    public void execute(){
        account.setBalance(newBalance);
        dateTime = LocalDateTime.now();
    }


}
