/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

/*
Position class represents a position on a stock, where a position
is a single stock that a trader owns in their portfolio. If you own three
stocks, you "carry three positions". Note that when you open a position on a new
stock, your initial net is always 0. You gain or lose money from your initial
investment based on the stock's performance. You can buy or sell more shares of a stock
after your position is open to update your position.
 */
package edu.vanderbilt.cs.cyberbull.core.position;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.activity.portfolio.operations.OrderOperation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
Class representing a "position" in a portfolio. A position is a single stock
that a trader owns within his or her portfolio, where a portfolio can include many
different positions. A position captures some quantity of shares of a stock, and
some net gain or loss over time from initially being opened. It can be updated by
adding or removing shares of that same stock to the position
over time. You may want to add to your position if the price is low and buying power is high
(i.e. the stock is bullish).
 */

public class Position {
    private final Stock stock;
    private double quantity;
    private double netToday;
    private double netTotal;
    public double currentValue;
    private final LocalDateTime created;
    private LocalDateTime updated;
    private List<OrderOperation> orderOperationList;

    public Position(Stock stock, double quantity, OrderOperation orderOperation){
        // A position should be created by an order operation, which inserts itself into the creation
        // so the position has a record of what order operation created it :)
        this.stock = stock;
        this.quantity = quantity;
        this.netToday = 0;
        this.netTotal = 0;
        this.currentValue = 0;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.orderOperationList = new ArrayList<>();
        this.orderOperationList.add(orderOperation);
    }


    public Position(Stock stock, double quantity){
        this.stock = stock;
        this.quantity = quantity;
        this.netToday = 0;
        this.netTotal = 0;
        this.currentValue = 0;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.orderOperationList = new ArrayList<>();
    }

    public List<OrderOperation> getOrderHistory(){
        return orderOperationList.stream().sorted(
                Comparator.comparing(OrderOperation::getDateTime)).collect(Collectors.toList());
    }

    public double getCurrentValue(){
        return stock.getCurrentPrice() * quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity, OrderOperation orderOperation) {
        this.quantity = quantity;
        this.updated = LocalDateTime.now();
        if (orderOperation != null){
            this.orderOperationList.add(orderOperation);
        }
    }

    public String getCreatedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.created.format(formatter);
    }
    public String getLastUpdatedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.updated.format(formatter);
    }

    public void updateQuantityByValue(double quantity, OrderOperation orderOperation) {
        this.quantity += quantity;
        this.updated = LocalDateTime.now();
        if (orderOperation != null){
            this.orderOperationList.add(orderOperation);
        }
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String toString() {
        return "Position: " + this.quantity + " shares of stock " + this.stock.getSymbol() + " opened on " + this.created.toLocalDate() + " at " +
                this.created.toLocalTime() + " and last updated on " + this.updated.toLocalDate() + " at " +
                this.updated.toLocalTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.quantity, quantity) == 0 && Double.compare(position.netToday, netToday) == 0 && Double.compare(position.netTotal, netTotal) == 0 && stock.equals(position.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, quantity, netToday, netTotal);
    }
}
