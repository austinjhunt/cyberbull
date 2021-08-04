# Cyberbull - Brokerage Account Management Meets Design Patterns

![cyberbull](cyberbull.png)
This is a Spring web application called Cyberbull Investment Simulator built for a Vanderbilt University CS5278
(Principles of Software Engineering) final project. It's a reflection of my personal interest in the stock market and is
an exploration of many [Gang of Four design patterns](https://en.wikipedia.org/wiki/Design_Patterns) in the context of
stocks and brokerage account management. The application makes heavy use of **object-oriented** programming techniques
for **structure and organization**, as well as **functional** programming techniques to draw **clear connections**
between the **domain intent** (brokerage account management) and the **computation**. It also largely depends on
the [this Java library](https://github.com/sstrickx/yahoofinance-api) for interacting with the Yahoo Finance API,
plus [this free News API](https://newsapi.org/) for collecting current news filtered by business names.
**Note: the underlying stock database for this application is generated
using [this CSV file](src/main/resources/static/csv/sp500.csv) which only includes the S&P 500.**

## First, the Object Oriented...

### What design patterns are used?

#### [The Command Pattern](https://refactoring.guru/design-patterns/command)

The command pattern -- described in the link above as **a behavioral design pattern that turns a request into a
stand-alone object that contains all information about the request** -- is used heavily for maintaining records of
activity, where "activity" is actually a command invoker that executes and stores concrete implementations of different
operations.

##### At the Account Level

An [AccountActivity Commander](src/main/java/edu/vanderbilt/cs/cyberbull/core/account/commander/AccountActivity.java)
is used to keep a record of account-level activity, namely transfers, where a TransferOperation is a concrete
AccountOperation that gets executed and stored by the Account Activity Commander. This commander is useful for
displaying a history of transfer operations on a dashboard.

##### At the Portfolio Level

A [PortfolioActivity Commander](src/main/java/edu/vanderbilt/cs/cyberbull/core/portfolio/PortfolioActivity.java) is used
to keep a record of portfolio activity, represented as PortfolioOperations. A PortfolioOperation is most generally an
OrderOperation, and for the sake of demonstration it is making use of only the MarketOrderOperation, which represents
a "Market Order" of a stock. The alternative is a Limit Order. Documentation about these order types is provided in the
respective .java files. Moreover, since a portfolio consists of a set of open positions (a position just represents some
investment in some number of shares of a stock), and a position can be updated over time by buying or selling more of
that position's stock, Cyberbull also stores the operations affecting a given position in an operation list for that
position. This means we can view a history of orders at the portfolio level and at the position level (within that
portfolio) on an account dashboard.

#### [The Strategy Pattern](https://refactoring.guru/design-patterns/strategy)

One important thing to note is that while a bank account simply has a balance associated with it, a brokerage account
has both a balance and a core position. A core position represents a balance of uninvested cash just sitting in the
account waiting to be invested or transferred somewhere. The *balance* is the sum of the investment values in the
account's portfolios and the core position. Now, for handling transfers, we are only interested in the transferable
balance of an account, which for a brokerage account is just the core position. So, to find transferable balance of an
account that could be one of two different types, Cyberbull uses the strategy pattern.
The [BalanceFinderContext](src/main/java/edu/vanderbilt/cs/cyberbull/core/account/balancefinder/BalanceFinderContext.java)
class serves as the context which takes in an account and routes the "get the transferable balance" logic to the
appropriate strategy (there's a bank account balance finder strategy which just returns the balance if it's a bank
account, and a brokerage account strategy which returns the core position if it's a brokerage account). This way, the
client doesn't need to know what type an account is, or how to handle that type.

#### [The Factory Method Pattern](https://refactoring.guru/design-patterns/creational-patterns)

With the factory method, you provide an interface for creating objects in a superclass, but you use subclasses to change
the type of object that gets created.

###### Positions

When managing a stock portfolio, you can open different types of positions depending basically on what you are hoping to
profit off of. In a nutshell, if you want to profit off of a business losing money, you can open a short position, AKA
you can "short" the company's stock. Alternatively, and most commonly, you may want to profit off of a company's growth
buy buying shares and selling them at a later date at a higher price; this is called a "long"
position. You can check
out [Investopedia](https://www.investopedia.com/ask/answers/100314/whats-difference-between-long-and-short-position-market.asp)
for more information about the finance side of things. Now, for creating positions within the app, there is a
PositionFactory interface with a createPosition() method, and an IPositionFactory superclass that implements that
interface to create a Position superclass. Below that, there is a ShortPosition class and a LongPosition class,
representing the different subclasses, or types, of market positions. Running parallel, there is a ShortPositionFactory
and a LongPositionFactory responsible for creating those respective types of Position objects.

#### [The Bridge Pattern](https://refactoring.guru/design-patterns/bridge)

The Bridge pattern is used pretty heavily since the obvious goal is to divide the complexity of the underlying code
base (found in the core/ folder) from the client, or the controller. The Dashboard object and DashboardService serves
largely as this bridge of communication between the simple front end and complex back end by persistently storing
backend implementations as attributes (i.e. *dependency injection*) which get called upon to handle front end requests.
The dashboard service is the most persistent object across the controllers, allowing state to be shared and maintained.

#### [The Visitor Pattern](https://refactoring.guru/design-patterns/visitor)

The visitor pattern, as mentioned in the link above, "lets you separate algorithms from the objects on which they
operate". In this project, the visitor pattern is used to easily provide multiple methods of accessing price history of
a stock. Specifically, the visitor (one of the concrete implementations of
the [StockHistoryVisitor](src/main/java/edu/vanderbilt/cs/cyberbull/core/stock_history/StockHistoryVisitor.java)
interface) generates the price history of a given stock at an interval determined by the type of visitor, e.g. daily,
monthly, or weekly. That is, different types of visitors "visit" the historical data to generate a
differently-intervaled lists of historical price points.

#### [The Proxy Pattern](https://refactoring.guru/design-patterns/proxy)

As discussed in the link above, a proxy controls access to some original object, allowing you to perform something
either before or after the request reaches that original object. In the context of this project, the proxy pattern comes
to life with the [Stock](src/main/java/edu/vanderbilt/cs/cyberbull/core/Stock.java) class. Why? Well, Cyberbull is
largely dependent on YahooFinance, specifically YahooFinance logic specific to certain stocks, e.g. opens, closes,
volumes, general price information, etc. The proxy pattern is seen in how the internal Stock class is used to interact
with and process information from the Yahoo Finance API. It's also seen in the use of
the [News Finder](src/main/java/edu/vanderbilt/cs/cyberbull/core/news/NewsFinder.java) which utilizes the SDK
for [this free news API]((https://newsapi.org/)) to send customized requests to filter out news and handle custom
formatting of the responses from that API.

#### [The Chain of Responsibility Pattern](https://refactoring.guru/design-patterns/chain-of-responsibility)

This is pretty typical of web applications where requests get routed from a front end down through a chain of backend
modules.

#### [The Iterator Pattern](https://refactoring.guru/design-patterns/iterator)

The iterator pattern is used to iterate over historical quotes of a stock and build a stringified JSON object. Also, the
iterator() method of the CSVReader class within the Stock Database
class ([StockDB]((src/main/java/edu/vanderbilt/cs/cyberbull/core/stockdb/StockDB.java)) is used to handle the iterative
parsing of a static CSV file of the S&P 500 stocks. This is the class the forms the underlying primary database of
stocks for the application. *Note: it currently only includes the S&P 500 Stocks but can be extended.*

## Now, the Functional Programming...

This project makes very heavy use of functional programming with streams, filters, tons of lambda expressions, and other
items in this category. To provide some key examples:

1. When making a trade (i.e. buying or selling a stock), Cyberbull uses functional programming to quickly filter and
   find out if there is already an existing open position for the stock being bought (or sold):

``` 
// first determine if we already have a position for this symbol :)
Optional<Position> positionOptional = account.getPortfolio().getPositions().stream().filter(
        position-> position.getStock().getSymbol().equals(symbol)
).findFirst();
```

2. To get the balance of a brokerage account, as mentioned above, we need the sum of all of the total values of the
   current open positions plus the core position (uninvested, sitting money) of the account.

```
@Override
public double getBalance() {
    return this.corePosition + getPortfolio().getPositions().stream().map(Position::getCurrentValue).reduce(
            0.0, Double::sum
    ); // uninvested + invested
}
```

3. To get a specific stock by a symbol from the Stock Database, Cyberbull streams the list of SP500 stocks and filter
   with a predicate asking if the current stock's symbol matches the input.

```
public Optional<Stock> getStock(String symbol){
    return sp500.stream().filter(
            stock->stock.getSymbol().equals(symbol)
    ).findFirst();
}
```

4. To quickly build a list of JSON objects representing key values of historical quotes of a given stock, Cyberbull
   streams the output of the visitor (there's a visitor for monthly history, daily history, and weekly history) and add
   the output of `buildQuoteJson(quote)` to a history list for each historical quote object.

``` 
public ArrayList<JSONObject> getMonthlyHistory(){
    ArrayList<JSONObject> history = new ArrayList<>();
    MonthlyHistoryVisitor monthlyHistoryVisitor = new MonthlyHistoryVisitor();
    monthlyHistoryVisitor.visit(this).stream().forEachOrdered(
            quote->history.add(buildQuoteJson(quote))
    );
    return history;
}
```

5. To get the total value of a given watch list (where a watch list is a collection of stocks that you are interested in
   but perhaps not invested in yet), we stream the stocks in the watchlist and use a method reference to the
   getCurrentPrice method of Stock, then use reduce to get the sum of the doubles.

``` 
public double getTotalValue(){
    return this.stocks.stream().map(Stock::getCurrentPrice).reduce(0.0, Double::sum);
}
```

6. Here's a fun one. After you have built a series of brokerage accounts and have invested in stocks across different
   portfolios, the app does the following in order to collect a list of all of the stocks you've invested in across your
   accounts.

``` 
List<Stock> allStocks = new ArrayList<>();
dashboardService.getBrokerageAccounts().forEach(
    ba -> ba.getPortfolio().getPositions().forEach(
        p->allStocks.add(p.getStock())));
```
   