package edu.vanderbilt.cs.cyberbull.services;

import com.kwabenaberko.newsapilib.models.Article;
import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.Dashboard;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.activity.Operation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.AccountOperation;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    private final Dashboard dashboard;
    private String activeError;
    public String getActiveError(){
        return activeError;
    }
    public void setActiveError(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        activeError = e.getClass().toString() + " ------ " +
                "Message: \n" + e.getMessage() + "-----Stack Trace: " + sw.toString();
    }
    public DashboardService() throws IOException, CsvException {
        dashboard = new Dashboard();
        activeError = "";
    }
    public List<Stock> getSP500(){
        return dashboard.getSP500();
    }
    public Optional<Stock> getStock(String symbol){
        return dashboard.getStock(symbol);
    }
    public List<Account> getBrokerageAccounts(){
        return dashboard.getBrokerageAccountList();
    }
    public List<Account> getBankAccounts(){
        return dashboard.getBankAccountList();
    }
    public Account getBankAccount(String accountNumber){
        return dashboard.getBankAccount(accountNumber);
    }
    public Account getBrokerageAccount(String accountNumber){
        return dashboard.getBrokerageAccount(accountNumber);
    }
    public void addBankAccount(String title, String description, String routingNumber, String accountNumber){
        dashboard.addBankAccount(title, description, routingNumber, accountNumber);
    }
    public void addRandomBankAccount(){
        dashboard.addRandomBankAccount();
    }
    public void addBrokerageAccount(String title, String description){
        dashboard.addBrokerageAccount(title, description);
    }
    public void addRandomBrokerageAccount(){
        dashboard.addRandomBrokerageAccount();
    }
    public boolean removeBankAccount(String accountNumber){
        return dashboard.removeBankAccount(accountNumber);
    }
    public boolean removeBrokerageAccount(String accountNumber){
        return dashboard.removeBrokerageAccount(accountNumber);
    }
    public List<HistoricalQuote> getDailyStockHistory(Stock stock){
        return dashboard.getDailyStockHistory(stock);
    }
    public List<HistoricalQuote> getWeeklyStockHistory(Stock stock){
        return dashboard.getWeeklyStockHistory(stock);
    }
    public List<HistoricalQuote> getMonthlyStockHistory(Stock stock){
        return dashboard.getMonthlyStockHistory(stock);
    }
    public List<Article> getBusinessNews(String businessName){
        return dashboard.getBusinessNews(businessName);
    }
    public void transfer(String from, String to, double amount) throws InsufficientFundsException,
            NullPointerException  {
        dashboard.transfer(from, to, amount);
    }
    public List<Stock> getAllStocks(){
        return dashboard.getAllStocks();
    }
    public List<Operation> getAllActivity(){
        return dashboard.getAllActivity();
    }
    public void updateBankAccountBalance(String accountNumber, double newBalance){
        dashboard.updateBankAccountBalance(accountNumber, newBalance);
    }
}
