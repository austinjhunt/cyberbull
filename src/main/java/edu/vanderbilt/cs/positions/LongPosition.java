/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.positions;

import edu.vanderbilt.cs.Stock;

/*
Having a “long” position in a security means that you own the security.
You've bought shares with the hope of selling at a higher price.
 */
public class LongPosition extends Position{
    public LongPosition(Stock stock, double quantity) {
        super(stock, quantity);
    }
}
