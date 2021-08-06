package edu.vanderbilt.cs.cyberbull.core.couchbase.stockdb;

import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockDBTest {
    protected static StockDB stockDB;

    @BeforeAll
    static void setUp() throws IOException, CsvException {
        stockDB = new StockDB();
    }


    @Test
    void getSP500() {
        List<Stock> stocks = stockDB.getSP500();
        assertTrue(stocks.size() > 0);
    }

    @Test
    void getStock() {
        assertTrue(stockDB.getStock("TSLA").isPresent());
        assertFalse(stockDB.getStock("Nonsense").isPresent());
    }
}