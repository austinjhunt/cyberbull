package edu.vanderbilt.cs.cyberbull.core.stock_history;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DailyHistoryVisitorTest {
    protected static DailyHistoryVisitor dailyHistoryVisitor;
    protected static Stock stock;

    @BeforeAll
    static void setUp(){
        dailyHistoryVisitor = new DailyHistoryVisitor();
        stock = new Stock("TSLA");
    }

    @Test
    void visit() {
        List<HistoricalQuote> history = dailyHistoryVisitor.visit(stock);
        assertFalse(history.isEmpty());
        assertTrue(history.size() > 100);
    }
}