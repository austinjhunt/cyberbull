/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.activity.portfolio.operations;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    protected Account account;
    protected double quantity;
    protected LocalDateTime dateTime;
    protected final double currentSharePrice;
    protected final double transactionTotal;
    public OrderOperation(String action, Stock stock, double quantity, Account account){
        this.action = action + " " + quantity + " shares of " + stock.getSymbol();
        this.stock = stock;
        this.currentSharePrice = stock.getCurrentPrice();
        this.quantity = quantity;
        this.transactionTotal = this.currentSharePrice * this.quantity;
        this.account = account;
    }
    public String getAction(){
        return action;
    }
    protected void setAction(String action){
        this.action = action;
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
    public void execute() throws InsufficientFundsException {
        // no op for superclass; override in subclasses; sets datetime to time of execute() call
    }
    @Override
    public double getCurrentSharePrice() {
        return currentSharePrice;
    }
    @Override
    public double getTransactionTotal() {
        return transactionTotal;
    }
    @Override
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    @Override
    public String getDateTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
