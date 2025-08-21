package com.employee.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
/* ====(correct login)====
 * Browser -> /login.do (POST) -> LoginServlet
      	   -> Sets session -> redirect /list.do
            Filter checks session -> passes
            FrontController -> listEmployees() -> forwards to employee-list2.jsp
            Browser renders employee list
   ====(not logged in)====
   Browser -> /list.do
Filter checks session -> not found
Filter -> redirects /showLogin.do
FrontController -> /showLogin.do -> forwards to login.jsp
Browser shows login page

*/
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read credentials from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // For demo, hardcode valid login (you can replace with DB lookup)
        if ("admin".equals(username) && "admin123".equals(password)) {
            //  Login success → set session attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // Redirect to employee list
            response.sendRedirect(request.getContextPath() + "/list.do");
        } else {
            //  Invalid login → forward back to login.jsp with error
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                   .forward(request, response);
        }
    }
}
