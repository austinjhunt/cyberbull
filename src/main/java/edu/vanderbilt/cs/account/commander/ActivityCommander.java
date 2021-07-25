/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Invoker in Command pattern. Handles the execution and storage of
account operations; each account has its own commander.
 */

package edu.vanderbilt.cs.account.commander;

import java.util.ArrayList;
import java.util.List;

/*
Invoker in command pattern, represents account activity; responsible for
executing and optionally saving account-related commands, e.g. orders (for brokerage accounts)
and transfers (brokerage and bank accounts)
 */

public class ActivityCommander {
    private final List<AccountOperation> operations = new ArrayList<>();
    public List<AccountOperation> getOperations(){
        return this.operations;
    }

    public void executeOperation(AccountOperation operation, boolean saveOperation){
        if (saveOperation) {
            operations.add(operation);
        }
        operation.execute();
    }
}