package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;

public class PositionFactoryImpl implements PositionFactory{
    @Override
    public Position createPosition(Stock stock, double quantity) {
        return new Position(stock, quantity);
    }
}
