/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/*
Order class represents a buy or sell order on a particular stock; an order can be a market order (immediate action)
or a limit order; Limit/Market orders represented as subclasses of order.

 */
public class OrderOperation implements PortfolioOperation {
    protected String action; // buy or sell
    protected Stock stock;
    protected double quantity;
    public OrderOperation(String action, Stock stock, double quantity){
        this.action = action;
        this.stock = stock;
        this.quantity = quantity;
    }
    public String getAction(){
        return action;
    }
    public Stock getStock(){
        return stock;
    }
    public double getQuantity(){
        return quantity;
    }
    public boolean marketIsOpen(){
        boolean open = false;
        LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            return false;
        } else {
            // TODO: holiday check
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            if (!(hour < 9 || hour > 17 || (hour < 10 && minute < 30) || (hour > 16 && minute >= 30))) {
                open = true;
            }
            return open;
        }
    }
    @Override
    public boolean execute() throws InsufficientFundsException {
        return true; // override!
    }

    @Override
    public double getCurrentSharePrice() {
        return 0;
    }

    @Override
    public double getTransactionTotal() {
        return 0;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }
}
