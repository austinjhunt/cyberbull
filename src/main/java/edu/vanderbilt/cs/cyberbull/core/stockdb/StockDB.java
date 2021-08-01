package edu.vanderbilt.cs.cyberbull.core.stockdb;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import edu.vanderbilt.cs.cyberbull.core.Stock;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StockDB {
    private final ArrayList<Stock> sp500;
    public StockDB() throws IOException, CsvException {
        sp500 = new ArrayList<>();
        parseCSV();
    }
    private void parseCSV() throws IOException, CsvException {
        CSVReader reader = new CSVReader(
                new FileReader("src/main/resources/static/csv/sp500.csv"));
        Iterator<String[]> iter = reader.iterator();
        iter.next(); // header
        while (iter.hasNext()){
            String[] record = iter.next();
            String symbol = record[0];
            String businessName = record[1];
            String industry = record[2];
            Stock stock = new Stock(symbol); // symbol
            stock.setBusinessName(businessName);
            stock.setIndustry(industry);
            sp500.add(stock);
        }
    }
    public List<Stock> getSP500(){
        return sp500;
    }
    public Optional<Stock> getStock(String symbol){
        return sp500.stream().filter(
                stock->stock.getSymbol().equals(symbol)
        ).findFirst();
    }
}
