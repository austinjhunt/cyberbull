package edu.vanderbilt.cs.cyberbull;

import edu.vanderbilt.cs.cyberbull.controllers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CyberbullApplicationTests {

	@Autowired
	private DashboardController dashboardController;
	@Autowired
	private BankAccountController bankAccountController;
	@Autowired
	private BrokerageAccountController brokerageAccountController;
	@Autowired
	private ErrorController errorController;
	@Autowired
	private FragmentsController fragmentsController;
	@Autowired
	private StockController stockController;
	@Autowired
	private TransferController transferController;
	@Autowired
	private WatchListController watchListController;


	@Test
	void contextLoads() {
		assertNotEquals(null, dashboardController);
		assertNotEquals(null, bankAccountController);
		assertNotEquals(null, brokerageAccountController);
		assertNotEquals(null, errorController);
		assertNotEquals(null, fragmentsController);
		assertNotEquals(null, stockController);
		assertNotEquals(null, transferController);
		assertNotEquals(null, watchListController);

	}

}
