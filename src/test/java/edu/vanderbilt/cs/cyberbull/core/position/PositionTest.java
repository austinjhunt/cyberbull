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
        double lowVal = expected - 10;
        double highVal = expected + 10;
        assertTrue(expected > lowVal && expected < highVal);
    }

    @Test
    void getStock() {
        assertEquals(stock, position.getStock());
    }

    @Test
    void getQuantity() {
        position.setQuantity(quantity, null);
        assertEquals(quantity, position.getQuantity());
    }

    @Test
    void setQuantity() {
        position.setQuantity(15, null);
        assertEquals(15,position.getQuantity());
    }

    @Test
    void updateQuantityByValue() {
        position.setQuantity(10, null);
        position.updateQuantityByValue(5, null);
        assertEquals(15, position.getQuantity());
    }
}