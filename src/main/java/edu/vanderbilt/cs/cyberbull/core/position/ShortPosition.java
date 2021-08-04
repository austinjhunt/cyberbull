package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations.OrderOperation;

public class ShortPosition extends Position{
    public ShortPosition(Stock stock, double quantity, OrderOperation orderOperation) {
        super(stock, quantity, orderOperation);
    }

    public ShortPosition(Stock stock, double quantity) {
        super(stock, quantity);
    }
}
