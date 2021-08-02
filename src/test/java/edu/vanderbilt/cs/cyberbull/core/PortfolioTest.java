package edu.vanderbilt.cs.cyberbull.core;

import edu.vanderbilt.cs.cyberbull.core.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {
    protected static Portfolio portfolio;
    @BeforeAll
    static void setUp(){
        portfolio = new Portfolio();
    }

    @Test
    void addPosition() {
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
    }

    @Test
    void getPositions(){
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
    }

    @Test
    void removePosition() {
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
        portfolio.removePosition(pos);
        assertFalse(portfolio.getPositions().contains(pos));
    }
}