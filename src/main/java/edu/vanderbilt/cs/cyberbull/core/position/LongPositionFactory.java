package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;

public class LongPositionFactory implements PositionFactory{

    @Override
    public Position createPosition(Stock stock, double quantity) {
        return new LongPosition(stock, quantity);
    }
}
