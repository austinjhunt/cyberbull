package edu.vanderbilt.cs.cyberbull.core.account;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrokerageAccountFactoryTest {
    protected static BrokerageAccountFactory brokerageAccountFactory;

    @BeforeAll
    static void setUp(){
        brokerageAccountFactory = new BrokerageAccountFactory();
    }

    @Test
    void createAccount() {
        String title = "title";
        String description = "description";
        String routingNumber = "123456789";
        String accountNumber = "123456789012";
        Account account = brokerageAccountFactory.createAccount(
                title,
                description,
                routingNumber,
                accountNumber
        );
        assertEquals(title,account.getTitle());
        assertEquals(description, account.getDescription());
        assertEquals(routingNumber, account.getRoutingNumber());
        assertEquals(accountNumber, account.getAccountNumber());
    }

    @Test
    void createRandomAccount() {
        Account account = brokerageAccountFactory.createRandomAccount();
        assertFalse(account.getTitle().isBlank());
        assertTrue(account.getDescription().isBlank());
        assertEquals(12, account.getAccountNumber().length());
        assertEquals(9, account.getRoutingNumber().length());
    }

}