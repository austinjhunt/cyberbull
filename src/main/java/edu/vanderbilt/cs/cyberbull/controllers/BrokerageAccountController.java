package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BrokerageAccountController {
    private DashboardService dashboardService;
    @Autowired
    public BrokerageAccountController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/brokerageAccount/{accountNumber}")
    public String viewBrokerageAccount(@PathVariable String accountNumber, Model model){
        try{
            Account account = this.dashboardService.getBrokerageAccount(accountNumber);
            model.addAttribute("portfolio", account.getPortfolio());
            model.addAttribute("watchlists", account.getWatchlists());
            model.addAttribute("account",  account);
            model.addAttribute("sp500stocks", dashboardService.getSP500());
            return "accounts/brokerage-account";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/brokerageAccounts/create")
    public String addBrokerageAccountGet(Model model ){
        try{
            return "accounts/create-brokerage-account";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }

    }

    @GetMapping(path="/brokerageAccounts/createRandom")
    public RedirectView addRandomBrokerageAccountGet(Model model){
        try{
            this.dashboardService.addRandomBrokerageAccount();
            return new RedirectView("/");
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return new RedirectView("/uh-oh");
        }
    }

    @PostMapping("/brokerageAccounts/create")
    public RedirectView addBrokerageAccountPost(HttpServletRequest request, Model model){
        try{
            this.dashboardService.addBrokerageAccount(
                    request.getParameter("title"),
                    request.getParameter("description"));
            return new RedirectView("/");
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return new RedirectView("/uh-oh");
        }
    }
}
