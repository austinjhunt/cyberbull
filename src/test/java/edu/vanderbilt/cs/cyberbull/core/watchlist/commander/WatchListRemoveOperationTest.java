package edu.vanderbilt.cs.cyberbull.core.watchlist.commander;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchListRemoveOperationTest {
    protected static WatchListRemoveOperation watchListRemoveOperation;
    protected static Stock stock;
    protected static WatchList watchList;
    @BeforeAll
    static void setUp(){
        stock = new Stock("TSLA");
        watchList = new WatchList();
        watchListRemoveOperation = new WatchListRemoveOperation(stock, watchList);
    }
    @Test
    void execute() {
        watchList.add(stock);
        assertTrue(watchList.contains(stock));
        watchListRemoveOperation.execute();
        assertFalse(watchList.contains(stock));
    }
}