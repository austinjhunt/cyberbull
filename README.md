# Cyberbull - Brokerage Account Management Meets Design Patterns
![cyberbull](cyberbull.png)
This is a Java application built for a Vanderbilt University CS5278 (Principles of Software Engineering) final project. 
It's a reflection of my personal interest in the stock market and is an exploration of many [Gang of Four design 
patterns](https://en.wikipedia.org/wiki/Design_Patterns) in the context of stocks and brokerage account management.  
The application makes heavy use of **object-oriented** programming techniques for **structure and organization**, as 
well as **functional** programming techniques to draw **clear connections** between the **domain intent** (brokerage account 
management) and the **computation**.

## What design patterns are used?
### [The Command Pattern](https://refactoring.guru/design-patterns/command) 
The command pattern -- described in the link above as **a behavioral design pattern that turns a request into a 
stand-alone object that contains all information about the request** -- is used heavily for maintaining account 
activity, where "activity" is actually a command invoker that stores (for optional undo/redo) concrete implementations 
of an ["AccountOperation"](src/main/java/edu/vanderbilt/cs/account/commander/AccountOperation.java) interface; an 
account operation could be a market or limit order of some quantity of shares of a stock, or perhaps a fund transfer 
to or from another account. These concrete operation implementations are represented with the 
following classes: 
* ["LimitOrderOperation"](src/main/java/edu/vanderbilt/cs/account/commander/LimitOrderOperation.java) - representing 
  a limit order, or an order to buy or sell some shares of a stock at either the limit price or better (lower if 
  buying, higher if selling)
* ["MarketOrderOperation"](src/main/java/edu/vanderbilt/cs/account/commander/MarketOrderOperation.java) - 
  representing a market order, or an order to buy or sell a stock immediately (now if market is open, or at market 
  open if currently closed)
* ["TransferOperation"](src/main/java/edu/vanderbilt/cs/account/commander/TransferOperation.java) - representing a 
  fund transfer between accounts (most generally between a brokerage account and a bank account)
  
### [The Visitor Pattern](https://refactoring.guru/design-patterns/visitor)
The visitor pattern, as mentioned in the link above, "lets you separate algorithms from the objects on which they 
operate". In this project, the visitor pattern is used to easily provide multiple methods of accessing price history 
of a stock. Specifically, the visitor (one of the concrete implementations of the [StockHistoryVisitor](src/main/java/edu/vanderbilt/cs/history/StockHistoryVisitor.java) interface) generates the price history of a given 
stock at an interval determined by the type of visitor, e.g. daily, monthly, or weekly. That is, different types of 
visitors "visit" the historical data to generate a differently-intervaled lists of historical price points. 
