/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.random.RandomBrokerageAccountTitleFactory;
import edu.vanderbilt.cs.cyberbull.core.random.RandomUtil;

public class BrokerageAccountFactory implements AccountFactory {
    private RandomBrokerageAccountTitleFactory randomBrokerageAccountTitleFactory = new RandomBrokerageAccountTitleFactory();
    @Override
    public Account createAccount(String title, String description, String routingNumber, String accountNumber) {
        return new BrokerageAccount(title, description, routingNumber, accountNumber);
    }
    public Account createRandomAccount(String title, String description){
        // create an account by only specifying title and description; generate random routing/account nums
        return new BrokerageAccount(title, description, RandomUtil.randomNumberString(9), RandomUtil.randomNumberString(12));
    }

    public Account createRandomAccount(){
        // create an account by only specifying title and description; generate random routing/account nums
        return new BrokerageAccount(
                randomBrokerageAccountTitleFactory.generateRandomPhrase(), "",
                RandomUtil.randomNumberString(9), RandomUtil.randomNumberString(12));
    }


}
