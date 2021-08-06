/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core;

import com.kwabenaberko.newsapilib.models.Article;
import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.AccountManager;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.account.BrokerageAccountFactory;
import edu.vanderbilt.cs.cyberbull.core.account.balancefinder.BalanceFinderContext;
import edu.vanderbilt.cs.cyberbull.core.activity.Operation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountActivity;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.AccountOperation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.TransferOperation;
import edu.vanderbilt.cs.cyberbull.core.couchbase.stockdb.StockDB;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.news.NewsFinder;
import edu.vanderbilt.cs.cyberbull.core.stock_history.DailyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.MonthlyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.WeeklyHistoryVisitor;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Dashboard represents the Bridge pattern in that it divides the underlying code
of the accounts, portfolios, stocks, positions, etc. from the key client-facing functionalities.

Assume a client can have multiple bank accounts on their dashboard and multiple brokerage
 */
public class Dashboard {
    private final AccountManager accountManager;
    private final List<Article> newsCache;
    private final NewsFinder newsFinder;
    private final StockDB stockDB;

    public Dashboard() throws IOException, CsvException {
        accountManager = new AccountManager();
        newsCache = new ArrayList<>();
        newsFinder = new NewsFinder();
        stockDB = new StockDB();
    }

    public List<Stock> getAllStocks(){
        List<Stock> allStocks = new ArrayList<>();
        getBrokerageAccountList().forEach(ba -> ba.getPortfolio().getPositions().forEach(p->allStocks.add(p.getStock())));
        return allStocks;
    }

    public boolean addBankAccount(String title, String description, String routingNumber, String accountNumber){
        return accountManager.addBankAccount(title, description, routingNumber, accountNumber);
    }
    public boolean removeBankAccount(String accountNumber){
        return accountManager.removeBankAccount(accountNumber);
    }
    public boolean addBrokerageAccount(String title, String description){
        return accountManager.addBrokerageAccount(title,description);
    }
    public boolean removeBrokerageAccount(String accountNumber){
         return accountManager.removeBrokerageAccount(accountNumber);
    }
    public void clearBrokerageAccountList(){
        accountManager.clearBrokerageAccountList();
    }
    public void clearBankAccountList(){
        accountManager.clearBankAccountList();
    }
    public boolean addRandomBrokerageAccount(){
        return accountManager.addRandomBrokerageAccount();
    }
    public boolean addRandomBankAccount(){
        return accountManager.addRandomBankAccount();
    }
    public List<Account> getBrokerageAccountList(){
        return accountManager.getBrokerageAccountList();
    }
    public List<Account> getBankAccountList(){
        return accountManager.getBankAccountList();
    }
    public void transfer(String from, String to, double amount) throws InsufficientFundsException,
            NullPointerException  {
        accountManager.transfer(from,to,amount);
    }
    public void transfer(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException,
            NullPointerException  {
       accountManager.transfer(fromAccount,toAccount,amount);
    }
    public Account getBankAccount(String accountNumber){
        return accountManager.getBankAccount(accountNumber);
    }
    public Account getBrokerageAccount(String accountNumber){
        return accountManager.getBrokerageAccount(accountNumber);
    }
    public void updateBankAccountBalance(String accountNumber, double newBalance){
        accountManager.updateBankAccountBalance(accountNumber, newBalance);
    }
    public List<Operation> getAllActivity(){
        return accountManager.getAllActivity();
    }
    public List<Stock> getSP500(){
        return stockDB.getSP500();
    }
    public Optional<Stock> getStock(String symbol){
        return stockDB.getStock(symbol);
    }


    public List<HistoricalQuote> getDailyStockHistory(Stock stock){
        DailyHistoryVisitor visitor = new DailyHistoryVisitor();
        return visitor.visit(stock);
    }
    public List<HistoricalQuote> getWeeklyStockHistory(Stock stock){
        WeeklyHistoryVisitor visitor = new WeeklyHistoryVisitor();
        return visitor.visit(stock);
    }
    public List<HistoricalQuote> getMonthlyStockHistory(Stock stock){
        MonthlyHistoryVisitor visitor = new MonthlyHistoryVisitor();
        return visitor.visit(stock);
    }

    public List<Article> getBusinessNews(String businessName){
        if (newsCache.size() == 0) {
            newsCache.addAll(newsFinder.queryNewsForBusiness(businessName));
        }
        return newsCache;
    }
}
