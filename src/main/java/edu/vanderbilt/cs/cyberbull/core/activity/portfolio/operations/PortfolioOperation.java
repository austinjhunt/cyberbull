/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.portfolio.operations;

/*
Part of the command pattern; represents an operation to be executed and optionally stored by
the command invoker (AccountCommandExecutor).
 */

import edu.vanderbilt.cs.cyberbull.core.activity.Operation;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;

import java.time.LocalDateTime;

public interface PortfolioOperation extends Operation {
    void execute() throws InsufficientFundsException;
    double getCurrentSharePrice();
    double getTransactionTotal();

}
