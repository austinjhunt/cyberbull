// DISABLING USER MANAGEMENT FOR NOW

//package edu.vanderbilt.cs.cyberbull.controllers;
//
//import edu.vanderbilt.cs.cyberbull.services.auth.token.ConfirmationToken;
//import edu.vanderbilt.cs.cyberbull.services.auth.token.ConfirmationTokenService;
//import edu.vanderbilt.cs.cyberbull.services.auth.user.User;
//import edu.vanderbilt.cs.cyberbull.services.auth.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//@Controller
//public class AuthController {
//
//    private final UserService userService;
//    private final ConfirmationTokenService confirmationTokenService;
//    @Autowired
//    public AuthController(UserService userService, ConfirmationTokenService confirmationTokenService){
//        this.userService = userService;
//        this.confirmationTokenService = confirmationTokenService;
//    }
//
//    @GetMapping("/sign-in")
//    String signIn() {
//
//        return "auth/login";
//    }
//    @PostMapping("/sign-in")
//    String signIn(Model model, HttpServletRequest request){
//        String email = request.getParameter("username");
//        String password = request.getParameter("password");
//        String storedPasswordForUsername = userService.loadUserByUsername(email).getPassword();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        if (bCryptPasswordEncoder.encode(password).equals(storedPasswordForUsername)){
//            // authenticated
//            return "redirect:/";
//        } else{
//            model.addAttribute("authfail", "Username and password did not match any existing credentials");
//            return "login";
//        }
//    }
//
//    @GetMapping("/sign-up")
//    String signUp(Model model) {
//        model.addAttribute("user", new User());
//        return "auth/signup";
//    }
//
//    @PostMapping("/sign-up")
//    String signUp(User user) {
//        userService.signUpUser(user);
//        return "redirect:/sign-in";
//    }
//
//    @GetMapping("/confirm")
//    String confirmMail(@RequestParam("token") String token) {
//
//        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
//
//        optionalConfirmationToken.ifPresent(userService::confirmUser);
//
//        return "/sign-in";
//    }
//
//}