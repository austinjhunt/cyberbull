/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.positions;

import edu.vanderbilt.cs.Stock;

public interface PositionFactory {
    public Position createPosition(Stock stock, double quantity);
}
