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
Operation representing creation of new account
 */

public class CreateAccountOperation implements  AccountOperation {
    private LocalDateTime dateTime;
    private String action;
    private final BrokerageAccountFactory brokerageAccountFactory;
    private final BankAccountFactory bankAccountFactory;
    private String title;
    private String description;
    private String routingNumber;
    private String accountNumber;
    private AccountType type;
    private Account accountCreated;
    public CreateAccountOperation(AccountType accountType){
        type = accountType;
        brokerageAccountFactory = new BrokerageAccountFactory();
        bankAccountFactory = new BankAccountFactory();
        // if these are null at time of execute(), use random values
        // for specific account creation, setters will be used after construction of CreateAccountOperation
        title = null;
        description = null;
        routingNumber = null;
        accountNumber = null;
    }

    // Provide setters for specific account creation before execution.
    public CreateAccountOperation setTitle(String title){
        this.title = title;
        return this;
    }
    public CreateAccountOperation setDescription(String description){
        this.description = description;
        return this;
    }
    public CreateAccountOperation setRoutingNumber(String routingNumber){
        this.routingNumber = routingNumber;
        return this;
    }
    public CreateAccountOperation setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
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
    public Account getAccountCreated(){
        return accountCreated;
    }
    public void execute(){
        if (title == null && description == null && routingNumber == null && accountNumber == null){
            if (type == AccountType.BANK){
                accountCreated = bankAccountFactory.createRandomAccount();
            } else if (type == AccountType.BROKERAGE){
                accountCreated = brokerageAccountFactory.createRandomAccount();
            }
        } else if (routingNumber == null && accountNumber == null){
            // With brokerage accounts only, you have option of providing JUST a title and description
             accountCreated = brokerageAccountFactory.createRandomAccount(title, description);
        } else {
            // all have values
            if (type == AccountType.BANK){
                accountCreated = bankAccountFactory.createAccount(
                        title,description,routingNumber,accountNumber
                );
            } else if (type == AccountType.BROKERAGE){
                accountCreated = brokerageAccountFactory.createAccount(
                        title,description,routingNumber,accountNumber
                );
            }
        }
        action = "Create " + accountCreated.toString();
        dateTime = LocalDateTime.now();
    }


}
