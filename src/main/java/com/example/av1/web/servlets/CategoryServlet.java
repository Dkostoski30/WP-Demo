package com.example.av1.web.servlets;

import com.example.av1.Service.CategoryService;
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


@WebServlet(name = "CategoryServlet", urlPatterns = "/servlets/category2")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService;
    private final SpringTemplateEngine springTemplateEngine;

    public CategoryServlet(CategoryService categoryService, SpringTemplateEngine springTemplateEngine) {
        this.categoryService = categoryService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        String clientAgent = req.getHeader("user-agent");
        User user = (User)req.getSession().getAttribute("user");

        webContext.setVariable("categories", categoryService.listCategories());
        webContext.setVariable("clientAgent", clientAgent);
        webContext.setVariable("name", "");
        webContext.setVariable("surname", "");

        springTemplateEngine.process("categories.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("desc") + " (added)";

        categoryService.create(name, description);

        resp.sendRedirect("/servlet/category2");
    }
}
