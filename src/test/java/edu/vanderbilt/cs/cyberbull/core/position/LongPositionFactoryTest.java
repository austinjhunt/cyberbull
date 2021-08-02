package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongPositionFactoryTest {
    protected static LongPositionFactory factory;
    protected static Stock stock;
    protected static double quantity;
    @BeforeAll
    static void setUp(){
        factory = new LongPositionFactory();
        stock = new Stock("TSLA");
        quantity = 10;
    }
    @Test
    void createPosition() {
        Position position = factory.createPosition(stock, quantity);
        assertEquals(stock, position.getStock());
        assertEquals(quantity, position.getQuantity());
        assertEquals(LongPosition.class, position.getClass());
    }
}