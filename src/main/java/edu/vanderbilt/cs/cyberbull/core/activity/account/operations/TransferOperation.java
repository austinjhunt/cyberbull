/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.account.operations;

import edu.vanderbilt.cs.cyberbull.core.account.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
TransferOperation represents a transfer of funds from one account to another. Executed and optionally
stored by the AccountCommandExecutor in the Command design pattern.
 */

public class TransferOperation implements  AccountOperation {
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount;
    private LocalDateTime dateTime;
    private final String action;
    public TransferOperation(Account fromAccount, Account toAccount, double amount){
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.action = "Transfer $" + amount + " from " + fromAccount.toString() + " to " + toAccount.toString();
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
        try{
            // this will only be reached if the source balance >= transfer amount. no need to check.
            // for brokerage account, transfer uses core position!
            if (fromAccount.getClass().getSimpleName().equals("BrokerageAccount")){
                fromAccount.updateCorePosition(amount * -1);
            } else if (fromAccount.getClass().getSimpleName().equals("BankAccount")){
                fromAccount.updateBalance(amount * -1);
            }
            if (toAccount.getClass().getSimpleName().equals("BrokerageAccount")){
                toAccount.updateCorePosition(amount);
            } else if (toAccount.getClass().getSimpleName().equals("BankAccount")){
               toAccount.updateBalance(amount);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.dateTime = LocalDateTime.now();
    }
}
