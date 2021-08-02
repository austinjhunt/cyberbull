package edu.vanderbilt.cs.cyberbull.core.random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilTest {
    @Test
    void getRandomNumber() {
        int randomNum = RandomUtil.getRandomNumber(0, 10);
        assertTrue(randomNum >= 0 && randomNum < 10);
    }

    @Test
    void randomNumberString() {
        String randomNumberString = RandomUtil.randomNumberString(10);
        assertEquals(10, randomNumberString.length());
    }
}