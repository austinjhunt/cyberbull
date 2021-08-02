package edu.vanderbilt.cs.cyberbull.core.watchlist.commander;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchListCommanderTest {

    protected static WatchListCommander watchListCommander;
    protected static WatchListAddOperation watchListAddOperation;
    protected static WatchListRemoveOperation watchListRemoveOperation;
    protected static Stock stock;
    protected static WatchList watchList;
    @BeforeAll
    static void setUp(){
        stock = new Stock("TSLA");
        watchList = new WatchList();
        watchListAddOperation = new WatchListAddOperation(stock, watchList);
        watchListRemoveOperation = new WatchListRemoveOperation(stock, watchList);
        watchListCommander = new WatchListCommander();
    }
    @AfterEach
    void tearDown(){
        watchList.clear();
        watchListCommander.clearOperations();
    }

    @Test
    void getOperations() {
        assertEquals(0, watchListCommander.getOperations().size());
        watchListCommander.executeOperation(watchListAddOperation, false);
        assertEquals(0, watchListCommander.getOperations().size());
        watchListCommander.executeOperation(watchListAddOperation, true);
        assertEquals(1, watchListCommander.getOperations().size());
        assertTrue(watchListCommander.getOperations().contains(watchListAddOperation));
    }

    @Test
    void executeOperation() {
        watchListCommander.executeOperation(watchListAddOperation, true);
        assertEquals(1, watchListCommander.getOperations().size());
        assertTrue(watchListCommander.getOperations().contains(watchListAddOperation));
        assertTrue(watchList.contains(stock));

        watchListCommander.executeOperation(watchListRemoveOperation, true);
        assertEquals(2, watchListCommander.getOperations().size());
        assertTrue(watchListCommander.getOperations().contains(watchListRemoveOperation));
        assertFalse(watchList.contains(stock));
    }
}