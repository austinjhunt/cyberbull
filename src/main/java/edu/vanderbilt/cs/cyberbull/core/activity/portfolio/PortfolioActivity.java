/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Invoker in Command pattern. Handles the execution and storage of
account operations; each account has its own commander.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.portfolio;


import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.activity.portfolio.operations.PortfolioOperation;

import java.util.ArrayList;
import java.util.List;

/*
Invoker in command pattern, represents account activity; responsible for
executing and optionally saving account-related commands, e.g. orders (for brokerage accounts)
and transfers (brokerage and bank accounts)
 */
/*
Think... You submit an order. That order should create, add to, or remove from a specific position in
the brokerage account portfolio.
Can an order be thought of as a factory of positions?... No. It will only ever map to one position.
So, should the position created by an order be an attribute of the order? Or should the order be added
to an "order list" attribute of the Position
 */
public class PortfolioActivity {
    private final List<PortfolioOperation> operations = new ArrayList<>();
    public List<PortfolioOperation> getOperations(){
        return this.operations;
    }
    public void executeOperation(PortfolioOperation operation, boolean saveOperation) throws InsufficientFundsException {
        if (saveOperation) {
            operations.add(operation);
        }
        operation.execute();
    }
}