package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.portfolio.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrokerageAccountTest {
    protected  BrokerageAccount account;
    @BeforeEach
    void setUp(){
        account= new BrokerageAccount();
    }

    @Test
    void setTitle() {
        String title = "My Account";
        account.setTitle(title);
        assertEquals(title, account.getTitle());
    }

    @Test
    void setDescription() {
        String description = "My Account";
        account.setDescription(description);
        assertEquals(description, account.getDescription());
    }

    @Test
    void setRoutingNumber() {
        String routingNum = "123456789";
        account.setRoutingNumber(routingNum);
        assertEquals(routingNum, account.getRoutingNumber());
    }

    @Test
    void setAccountNumber() {
        String accountNum = "123456789012";
        account.setAccountNumber(accountNum);
        assertEquals(accountNum, account.getAccountNumber());
    }

    @Test
    void getBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void setBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void updateBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
        account.updateBalance(50);
        assertEquals(550, account.getBalance());
    }

    @Test
    void getRoutingNumber() {
        String routingNum = "123456789";
        account.setRoutingNumber(routingNum);
        assertEquals(routingNum, account.getRoutingNumber());
    }

    @Test
    void getAccountNumber() {
        String accountNum = "123456789012";
        account.setAccountNumber(accountNum);
        assertEquals(accountNum, account.getAccountNumber());
    }

    @Test
    void getDescription() {
        String description = "My Account";
        account.setDescription(description);
        assertEquals(description, account.getDescription());
    }

    @Test
    void getTitle() {
        String title = "My Account";
        account.setTitle(title);
        assertEquals(title, account.getTitle());
    }

    @Test
    void getPortfolio() {
        assertEquals(Portfolio.class, account.getPortfolio().getClass());
    }

    @Test
    void getWatchlists() {
        assertEquals(0, account.getWatchlists().size());
        WatchList watchList = new WatchList();
        Stock stock = new Stock("TSLA");
        watchList.add(stock);
        account.addWatchlist(watchList);
        assertTrue(account.getWatchlists().get(0).contains(stock));
    }

    @Test
    void addWatchlist() {
        WatchList watchList = new WatchList();
        Stock stock = new Stock("TSLA");
        watchList.add(stock);
        account.addWatchlist(watchList);
        assertTrue(account.getWatchlists().get(0).contains(stock));
    }

    @Test
    void addStockToWatchList() {
        WatchList watchList = new WatchList();
        Stock stock = new Stock("TSLA");
        account.addWatchlist(watchList);
        account.addStockToWatchList(stock, watchList);
        assertTrue(account.getWatchlists().get(0).contains(stock));
    }

    @Test
    void removeStockFromWatchlist() {
        WatchList watchList = new WatchList();
        Stock stock = new Stock("TSLA");
        account.addWatchlist(watchList);
        account.addStockToWatchList(stock, watchList);
        assertTrue(account.getWatchlists().get(0).contains(stock));
        account.removeStockFromWatchlist(stock, watchList);
        assertFalse(account.getWatchlists().get(0).contains(stock));
    }

    @Test
    void setPortfolio() {
        Portfolio portfolio = new Portfolio(account);
        account.setPortfolio(portfolio);
        assertEquals(portfolio, account.getPortfolio());
    }

    @Test
    void addWatchList() {
        WatchList watchList = new WatchList();
        account.addWatchlist(watchList);
        assertEquals(watchList, account.getWatchlists().get(0));
    }
}