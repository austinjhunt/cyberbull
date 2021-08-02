package edu.vanderbilt.cs.cyberbull.core.random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomWatchListTitleFactoryTest {
    protected static RandomWatchListTitleFactory factory;

    @BeforeAll
    static void setUp(){
        factory = new RandomWatchListTitleFactory();
    }

    @Test
    void generateRandomPhrase() {
        assertFalse(factory.generateRandomPhrase().isBlank());
    }
}