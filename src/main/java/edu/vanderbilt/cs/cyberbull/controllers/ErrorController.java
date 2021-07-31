package edu.vanderbilt.cs.cyberbull.controllers;

import edu.vanderbilt.cs.cyberbull.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    private DashboardService dashboardService;
    @Autowired
    public ErrorController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping(path="/uh-oh")
    public String errorGet(Model model){
        // error message (model attribute "error") will have been set before /error is reached
        System.out.println("Active error? -> " + this.dashboardService.getActiveError());
        model.addAttribute("errorMessage", this.dashboardService.getActiveError());
        return "error";
    }

    @PostMapping(path="/uh-oh")
    public String errorPost(HttpServletRequest request, Model model){
        model.addAttribute("errorMessage", request.getParameter("error"));
        return "error";
    }
}
