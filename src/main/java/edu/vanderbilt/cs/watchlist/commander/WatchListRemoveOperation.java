/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.watchlist.commander;

import edu.vanderbilt.cs.Stock;
import edu.vanderbilt.cs.watchlist.WatchList;

/*
Concrete Remove operation class implementing the WatchListOperation interface;
command representing the removal of a stock from a watchlist
 */
public class WatchListRemoveOperation implements WatchListOperation {
    private Stock stock;
    private WatchList watchList;
    public WatchListRemoveOperation(Stock stock, WatchList watchList){
        this.stock = stock;
        this.watchList = watchList;
    }
    public boolean execute() {
        return this.watchList.remove(this.stock);
    }
}
