/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;

/*
One of the concrete creators in the Factory Method pattern used
for Position object creation. This concrete creator implements
the PositionFactory creator to produce a concrete ShortPosition product
(a subclass of Position)
 */
public class ShortPositionFactory implements PositionFactory {
    public Position createPosition(Stock stock, double quantity) {
        return new ShortPosition(stock, quantity);
    }
}
