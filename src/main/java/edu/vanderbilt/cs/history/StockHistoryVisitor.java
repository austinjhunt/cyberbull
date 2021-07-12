/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.history;

/*
Visitor interface that uses visitor design pattern to build out different
types of Stock Histories (which vary by time interval and length).
 */
public interface StockHistoryVisitor {
    public StockHistory visit();
}
