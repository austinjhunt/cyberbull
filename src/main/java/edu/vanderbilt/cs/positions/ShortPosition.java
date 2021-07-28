/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.positions;

import edu.vanderbilt.cs.Stock;


/*
A "short" position means you're borrowing shares of a stock; you do not own shares.
You open short positions if you believe the price of a stock will decrease in value.
Hedgies do this often in hopes that a company will go bankrupt and they can profit off of its bankruptcy.
If the price of the shares shorted does drop, you can buy the stock at a lower price and
make a profit. If the share price rises and you buy back at the higher price, you incur a loss.
Short selling is for experienced investors. Most traders open long positions.
 */

public class ShortPosition extends Position {
    public ShortPosition(Stock stock, double quantity) {
        super(stock, quantity);
    }
}
