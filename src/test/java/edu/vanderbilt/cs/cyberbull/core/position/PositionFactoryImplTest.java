package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionFactoryImplTest {
    private PositionFactory positionFactory;
    @BeforeEach
    void setUp(){
        positionFactory = new IPositionFactory();
    }
    @Test
    void createPosition() {
        Stock stock = new Stock("TSLA");
        double quantity = 5;
        Position position = positionFactory.createPosition(stock,quantity);
        assertEquals(stock, position.getStock());
        assertEquals(quantity, position.getQuantity());
    }
}