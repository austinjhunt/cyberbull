/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.account.commander;


import edu.vanderbilt.cs.account.Account;

import java.time.LocalDateTime;

public class TransferOperation implements AccountOperation {
    private Account fromAccount;
    private Account toAccount;
    private boolean successful = false;
    private double amount;
    private LocalDateTime dateTime;

    public TransferOperation(Account fromAccount, Account toAccount, double amount){
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
    public boolean execute(){
        try{
            this.fromAccount.updateBalance(amount * -1);
            this.toAccount.updateBalance(amount);
            this.successful = true;
            // fixme: add transfer activity to both accounts
        } catch (Exception e){
            System.out.println(e.getMessage());
            this.successful = false;
        }
        this.dateTime = LocalDateTime.now();
        return this.successful;
    }

}
