package edu.vanderbilt.cs.cyberbull.core.stock_history;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyHistoryVisitorTest {
    protected static MonthlyHistoryVisitor monthlyHistoryVisitor;
    protected static Stock stock;

    @BeforeAll
    static void setUp(){
        monthlyHistoryVisitor = new MonthlyHistoryVisitor();
        stock = new Stock("TSLA");
    }

    @Test
    void visit() {
        List<HistoricalQuote> history = monthlyHistoryVisitor.visit(stock);
        assertFalse(history.isEmpty());
        try {
            assertEquals(12, history.size());
        } catch (AssertionError e){
            assertEquals(13, history.size());
        }
    }
}