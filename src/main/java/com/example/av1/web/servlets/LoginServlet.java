package com.example.av1.web.servlets;

import com.example.av1.Service.AuthenticationService;
import com.example.av1.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="login-servlet", urlPatterns = "/servlets/login")
public class LoginServlet extends HttpServlet {
    private final AuthenticationService authenticationService;
    private final SpringTemplateEngine templateEngine;

    public LoginServlet(AuthenticationService authenticationService, SpringTemplateEngine templateEngine) {
        this.authenticationService = authenticationService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        templateEngine.process("login.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        User user = null;
        try{
            user = authenticationService.login(username, password);
            webContext.setVariable("login_failed", false);
        }catch (RuntimeException e){
            webContext.setVariable("login_failed", true);
            webContext.setVariable("error_message", e.getMessage());

            templateEngine.process("login.html", webContext, resp.getWriter());
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/servlets/category2");
    }
}
