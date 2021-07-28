/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.account;

public interface AccountFactory {
    public String randomNumberString(int length);
    public Account createAccount(String title, String description);
}
