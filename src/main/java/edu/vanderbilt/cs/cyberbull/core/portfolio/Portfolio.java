/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.cyberbull.core.portfolio;


import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.commander.AccountActivity;
import edu.vanderbilt.cs.cyberbull.core.account.commander.account_operations.AccountOperation;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations.MarketOrderOperation;
import edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations.OrderOperation;
import edu.vanderbilt.cs.cyberbull.core.portfolio.portfolio_operations.PortfolioOperation;
import edu.vanderbilt.cs.cyberbull.core.position.Position;

import java.util.ArrayList;
import java.util.List;

/* Portfolio class represents a portfolio which is a collection of positions/investments;
generally these investments can be stocks, bonds, cash, commodities, and other types
of investments.
 */
public class Portfolio {
    List<Position> positionList;
    PortfolioActivity activity;
    Account account;
    public Portfolio(Account account){
        this.positionList = new ArrayList<>();
        // record of portfolio updates
        this.activity = new PortfolioActivity();
        this.account = account;
    }
    public void addPosition(Position position) throws InsufficientFundsException {
        activity.executeOperation(
                new MarketOrderOperation("buy", position.getStock(), position.getQuantity(), account),
                true
        );
        positionList.add(position);
    }
    public void removePosition(Position position) throws InsufficientFundsException {
        // same as selling full quantity of position
        activity.executeOperation(
                new MarketOrderOperation("sell", position.getStock(), position.getQuantity(), account),
                true
        );
        positionList.remove(position);
    }
    public void openPosition(Stock stock, double quantity) throws InsufficientFundsException {
        OrderOperation op = new MarketOrderOperation("buy", stock, quantity, account);
        activity.executeOperation(op, true );
        Position position = new Position(stock, quantity, op);
        positionList.add(position);
    }
    public void buyMore(Position position, double quantity) throws InsufficientFundsException {
        OrderOperation op = new MarketOrderOperation("buy", position.getStock(), quantity, account);
        activity.executeOperation(op, true );
        positionList.remove(position);
        position.updateQuantityByValue(quantity, op);
        positionList.add(position);
    }
    public void sellSome(Position position, double quantity) throws InsufficientFundsException {
        OrderOperation op = new MarketOrderOperation("sell", position.getStock(), quantity, account);
        activity.executeOperation(op , true);
        positionList.remove(position);
        position.updateQuantityByValue(quantity * -1, op);
        positionList.add(position);
    }
    public List<Position> getPositions(){
        return positionList;
    }


}
