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

public class AccountCommandExecutor {
    private final List<AccountOperation> operations = new ArrayList<>();
    public List<AccountOperation> getOperations(){
        return this.operations;
    }

    public boolean executeOperation(AccountOperation operation, boolean saveOperation){
        if (saveOperation) {
            operations.add(operation);
        }
        return operation.execute();
    }
}