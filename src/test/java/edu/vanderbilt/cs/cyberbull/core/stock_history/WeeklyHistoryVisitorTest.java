package edu.vanderbilt.cs.cyberbull.core.stock_history;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeeklyHistoryVisitorTest {
    protected static WeeklyHistoryVisitor weeklyHistoryVisitor;
    protected static Stock stock;

    @BeforeAll
    static void setUp(){
        weeklyHistoryVisitor = new WeeklyHistoryVisitor();
        stock = new Stock("TSLA");
    }

    @Test
    void visit() {
        List<HistoricalQuote> history = weeklyHistoryVisitor.visit(stock);
        assertFalse(history.isEmpty());
        try {
            assertEquals(52, history.size());
        } catch (AssertionError e){
            assertEquals(53, history.size());
        }
    }
}