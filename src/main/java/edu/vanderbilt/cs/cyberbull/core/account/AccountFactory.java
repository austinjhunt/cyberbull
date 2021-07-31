/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.account;

public interface AccountFactory {
    public Account createAccount(String title, String description, String routingNumber, String accountNumber);
}
