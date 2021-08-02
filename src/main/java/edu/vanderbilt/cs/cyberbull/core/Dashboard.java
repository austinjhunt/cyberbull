/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.account.BrokerageAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.account.commander.ActivityCommander;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.TransferOperation;

import java.util.ArrayList;
import java.util.List;

/* Dashboard represents the Bridge pattern in that it divides the underlying code
of the accounts, portfolios, stocks, positions, etc. from the key client-facing functionalities.

Assume a client can have multiple bank accounts on their dashboard and multiple brokerage
 */
public class Dashboard {
    private final List<Account> bankAccountList;
    private final List<Account> brokerageAccountList;
    private final BrokerageAccountFactory brokerageAccountFactory;
    private final BankAccountFactory bankAccountFactory;
    private final ActivityCommander activityCommander;


    public Dashboard(){
        this.bankAccountList = new ArrayList<>();
        this.brokerageAccountList = new ArrayList<>();
        this.brokerageAccountFactory = new BrokerageAccountFactory();
        this.bankAccountFactory = new BankAccountFactory();
        this.activityCommander = new ActivityCommander();
    }

    // Create bank account with all information provided.
    public boolean addBankAccount(String title, String description, String routingNumber, String accountNumber){
        return this.bankAccountList.add(
                bankAccountFactory.createAccount(title,description,routingNumber,accountNumber)
        );
    }
    public boolean removeBankAccount(String accountNumber){
        return this.bankAccountList.removeIf(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber));
    }
    public boolean addBrokerageAccount(String title, String description){
        return this.brokerageAccountList.add(
                brokerageAccountFactory.createRandomAccount(title,description)
        );
    }
    public boolean removeBrokerageAccount(String accountNumber){
        return this.brokerageAccountList.removeIf(brokerageAccount -> brokerageAccount.getAccountNumber().equals(accountNumber));
    }

    public void clearBrokerageAccountList(){
        brokerageAccountList.clear();
    }
    public void clearBankAccountList(){
        bankAccountList.clear();
    }

    public boolean addRandomBrokerageAccount(){
        return this.brokerageAccountList.add(
                brokerageAccountFactory.createRandomAccount()
        );
    }
    public boolean addRandomBankAccount(){
        return this.bankAccountList.add(
                bankAccountFactory.createRandomAccount()
        );
    }

    public List<Account> getBrokerageAccountList(){
        return this.brokerageAccountList;
    }

    public List<Account> getBankAccountList(){
        return this.bankAccountList;
    }

    public void transfer(Account from, Account to, double amount){
        this.activityCommander.executeOperation(
                new TransferOperation(from, to, amount), true
        );
    }
}
