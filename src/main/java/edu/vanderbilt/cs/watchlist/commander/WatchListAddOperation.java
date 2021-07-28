/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.watchlist.commander;

import edu.vanderbilt.cs.Stock;
import edu.vanderbilt.cs.watchlist.WatchList;

/*
Concrete add operation class implementing the WatchListOperation interface;
command representing the addition of a stock to a watchlist
 */
public class WatchListAddOperation implements WatchListOperation{
    private Stock stock;
    private WatchList watchList;
    public WatchListAddOperation(Stock stock, WatchList watchList){
        this.stock = stock;
        this.watchList = watchList;
    }
    public boolean execute() {
        return this.watchList.add(this.stock);
    }
}
