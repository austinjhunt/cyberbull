package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {
    private DashboardService dashboardService;
    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @RequestMapping({"/",""})
    public String home(Model model){
        try{
            List<Stock> allStocks = new ArrayList<>();
            dashboardService.getBrokerageAccounts().forEach(ba -> ba.getPortfolio().getPositions().forEach(p->allStocks.add(p.getStock())));
            model.addAttribute("allstocks", allStocks);
            model.addAttribute("brokerageAccounts", dashboardService.getBrokerageAccounts());
            model.addAttribute("bankAccounts", dashboardService.getBankAccounts());
            return "index";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
