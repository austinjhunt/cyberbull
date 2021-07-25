/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
BankAccount class represents a bank account with an identifying routing and account number, as well
as a balance at any given time that can be increased or decreased via transfers (Transfer class) from other accounts.
 */

package edu.vanderbilt.cs.account;
import edu.vanderbilt.cs.account.commander.ActivityCommander;

public class BankAccount implements Account{
    private final String routingNumber;
    private final String accountNumber;
    private final String description;
    private final String title;
    private final ActivityCommander commander;
    private ActivityCommander activity;
    private double balance;

    public BankAccount(String title, String description, String routingNumber, String accountNumber){
        this.title = title;
        this.description = description;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.commander = new ActivityCommander();
    }
    public BankAccount(String title, String description, String routingNumber, String accountNumber, double balance){
        this.title = title;
        this.description = description;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.commander = new ActivityCommander();
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return this.balance;
    }
    public void updateBalance(double delta){
        this.balance += delta;
    }
    public ActivityCommander getActivity() {
        return this.activity;
    }
    public void setActivity(ActivityCommander activity) {
        this.activity = activity;
    }
}
