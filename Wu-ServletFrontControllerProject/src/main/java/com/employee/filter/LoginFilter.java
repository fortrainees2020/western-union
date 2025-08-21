package com.employee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.do")   // applies to all *.do requests (FrontController)
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter initialized...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    	System.out.println("Filter Executed");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();   // e.g. /list.do, /login.do
        HttpSession session = req.getSession(false);

        // Allow login page and login action without authentication
        if (path.equals("/login.do") || path.equals("/showLogin.do")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if user is logged in
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response); // user is logged in, continue
        } else {
            System.out.println("Unauthorized access to: " + path);
            resp.sendRedirect(req.getContextPath() + "/showLogin.do"); // redirect to login page
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter destroyed...");
    }
}

