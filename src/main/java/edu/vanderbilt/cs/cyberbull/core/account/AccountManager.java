package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.account.balancefinder.BalanceFinderContext;
import edu.vanderbilt.cs.cyberbull.core.activity.Operation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountActivity;
import edu.vanderbilt.cs.cyberbull.core.activity.account.AccountType;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.AccountOperation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.CreateAccountOperation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.TransferOperation;
import edu.vanderbilt.cs.cyberbull.core.activity.account.operations.UpdateBalanceOperation;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountManager {
    private final List<Account> bankAccountList;
    private final List<Account> brokerageAccountList;
    private final AccountActivity activity;
    public AccountManager(){
        bankAccountList = new ArrayList<>();
        brokerageAccountList = new ArrayList<>();
        activity = new AccountActivity();
    }
    public boolean addBankAccount(String title, String description, String routingNumber, String accountNumber){
        CreateAccountOperation operation = new CreateAccountOperation(AccountType.BANK);
        activity.executeOperation(
                operation.setTitle(title)
                        .setDescription(description)
                        .setRoutingNumber(routingNumber)
                        .setAccountNumber(accountNumber), true
        );
        return bankAccountList.add(operation.getAccountCreated());
    }
    public boolean removeBankAccount(String accountNumber){
        return bankAccountList.removeIf(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber));
    }
    public boolean addBrokerageAccount(String title, String description){
        CreateAccountOperation operation = new CreateAccountOperation(AccountType.BROKERAGE);
        activity.executeOperation(
                operation.setTitle(title)
                        .setDescription(description), true
        );
        return brokerageAccountList.add(operation.getAccountCreated());
    }
    public boolean removeBrokerageAccount(String accountNumber){
        return brokerageAccountList.removeIf(brokerageAccount -> brokerageAccount.getAccountNumber().equals(accountNumber));
    }

    public void clearBrokerageAccountList(){
        brokerageAccountList.clear();
    }
    public void clearBankAccountList(){
        bankAccountList.clear();
    }

    public boolean addRandomBrokerageAccount(){
        CreateAccountOperation operation = new CreateAccountOperation(AccountType.BROKERAGE);
        activity.executeOperation(operation, true );
        return brokerageAccountList.add(operation.getAccountCreated());
    }
    public boolean addRandomBankAccount(){
        CreateAccountOperation operation = new CreateAccountOperation(AccountType.BANK);
        activity.executeOperation(operation, true );
        return bankAccountList.add(operation.getAccountCreated());
    }

    public List<Account> getBrokerageAccountList(){
        return brokerageAccountList;
    }

    public List<Account> getBankAccountList(){
        return bankAccountList;
    }
    public Account getBankAccount(String accountNumber){
        return getBankAccountList().stream().filter(account->
                account.getAccountNumber().equals(accountNumber)).collect(Collectors.toList()).get(0);
    }
    public Account getBrokerageAccount(String accountNumber){
        return getBrokerageAccountList().stream().filter(account->
                account.getAccountNumber().equals(accountNumber)).collect(Collectors.toList()).get(0);
    }

    private Account getAccountFromStringIdentifier(String identifier){
        // identifier is either bank-<BankAccount.accountNumber> or brokerage-<BrokerageAccount.accountNumber>
        String accountType = identifier.split("-")[0];
        String accountNumber = identifier.split("-")[1];
        Account account = null;
        if (accountType.equals("bank")){
            account = getBankAccount(accountNumber);
        } else if (accountType.equals("brokerage")){
            account = getBrokerageAccount(accountNumber);
        }
        return account;
    }
    public void transfer(String from, String to, double amount) throws InsufficientFundsException,
            NullPointerException  {
        transfer(getAccountFromStringIdentifier(from), getAccountFromStringIdentifier(to), amount);
    }
    public void transfer(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException,
            NullPointerException  {
        BalanceFinderContext balanceFinderContext = new BalanceFinderContext(fromAccount);
        double fromBalance = balanceFinderContext.executeStrategy();
        if (toAccount != null && fromAccount != null && amount <= fromBalance){
            activity.executeOperation(
                    new TransferOperation(fromAccount, toAccount, amount), true
            );
        } else if (amount > fromBalance) {
            throw new InsufficientFundsException("Transfer amount " + amount + " exceeds the FROM account balance");
        } else if (toAccount == null || fromAccount == null) {
            throw new NullPointerException("Neither the toAccount nor the from account can be null.");
        }
    }
    public void updateBankAccountBalance(String accountNumber, double newBalance){
        UpdateBalanceOperation updateBalanceOperation = new UpdateBalanceOperation(getBankAccount(accountNumber), newBalance);
        activity.executeOperation(updateBalanceOperation, true);
    }
    public List<Operation> getAllActivity(){
        // account operations
        List<Operation> allActivity = new ArrayList<>(activity.getOperations());
        // all portfolio operations
        getBrokerageAccountList().forEach(ba -> allActivity.addAll(
                ba.getPortfolio().getActivity().getOperations()));
        // now sort by time
        allActivity.sort((op1, op2)->op2.getDateTime().compareTo(op1.getDateTime()));
        return allActivity;
    }
}
