# Cyberbull - Brokerage Account Management Meets Design Patterns
![cyberbull](cyberbull.png)
This is a Spring web application called Cyberbull Investment Simulator built for a Vanderbilt University CS5278 
(Principles of Software Engineering) final project. It's a reflection of my personal interest in the stock market 
and is an exploration of many [Gang of Four design 
patterns](https://en.wikipedia.org/wiki/Design_Patterns) in the context of stocks and brokerage account management. 
The application makes heavy use of **object-oriented** programming techniques for **structure and organization**, as 
well as **functional** programming techniques to draw **clear connections** between the **domain intent** (brokerage account 
management) and the **computation**.
## First, the Object Oriented...
### What design patterns are used? 
#### [The Command Pattern](https://refactoring.guru/design-patterns/command) 
The command pattern -- described in the link above as **a behavioral design pattern that turns a request into a 
stand-alone object that contains all information about the request** -- is used heavily for maintaining records of 
activity, where "activity" is actually a command invoker that executes and stores concrete implementations of different 
operations.
##### At the Account Level
An [AccountActivity Commander](src/main/java/edu/vanderbilt/cs/cyberbull/core/account/commander/AccountActivity.java)
is used to keep a record of account-level activity, namely transfers, where a 
TransferOperation is a concrete AccountOperation that gets executed and stored by the Account Activity Commander. 
This commander is useful for displaying a history of transfer operations on a dashboard. 
##### At the Portfolio Level 
A [PortfolioActivity Commander](src/main/java/edu/vanderbilt/cs/cyberbull/core/portfolio/PortfolioActivity.java) is 
used to keep a record of portfolio activity, represented as PortfolioOperations. A PortfolioOperation is most 
generally an OrderOperation, and for the sake of demonstration we are making use of only the MarketOrderOperation, 
which represents a "Market Order" of a stock. The alternative is a Limit Order. Documentation about these order 
types is provided in the respective .java files. Moreover, since a portfolio consists of a set of open positions (a 
position just represents some investment in some number of shares of a stock), and a position can be updated over 
time by buying or selling more of that position's stock, we also store the operations affecting a given position in 
an operation list for that position. This means we can view a history of orders at the portfolio level and at the 
position level (within that portfolio) on an account dashboard.

#### [The Factory Method Pattern](https://refactoring.guru/design-patterns/creational-patterns)
With the factory method, you provide an interface for creating objects in a superclass, but you use subclasses to 
change the type of object that gets created. 
###### Positions
When managing a stock portfolio, you can open different types of positions depending basically on what you are 
hoping to profit off of. In a nutshell, if you want to profit off of a business losing money, you can open a short 
position, AKA you can "short" the company's stock. Alternatively, and most commonly, you may want to profit off of a 
company's growth buy buying shares and selling them at a later date at a higher price; this is called a "long" 
position. You can check out [Investopedia](https://www.investopedia.com/ask/answers/100314/whats-difference-between-long-and-short-position-market.asp)
for more information about the finance side of things. Now, for creating positions within the app, we have a 
PositionFactory interface with a createPosition() method, and an IPositionFactory superclass that implements that 
interface to create a Position superclass. Below that, we have a ShortPosition class and a LongPosition class, 
representing the different subclasses, or types, of market positions. Running parallel, we have a 
ShortPositionFactory and a LongPositionFactory responsible for creating those respective types of Position objects.  

#### [The Bridge Pattern](https://refactoring.guru/design-patterns/bridge)
The Bridge pattern is used pretty heavily since we definitely want to divide the complexity of the underlying code 
base (found in the core/ folder) from the client, or the controller. The Dashboard object and DashboardService 
serves largely as this bridge of communication between the simple front end and complex back end by persistently 
storing backend implementations as attributes (i.e. *dependency injection*) which get called upon to handle front end 
requests. The dashboard service is the most persistent object across the controllers, allowing state to be shared and maintained. 
 
#### [The Visitor Pattern](https://refactoring.guru/design-patterns/visitor)
The visitor pattern, as mentioned in the link above, "lets you separate algorithms from the objects on which they 
operate". In this project, the visitor pattern is used to easily provide multiple methods of accessing price history 
of a stock. Specifically, the visitor (one of the concrete implementations of the [StockHistoryVisitor](src/main/java/edu/vanderbilt/cs/cyberbull/core/stock_history/StockHistoryVisitor.java) interface) generates the price history of a given 
stock at an interval determined by the type of visitor, e.g. daily, monthly, or weekly. That is, different types of 
visitors "visit" the historical data to generate a differently-intervaled lists of historical price points. 

## Now, the Functional Programming...

