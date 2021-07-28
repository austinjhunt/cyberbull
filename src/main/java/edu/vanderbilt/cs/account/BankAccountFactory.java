/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.account;

public class BankAccountFactory implements AccountFactory {

    public Account createAccount(String title, String description) {
        return new BankAccount(title, description, randomNumberString(9), randomNumberString(12));
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public String randomNumberString(int length){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            builder.append(getRandomNumber(0,10));
        }
        return builder.toString();
    }
}
