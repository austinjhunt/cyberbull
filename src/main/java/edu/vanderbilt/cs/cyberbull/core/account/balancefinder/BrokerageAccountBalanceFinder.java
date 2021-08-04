package edu.vanderbilt.cs.cyberbull.core.account.balancefinder;

import edu.vanderbilt.cs.cyberbull.core.account.Account;

/*
one of the strategies in teh Balance Finder strategy pattern for finding an account balance.
Bank account balance is easy. use .getBalance().
 */
public class BrokerageAccountBalanceFinder implements BalanceFinderStrategy{
    @Override
    public double execute(Account account){
        return account.getCorePosition();
    }
}
