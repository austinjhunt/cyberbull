package edu.vanderbilt.cs.cyberbull.core.account.balancefinder;

import edu.vanderbilt.cs.cyberbull.core.account.Account;

/*
Strategy for finding transferable balance of an account
 */
public interface BalanceFinderStrategy {
    public double execute(Account account);
}
