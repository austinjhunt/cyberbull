package edu.vanderbilt.cs.cyberbull.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccount;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExploreController {
    private DashboardService dashboardService;
    @Autowired
    public ExploreController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping(path="/explore")
    public String exploreGet(Model model){
        try{
            model.addAttribute("sp500stocks", dashboardService.getSP500());
            return "explore";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

}
