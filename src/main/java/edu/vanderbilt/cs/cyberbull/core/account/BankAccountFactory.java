/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.account;


import edu.vanderbilt.cs.cyberbull.core.random.RandomBankAccountTitleFactory;
import edu.vanderbilt.cs.cyberbull.core.random.RandomUtil;

/*
Bank account factory is a type of account factory specifically for
creating BankAccount products
 */
public class BankAccountFactory implements AccountFactory {
    private RandomBankAccountTitleFactory randomBankAccountTitleFactory = new RandomBankAccountTitleFactory();

    public Account createAccount(String title, String description, String routingNumber, String accountNumber) {
        return new BankAccount(
                title, description, routingNumber, accountNumber);
    }
    public Account createRandomAccount() {
        return new BankAccount(
                randomBankAccountTitleFactory.generateRandomPhrase(),
                "",
                RandomUtil.randomNumberString(9),
                RandomUtil.randomNumberString(12));
    }
}
