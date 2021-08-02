package edu.vanderbilt.cs.cyberbull.core.random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomBankAccountTitleFactoryTest {
    protected static RandomBankAccountTitleFactory factory;

    @BeforeAll
    static void setUp(){
        factory = new RandomBankAccountTitleFactory();
    }

    @Test
    void generateRandomPhrase() {
        assertFalse(factory.generateRandomPhrase().isBlank());
    }
}