package edu.vanderbilt.cs.cyberbull.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    protected static Stock stock;

    @BeforeAll
    static void setUp(){
        stock = new Stock("TSLA");
    }


    @Test
    void getCurrentPrice() {
        assertTrue(stock.getCurrentPrice() > 0);
    }

    @Test
    void getSymbol() {
        assertTrue(stock.getSymbol().equals("TSLA"));
    }

    @Test
    void getBusinessName() {
        stock.setBusinessName("Tesla");
        assertTrue(stock.getBusinessName().equals("Tesla"));
    }

    @Test
    void setBusinessName() {
        stock.setBusinessName("Tesla");
        assertTrue(stock.getBusinessName().equals("Tesla"));
    }

    @Test
    void getIndustry() {
        stock.setIndustry("Consumer Discretionary");
        assertTrue(stock.getIndustry().equals("Consumer Discretionary"));
    }

    @Test
    void setIndustry() {
        stock.setIndustry("Consumer Discretionary");
        assertTrue(stock.getIndustry().equals("Consumer Discretionary"));
    }

    @Test
    void compareCurrentPrice() {
        Stock apple = new Stock("AAPL");
        assertTrue(Stock.compareCurrentPrice(apple,stock) < 0);
    }

}