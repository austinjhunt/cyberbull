package edu.vanderbilt.cs.cyberbull.core.watchlist;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WatchListTest {
    protected static WatchList watchList;
    @BeforeAll
    static void setUp(){
        watchList = new WatchList();
    }
    @AfterEach
    void tearDown(){
        watchList.clear();
    }
    @Test
    void getId() {
        assertEquals(10, watchList.getId().length());
    }

    @Test
    void setId() {
        watchList.setId("0123456789");
        assertEquals("0123456789", watchList.getId());
    }

    @Test
    void setStocks() {
        ArrayList<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("TSLA"));
        stocks.add(new Stock("AAPL"));
        watchList.setStocks(stocks);
        assertEquals(
                stocks, watchList.getStocks()
        );
    }

    @Test
    void getTitle() {
        String title = "My Watchlist";
        watchList.setTitle(title);
        assertEquals(title, watchList.getTitle());
    }

    @Test
    void setTitle() {
        String title = "My Watchlist";
        watchList.setTitle(title);
        assertEquals(title, watchList.getTitle());
    }

    @Test
    void add() {
        Stock tsla = new Stock("TSLA");
        assertTrue(watchList.add(tsla));
    }

    @Test
    void remove() {
        Stock tsla = new Stock("TSLA");
        assertTrue(watchList.add(tsla));
        assertTrue(watchList.remove(tsla));
    }

    @Test
    void getStocks() {
        ArrayList<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("TSLA"));
        stocks.add(new Stock("AAPL"));
        watchList.setStocks(stocks);
        assertEquals(
                stocks, watchList.getStocks()
        );
    }


    @Test
    void getHighestPriceStock() {
        Stock aapl = new Stock("AAPL");
        Stock tsla = new Stock("TSLA");
        Stock amzn = new Stock("AMZN");
        Stock highest = aapl;
        if (tsla.getCurrentPrice() > highest.getCurrentPrice()){
            highest = tsla;
        }
        if (amzn.getCurrentPrice() > highest.getCurrentPrice()){
            highest = amzn;
        }
        watchList.add(aapl);
        watchList.add(tsla);
        watchList.add(amzn);
        Optional<Stock> highestInWatchlist = watchList.getHighestPriceStock();
        if (highestInWatchlist.isPresent()){
            assertEquals(highest, highestInWatchlist.get());
        } else{
            fail();
        }

    }

    @Test
    void getLowestPriceStock() {
        Stock aapl = new Stock("AAPL");
        Stock tsla = new Stock("TSLA");
        Stock amzn = new Stock("AMZN");
        Stock lowest = aapl;
        if (tsla.getCurrentPrice() < lowest.getCurrentPrice()){
            lowest = tsla;
        }
        if (amzn.getCurrentPrice() < lowest.getCurrentPrice()){
            lowest = amzn;
        }
        watchList.add(aapl);
        watchList.add(tsla);
        watchList.add(amzn);
        Optional<Stock> lowestInWatchlist = watchList.getLowestPriceStock();
        if (lowestInWatchlist.isPresent()){
            assertEquals(lowest, lowestInWatchlist.get());
        } else{
            fail();
        }
    }

    @Test
    void contains() {
        Stock tsla = new Stock("TSLA");
        watchList.add(tsla);
        assertTrue(watchList.contains(tsla));
    }

    @Test
    void getTotalValue() {
        Stock aapl = new Stock("AAPL");
        Stock tsla = new Stock("TSLA");
        Stock amzn = new Stock("AMZN");
        double total = aapl.getCurrentPrice() + tsla.getCurrentPrice() + amzn.getCurrentPrice();
        watchList.add(aapl);
        watchList.add(tsla);
        watchList.add(amzn);
        // might fail if highly volatile :(, two separate points in time
        assertEquals(total, watchList.getTotalValue());
    }
}