package edu.vanderbilt.cs.cyberbull.core.account.balancefinder;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccount;
import edu.vanderbilt.cs.cyberbull.core.account.BrokerageAccount;

/*
Context in the strategy pattern for finding transferable balance of an account
 */
public class BalanceFinderContext {
    private BalanceFinderStrategy strategy;
    private final Account account;
    public BalanceFinderContext(Account account){
        this.account = account;
        if (account instanceof BrokerageAccount){
            this.strategy = new BrokerageAccountBalanceFinder();
        } else if (account instanceof BankAccount){
            this.strategy = new BankAccountBalanceFinder();
        }
    }
    public double executeStrategy(){
        return this.strategy.execute(this.account);
    }
}
