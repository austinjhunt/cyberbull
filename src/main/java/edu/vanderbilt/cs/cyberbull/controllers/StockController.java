package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class StockController {
    private DashboardService dashboardService;
    @Autowired
    public StockController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping(path="/stock/{symbol}")
    public String exploreGet(@PathVariable String symbol,  Model model){
        try{
            Optional<Stock> optionalStock = dashboardService.getStock(symbol);
            if (optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                model.addAttribute("news", dashboardService.getBusinessNews(stock.getBusinessName()));
                model.addAttribute("stock", stock );
            }
            return "stock";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @GetMapping(path="/stock/{symbol}/history/monthly")
    public String monthlyHistory(@PathVariable String symbol, Model model){
        try{
            Optional<Stock> optionalStock = dashboardService.getStock(symbol);
            if (optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                ArrayList<JSONObject> history = optionalStock.get().getMonthlyHistory();
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                Iterator<JSONObject> historyIterator = history.iterator();
                while (historyIterator.hasNext()){
                    sb.append(historyIterator.next().toString());
                    if (historyIterator.hasNext()){
                        sb.append(",");
                    }
                }
                sb.append("]");
                return sb.toString();
            }
            return "failed";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @ResponseBody
    @GetMapping(path="/stock/{symbol}/history/weekly")
    public String weeklyHistory(@PathVariable String symbol, Model model){
        try{
            Optional<Stock> optionalStock = dashboardService.getStock(symbol);
            if (optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                ArrayList<JSONObject> history = optionalStock.get().getWeeklyHistory();
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                Iterator<JSONObject> historyIterator = history.iterator();
                while (historyIterator.hasNext()){
                    sb.append(historyIterator.next().toString());
                    if (historyIterator.hasNext()){
                        sb.append(",");
                    }
                }
                sb.append("]");
                return sb.toString();
            }
            return "failed";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @ResponseBody
    @GetMapping(path="/stock/{symbol}/history/daily")
    public String dailyHistory(@PathVariable String symbol, Model model){
        try{
            Optional<Stock> optionalStock = dashboardService.getStock(symbol);
            if (optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                ArrayList<JSONObject> history = optionalStock.get().getDailyHistory();
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                Iterator<JSONObject> historyIterator = history.iterator();
                while (historyIterator.hasNext()){
                    sb.append(historyIterator.next().toString());
                    if (historyIterator.hasNext()){
                        sb.append(",");
                    }
                }
                sb.append("]");
                return sb.toString();
            }
            return "failed";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
