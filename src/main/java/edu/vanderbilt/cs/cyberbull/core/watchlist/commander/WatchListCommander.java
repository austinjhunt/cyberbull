/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.watchlist.commander;

import java.util.ArrayList;
import java.util.List;

/*
Command invoker in the command pattern for watch list modifications; responsible for storing
operations executed on watchlists.
 */

public class WatchListCommander {
    private final List<WatchListOperation> operations = new ArrayList<>();
    public List<WatchListOperation> getOperations(){
        return this.operations;
    }

    public boolean executeOperation(WatchListOperation operation, boolean saveOperation){
        if (saveOperation) {
            operations.add(operation);
        }
        return operation.execute();
    }
}
