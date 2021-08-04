package edu.vanderbilt.cs.cyberbull.services;

import com.kwabenaberko.newsapilib.models.Article;
import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.Dashboard;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.news.NewsFinder;
import edu.vanderbilt.cs.cyberbull.core.stock_history.DailyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.MonthlyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stock_history.WeeklyHistoryVisitor;
import edu.vanderbilt.cs.cyberbull.core.stockdb.StockDB;
import org.springframework.stereotype.Service;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final Dashboard dashboard;
    private String activeError;
    private NewsFinder newsFinder;
    private List<Article> newsCache;
    private StockDB stockdb;
    public String getActiveError(){
        return this.activeError;
    }
    public void setActiveError(Exception activeError){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        activeError.printStackTrace(pw);
        this.activeError = activeError.getClass().toString() + " ------ " +
                "Message: \n" + activeError.getMessage() + "-----Stack Trace: " + sw.toString();
    }
    public DashboardService() throws IOException, CsvException {
        this.dashboard = new Dashboard();
        this.stockdb = new StockDB();
        this.newsFinder = new NewsFinder();
        this.newsCache = new ArrayList<>();
    }

    public List<Stock> getSP500(){
        return this.stockdb.getSP500();
    }
    public Optional<Stock> getStock(String symbol){
        return this.stockdb.getStock(symbol);
    }

    public List<Account> getBrokerageAccounts(){
        return this.dashboard.getBrokerageAccountList();
    }
    public List<Account> getBankAccounts(){
        return this.dashboard.getBankAccountList();
    }

    public Account getBankAccount(String accountNumber){
        System.out.println("Getting bank account from account number: " + accountNumber);
        return this.dashboard.getBankAccountList().stream().filter(account->
                account.getAccountNumber().equals(accountNumber)).collect(Collectors.toList()).get(0);
    }
    public Account getBrokerageAccount(String accountNumber){
        System.out.println("Getting brokerage account from account number: " + accountNumber);
        return this.dashboard.getBrokerageAccountList().stream().filter(account->
                account.getAccountNumber().equals(accountNumber)).collect(Collectors.toList()).get(0);
    }

    public boolean addBankAccount(String title, String description, String routingNumber, String accountNumber){
        return this.dashboard.addBankAccount(title,description,routingNumber,accountNumber);
    }
    public boolean addRandomBankAccount(){
        return this.dashboard.addRandomBankAccount();
    }
    public boolean addBrokerageAccount(String title, String description){
        return this.dashboard.addBrokerageAccount(title,description);
    }
    public boolean addRandomBrokerageAccount(){
        return this.dashboard.addRandomBrokerageAccount();
    }



    public boolean removeBankAccount(String accountNumber){
        return this.dashboard.removeBankAccount(accountNumber);
    }
    public boolean removeBrokerageAccount(String accountNumber){
        return this.dashboard.removeBrokerageAccount(accountNumber);
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

    public void transferFunds(String from, String to, double amount) throws InsufficientFundsException,
            NullPointerException  {
        Account fromAccount = null;
        Account toAccount = null;
        System.out.println("From account: " + from);
        System.out.println("To account: " + to);
        System.out.println("Amount: " + amount);
        if (from.contains("bank-")){
            System.out.println(from + " contains bank-, getting bankAccount");
            fromAccount = getBankAccount(from.split("-")[1]);
        } else if (from.contains("brokerage-")){
            System.out.println(from + " contains brokerage-, getting brokerageAccount");
            fromAccount = getBrokerageAccount(from.split("-")[1]);
        }
        if (to.contains("bank-")){
            System.out.println(to + " contains bank-, getting bankAccount");
            toAccount = getBankAccount(to.split("-")[1]);
        } else if (to.contains("brokerage-")){
            System.out.println(to + " contains brokerage-, getting brokerageAccount");
            toAccount = getBrokerageAccount(to.split("-")[1]);
        }
        if (toAccount != null && fromAccount != null && amount <= fromAccount.getBalance()){
            this.dashboard.transfer(fromAccount, toAccount, amount);
        } else if (amount > fromAccount.getBalance()) {
            throw new InsufficientFundsException("Transfer amount " + amount + " exceeds the FROM account balance");
        } else if (toAccount == null || fromAccount == null) {
            throw new NullPointerException("Neither the toAccount " + to + " nor" +
                    " the from account " + from + " can be null.");
        }

    }
}
