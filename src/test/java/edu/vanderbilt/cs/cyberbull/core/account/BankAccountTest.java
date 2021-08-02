package edu.vanderbilt.cs.cyberbull.core.account;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.commander.ActivityCommander;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    protected BankAccount account;
    @BeforeEach
    void setUp(){
        account = new BankAccount();
    }

    @Test
    void setBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void updateBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
        account.updateBalance(50);
        assertEquals(550, account.getBalance());
    }

    @Test
    void getRoutingNumber() {
        String routingNum = "123456789";
        account.setRoutingNumber(routingNum);
        assertEquals(routingNum, account.getRoutingNumber());
    }

    @Test
    void getAccountNumber() {
        String accountNum = "123456789012";
        account.setAccountNumber(accountNum);
        assertEquals(accountNum, account.getAccountNumber());
    }

    @Test
    void getDescription() {
        String description = "My Account";
        account.setDescription(description);
        assertEquals(description, account.getDescription());
    }

    @Test
    void setRoutingNumber() {
        String accountNum = "123456789012";
        account.setAccountNumber(accountNum);
        assertEquals(accountNum, account.getAccountNumber());
    }

    @Test
    void setAccountNumber() {
        String accountNum = "123456789012";
        account.setAccountNumber(accountNum);
        assertEquals(accountNum, account.getAccountNumber());
    }

    @Test
    void setDescription() {
        String description = "My Account";
        account.setDescription(description);
        assertEquals(description, account.getDescription());
    }

    @Test
    void setTitle() {
        String title = "My Account";
        account.setTitle(title);
        assertEquals(title, account.getTitle());
    }

    @Test
    void getTitle() {
        String title = "My Account";
        account.setTitle(title);
        assertEquals(title, account.getTitle());
    }

    @Test
    void getBalance() {
        assertEquals(0, account.getBalance());
        account.setBalance(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void getActivity() {
        assertEquals(ActivityCommander.class, account.getActivity().getClass());
    }

    @Test
    void setActivity() {
        ActivityCommander activityCommander = new ActivityCommander();
        account.setActivity(activityCommander);
        assertEquals(activityCommander, account.getActivity());
    }
}