package edu.vanderbilt.cs.cyberbull.core;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {
    protected static Dashboard dashboard;
    @BeforeAll
    static void setUp(){
        dashboard = new Dashboard();
    }
    @AfterEach
    void tearDown(){
        dashboard.clearBankAccountList();
        dashboard.clearBrokerageAccountList();
    }
    @Test
    void addBankAccount() {
        assertTrue(dashboard.addBankAccount(
                "bank account title",
                "bank account description",
                "123456789",
                "123456789012"
                ));
    }

    @Test
    void removeBankAccount() {
        dashboard.addBankAccount(
                "bank account title",
                "bank account description",
                "123456789",
                "123456789012"
        );
        assertTrue(dashboard.removeBankAccount("123456789012"));
    }

    @Test
    void addBrokerageAccount() {
        assertTrue(dashboard.addBrokerageAccount(
                "brokerage account title",
                "brokerage account description"));

    }

    @Test
    void removeBrokerageAccount() {
        dashboard.addBrokerageAccount(
                "brokerage account title",
                "brokerage account description");
        assertTrue(
                dashboard.removeBrokerageAccount(
                        dashboard.getBrokerageAccountList().get(0).getAccountNumber()
                )
        );
    }

    @Test
    void addRandomBrokerageAccount() {
        assertTrue(dashboard.addRandomBrokerageAccount());
    }

    @Test
    void addRandomBankAccount() {
        assertTrue(dashboard.addRandomBankAccount());
    }

    @Test
    void getBrokerageAccountList() {
        dashboard.addRandomBrokerageAccount();
        dashboard.addRandomBrokerageAccount();
        dashboard.addRandomBrokerageAccount();
        assertEquals(3, dashboard.getBrokerageAccountList().size());
    }

    @Test
    void getBankAccountList() {
        dashboard.addRandomBankAccount();
        dashboard.addRandomBankAccount();
        dashboard.addRandomBankAccount();
        assertEquals(3, dashboard.getBankAccountList().size());
    }

    @Test
    void transfer() {
        dashboard.addRandomBankAccount();
        dashboard.addRandomBrokerageAccount();
        // make sure you insufficient funds exception works
        Account bank = dashboard.getBankAccountList().get(0);
        Account brokerage = dashboard.getBrokerageAccountList().get(0);
//        assertThrows(
//                InsufficientFundsException.class, () -> {
//                    dashboard.transfer(bank, brokerage, 1500);
//                }
//        );
        bank.setBalance(1500);
        dashboard.transfer(bank, brokerage, 500);
        assertEquals(bank.getBalance(), 1000);
        assertEquals(brokerage.getBalance(), 500);
    }
}