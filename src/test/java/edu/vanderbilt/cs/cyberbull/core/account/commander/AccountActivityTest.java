package edu.vanderbilt.cs.cyberbull.core.account.commander;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.*;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.TransferOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountActivityTest {
    protected AccountActivity accountActivity;
    protected Account account;
    protected Account accountTransferSource;
    protected BrokerageAccountFactory brokerageAccountFactory;
    protected BankAccountFactory bankAccountFactory;
    protected TransferOperation transferOperation;
    protected Stock stock;
    protected double quantity;
    protected double limitPrice;
    protected double transferAmount;
    protected double initialSourceBalance;
    @BeforeEach
    void setUp(){
        quantity = 10;
        limitPrice = 100;
        transferAmount = 5000;
        initialSourceBalance = 10000;
        brokerageAccountFactory = new BrokerageAccountFactory();
        bankAccountFactory = new BankAccountFactory();
        account = brokerageAccountFactory.createRandomAccount();
        accountTransferSource = bankAccountFactory.createRandomAccount();
        accountTransferSource.setBalance(initialSourceBalance);
        stock = new Stock("TSLA");
        accountActivity = new AccountActivity();
        transferOperation = new TransferOperation(
                accountTransferSource,
                account,
                transferAmount
        );
    }

    @Test
    void getOperations() {
        accountActivity.executeOperation(transferOperation, true);
        assertEquals(1, accountActivity.getOperations().size());
    }

    @Test
    void executeOperation() {
        accountActivity.executeOperation(transferOperation, true);
        assertEquals(5000, account.getBalance());
        assertEquals(5000, accountTransferSource.getBalance());
    }
}