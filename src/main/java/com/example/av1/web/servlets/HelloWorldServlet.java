package com.example.av1.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/servlets/home"})
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Get the writer to write the response
        PrintWriter out = resp.getWriter();

        // Get the 'username' parameter from the request
        String username = req.getParameter("username");

        // Write the HTML response
        out.format("<html><head><title>Hello World</title></head><body>" +
                "<h1>Hello, %s!</h1>" +
                "</body></html>", username != null ? username : "Guest");
        out.flush();
    }
}
