/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;

/*
Having a “long” position in a security means that you own the security.
You've bought shares with the hope of selling at a higher price.
 */
public class LongPosition extends Position{
    public LongPosition(Stock stock, double quantity) {
        super(stock, quantity);
    }
}
