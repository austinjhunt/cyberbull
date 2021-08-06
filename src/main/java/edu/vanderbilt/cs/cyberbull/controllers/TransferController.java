package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientFundsException;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TransferController {
    private DashboardService dashboardService;
    @Autowired
    public TransferController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/transfer")
    public String transferGet(Model model){
        try{
            model.addAttribute("bankAccounts", this.dashboardService.getBankAccounts());
            model.addAttribute("brokerageAccounts", this.dashboardService.getBrokerageAccounts());
            return "forms/transfer";
        } catch (Exception e){
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

}
    @PostMapping(path="/transfer")
    public RedirectView transferPost(HttpServletRequest request, Model model){
        String toAccountNumber = request.getParameter("toAccountNumber");
        String fromAccountNumber = request.getParameter("fromAccountNumber");
        Double transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
        try{
            this.dashboardService.transfer(
                    fromAccountNumber, toAccountNumber, transferAmount);
        } catch (InsufficientFundsException | NullPointerException e){
            this.dashboardService.setActiveError(e);
            return new RedirectView("/uh-oh");
        }
        return new RedirectView("/");
    }
}
