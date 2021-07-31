package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.account.BankAccount;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BankAccountController {
    private DashboardService dashboardService;
    @Autowired
    public BankAccountController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping(path="/bankAccounts/create")
    public String addBankAccountGet(Model model){
        try{
            model.addAttribute("bankAccount", new BankAccount());
            return "accounts/create-bank-account";
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping(path="/bankAccounts/createRandom")
    public RedirectView addRandomBankAccountGet(Model model){
        try{
            this.dashboardService.addRandomBankAccount();
            return new RedirectView("/");
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return new RedirectView("/uh-oh");
        }
    }

    @PostMapping(path="/bankAccounts/create")
    public RedirectView addBankAccountPost(BankAccount bankAccount, Model model ){
        try{
            this.dashboardService.addBankAccount(
                    bankAccount.getTitle(),
                    bankAccount.getDescription(),
                    bankAccount.getRoutingNumber(),
                    bankAccount.getAccountNumber()
            );
            // use a factory to generate a new brokerage account with the provided
            return new RedirectView("/");
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return new RedirectView("/uh-oh");
        }
    }

    @PostMapping("/bankAccounts/updateBalance")
    public RedirectView updateBalancePost(HttpServletRequest request, Model model){
        try{
            String accountNumber = request.getParameter("accountNumber");
            String updatedBalanceString = request.getParameter("balance");
            Account account = dashboardService.getBankAccount(accountNumber);
            account.setBalance(Double.parseDouble(updatedBalanceString));
            return new RedirectView("/");
        } catch (Exception e) {
            this.dashboardService.setActiveError(e);
            model.addAttribute("errorMessage", e.getMessage());
            return new RedirectView("/uh-oh");
        }
    }
}
