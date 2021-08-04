package edu.vanderbilt.cs.cyberbull.core;

import edu.vanderbilt.cs.cyberbull.core.account.BrokerageAccount;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.portfolio.Portfolio;
import edu.vanderbilt.cs.cyberbull.core.position.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {
    protected static Portfolio portfolio;
    protected static BrokerageAccount account;
    @BeforeAll
    static void setUp(){
        account = new BrokerageAccount("title","description","1234", "5678");
        account.updateCorePosition(5000000);
        portfolio = new Portfolio(account);
    }

    @Test
    void addPosition() throws InsufficientFundsException {
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
    }

    @Test
    void getPositions() throws InsufficientFundsException {
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
    }

    @Test
    void removePosition() throws InsufficientFundsException {
        Position pos = new Position(new Stock("TSLA"), 10);
        portfolio.addPosition(pos);
        assertTrue(portfolio.getPositions().contains(pos));
        portfolio.removePosition(pos);
        assertFalse(portfolio.getPositions().contains(pos));
    }
}