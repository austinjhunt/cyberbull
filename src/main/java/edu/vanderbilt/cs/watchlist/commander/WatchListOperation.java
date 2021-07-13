/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.watchlist.commander;

/*
Part of the command pattern; represents an operation to be executed on a watchlist of stocks and optionally stored by
the command invoker (WatchListCommandExecutor).
 */

public interface WatchListOperation {
    boolean execute();
}
