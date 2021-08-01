package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yahoofinance.histquotes.HistoricalQuote;

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
//                model.addAttribute("dailyHistory", dashboardService.getDailyStockHistory(stock));
//                model.addAttribute("weeklyHistory", dashboardService.getWeeklyStockHistory(stock));
//                model.addAttribute("monthlyHistory", dashboardService.getMonthlyStockHistory(stock));
                model.addAttribute("stock", stock);
            }
            return "stock";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
