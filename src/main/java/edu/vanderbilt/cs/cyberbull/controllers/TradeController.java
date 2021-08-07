package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.core.Stock;
import edu.vanderbilt.cs.cyberbull.core.account.Account;
import edu.vanderbilt.cs.cyberbull.core.exceptions.InsufficientPositionsException;
import edu.vanderbilt.cs.cyberbull.core.position.Position;
import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class TradeController {
    private DashboardService dashboardService;
    @Autowired
    public TradeController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/trade/{brokerageAccountNumber}")
    public String tradeGet(@PathVariable String brokerageAccountNumber, Model model){
        try{
            model.addAttribute("sp500stocks", dashboardService.getSP500());
            model.addAttribute("account", dashboardService.getBrokerageAccount(brokerageAccountNumber));
            return "trade";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/trade/{brokerageAccountNumber}")
    public RedirectView tradePost(@PathVariable String brokerageAccountNumber, HttpServletRequest request, Model model){
        try{
            double shares = Double.parseDouble(request.getParameter("shares"));
            Account account = dashboardService.getBrokerageAccount(brokerageAccountNumber);
            String symbol = request.getParameter("symbol");
            String action = request.getParameter("action");
            // first determine if we already have a position for this symbol :)
            Optional<Position> positionOptional = account.getPortfolio().getPositions().stream().filter(
                    position-> position.getStock().getSymbol().equals(symbol)
            ).findFirst();
            Optional<Stock> optionalStock = dashboardService.getStock(symbol);
            if (optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                if (action.equals("buy")){
                    if (positionOptional.isPresent()){
                        // add to existing position
                        System.out.println("Updating existing position");
                        account.getPortfolio().buyMore(positionOptional.get(), shares);
                    } else {
                        // new position
                        System.out.println("New position");
                        account.getPortfolio().openPosition(stock, shares);
                    }
                } else if (action.equals("sell")){
                    if (positionOptional.isPresent()){
                        // remove from existing position
                        account.getPortfolio().sellSome(positionOptional.get(), shares);
                    } else {
                        // no position, cannot sell
                        dashboardService.setActiveError(new InsufficientPositionsException(
                                "cannot sell " + symbol + ", no open positions" + " for symbol"));
                        return new RedirectView("/uh-oh");
                    }
                }
            }
            return new RedirectView("/brokerageAccount/" + brokerageAccountNumber);
        } catch (Exception e) {
            dashboardService.setActiveError(e);
            return new RedirectView("/uh-oh");
        }
    }

}
