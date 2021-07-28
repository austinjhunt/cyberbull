/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.positions;

import edu.vanderbilt.cs.Stock;


/*
One of the concrete creators in the Factory Method pattern used
for Position object creation. This concrete creator implements
the PositionFactory creator to produce a concrete LongPosition product
(a subclass of Position)
 */
public class LongPositionFactory implements PositionFactory {
    @Override
    public Position createPosition(Stock stock, double quantity) {
        return new LongPosition(stock, quantity);
    }
}
