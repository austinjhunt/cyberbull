package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    protected static Stock stock;
    protected static double quantity;
    protected static Position position;
    @BeforeAll
    static void setUp(){
        stock = new Stock("TSLA");
        quantity = 10;
        position = new Position(stock, quantity);
    }
    @Test
    void getCurrentValue() {
        double expected = stock.getCurrentPrice() * quantity;
        assertEquals(expected, position.getCurrentValue());
    }

    @Test
    void getStock() {
        assertEquals(stock, position.getStock());
    }

    @Test
    void getQuantity() {
        position.setQuantity(quantity);
        assertEquals(quantity, position.getQuantity());
    }

    @Test
    void setQuantity() {
        position.setQuantity(15);
        assertEquals(15,position.getQuantity());
    }

    @Test
    void updateQuantityByValue() {
        position.setQuantity(10);
        position.updateQuantityByValue(5);
        assertEquals(15, position.getQuantity());
    }
}