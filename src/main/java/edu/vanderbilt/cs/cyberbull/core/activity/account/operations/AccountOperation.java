/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.account.operations;

/*
Part of the command pattern; represents an operation to be executed and optionally stored by
the command invoker (AccountCommandExecutor).
 */

import edu.vanderbilt.cs.cyberbull.core.activity.Operation;

public interface AccountOperation extends Operation {
    void execute();
    String getAction();
}
