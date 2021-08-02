package edu.vanderbilt.cs.cyberbull.core.watchlist.commander;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchListAddOperationTest {
    protected static WatchListAddOperation watchListAddOperation;
    protected static Stock stock;
    protected static WatchList watchList;
    @BeforeAll
    static void setUp(){
        stock = new Stock("TSLA");
        watchList = new WatchList();
        watchListAddOperation = new WatchListAddOperation(stock, watchList);
    }
    @Test
    void execute() {
        watchListAddOperation.execute();
        assert watchList.contains(stock);
    }
}