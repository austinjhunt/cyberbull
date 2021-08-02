package edu.vanderbilt.cs.cyberbull.core.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchListFactoryTest {
    protected WatchListFactory watchListFactory;
    @BeforeEach
    void setUp(){
        watchListFactory = new WatchListFactory();
    }
    @Test
    void createWatchList() {
        String title = "Meme stocks";
        WatchList watchList = watchListFactory.createWatchList(title);
        assertEquals(title, watchList.getTitle());
    }
}