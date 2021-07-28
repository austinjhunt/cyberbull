/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs;

import edu.vanderbilt.cs.account.BankAccount;
import edu.vanderbilt.cs.account.BankAccountFactory;
import edu.vanderbilt.cs.account.BrokerageAccount;
import edu.vanderbilt.cs.account.BrokerageAccountFactory;

import java.util.ArrayList;
import java.util.List;

/* Dashboard represents the Bridge pattern in that it divides the underlying code
of the accounts, portfolios, stocks, positions, etc. from the key client-facing functionalities.

Assume a client can have multiple bank accounts on their dashboard and multiple brokerage
 */
public class Dashboard {
    private List<BankAccount> bankAccountList;
    private List<BrokerageAccount> brokerageAccountList;
    private BrokerageAccountFactory brokerageAccountFactory;
    private BankAccountFactory bankAccountFactory;

    public Dashboard(String brokerageAccountTitle){
        this.bankAccountList = new ArrayList<>();
        this.brokerageAccountList = new ArrayList<>();
        this.brokerageAccountFactory = new BrokerageAccountFactory();
        this.bankAccountFactory = new BankAccountFactory();
    }
    // fixme: allow random brokerage account generation but doesnt make sense to allow random
    // bank account generation with bankaccountfactory
    public boolean addBankAccount(BankAccount bankAccount){
        return this.bankAccountList.add(bankAccount);
    }
    public boolean removeBankAccount(BankAccount bankAccount){
        return this.bankAccountList.remove(bankAccount);
    }
    public boolean addBrokerageAccount(BrokerageAccount brokerageAccount){
        return this.brokerageAccountList.add(brokerageAccount);
    }
    public boolean removeBrokerageAccount(BrokerageAccount brokerageAccount){
        return this.brokerageAccountList.remove(brokerageAccount);
    }
}
