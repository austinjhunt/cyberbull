package edu.vanderbilt.cs.cyberbull.core.account.commander;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.*;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.LimitOrderOperation;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.MarketOrderOperation;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.TransferOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.error.Mark;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCommanderTest {
    protected ActivityCommander activityCommander;
    protected LimitOrderOperation limitOrderOperation;
    protected Account account;
    protected Account accountTransferSource;
    protected BrokerageAccountFactory brokerageAccountFactory;
    protected BankAccountFactory bankAccountFactory;
    protected MarketOrderOperation marketOrderOperation;
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
        activityCommander = new ActivityCommander();
        limitOrderOperation = new LimitOrderOperation(
                "buy",
                stock,
                quantity,
                limitPrice,
                account
        );
        marketOrderOperation = new MarketOrderOperation(
                "buy",
                stock,
                quantity,
                account
        );
        transferOperation = new TransferOperation(
                accountTransferSource,
                account,
                transferAmount
        );
    }

    @Test
    void getOperations() {
        activityCommander.executeOperation(marketOrderOperation,true);
        activityCommander.executeOperation(limitOrderOperation, true);
        activityCommander.executeOperation(transferOperation, true);
        assertEquals(3,activityCommander.getOperations().size());
    }

    @Test
    void executeOperation() {
        activityCommander.executeOperation(transferOperation, true);
        assertEquals(5000, account.getBalance());
        assertEquals(5000, accountTransferSource.getBalance());
    }
}