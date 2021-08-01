package edu.vanderbilt.cs.cyberbull.services;

import edu.vanderbilt.cs.cyberbull.core.Dashboard;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final Dashboard dashboard;
    private String activeError;

    public String getActiveError(){
        return this.activeError;
    }
    public void setActiveError(Exception activeError){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        activeError.printStackTrace(pw);
        this.activeError =
                "Message: \n" + activeError.getMessage() + "\nStack Trace: \n" + sw.toString();
    }
    public DashboardService(){
        this.dashboard = new Dashboard();
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