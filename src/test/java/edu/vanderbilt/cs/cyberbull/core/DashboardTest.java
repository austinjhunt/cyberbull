package edu.vanderbilt.cs.cyberbull.core;

import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {
    protected static Dashboard dashboard;
    @BeforeAll
    static void setUp() throws IOException, CsvException {
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
    void transfer() throws InsufficientFundsException {
        dashboard.addRandomBankAccount();
        dashboard.addRandomBrokerageAccount();
        // make sure you insufficient funds exception works
        Account bank = dashboard.getBankAccountList().get(0);
        Account brokerage = dashboard.getBrokerageAccountList().get(0);
        bank.setBalance(1500);
        dashboard.transfer(bank, brokerage, 500);
        assertEquals(1000,bank.getBalance());
        assertEquals(500, brokerage.getBalance());
    }
}