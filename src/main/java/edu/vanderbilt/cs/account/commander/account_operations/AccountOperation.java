/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.account.commander.account_operations;

/*
Part of the command pattern; represents an operation to be executed and optionally stored by
the command invoker (AccountCommandExecutor).
 */

public interface AccountOperation {
    void execute();
}
