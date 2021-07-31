package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.core.watchlist.WatchList;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class WatchListController {
    private DashboardService dashboardService;
    @Autowired
    public WatchListController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/watchlists/create/{brokerageAccountNumber}")
    public String createWatchListGet(@PathVariable String brokerageAccountNumber, Model model){
        try{
            model.addAttribute("brokerageAccountNumber", brokerageAccountNumber);
            return "forms/create-watchlist";
        } catch (Exception e){
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @PostMapping("/watchlists/create/{brokerageAccountNumber}")
    public RedirectView createWatchListPost(@PathVariable String brokerageAccountNumber, HttpServletRequest request,
                                            Model model){
        try {
            String watchlistName = request.getParameter("watchlist-name");
            System.out.println("Creating watchlist with name " + watchlistName + " for account " + brokerageAccountNumber);
            Account account = this.dashboardService.getBrokerageAccount(brokerageAccountNumber);
            System.out.println("Adding watch list to account " + account);
            account.addWatchlist(watchlistName);
            return new RedirectView("/brokerageAccount/" + brokerageAccountNumber);
        } catch (Exception e){
            this.dashboardService.setActiveError(e);
            return new RedirectView("/uh-oh");
        }
    }
}
