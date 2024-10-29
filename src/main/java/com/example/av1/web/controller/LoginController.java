package com.example.av1.web.controller;

import com.example.av1.Service.AuthenticationService;
import com.example.av1.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //@RequestMapping(method = RequestMethod.GET, value = "")
    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest req, Model model){
        User user = null;
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            user = this.authenticationService.login(username, password);
            req.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (RuntimeException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error_message", e.getMessage());
            return "login";
        }
    }
}
