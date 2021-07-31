package edu.vanderbilt.cs.cyberbull.core.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String errorMessage){
        super(errorMessage);
    }
}
