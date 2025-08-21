			package com.employee.controller;
			
			import com.employee.dao.EmployeeDAO;
			import com.employee.model.Employee;
			
			import javax.servlet.*;
			import javax.servlet.annotation.WebServlet;
			import javax.servlet.http.*;
			import java.io.IOException;
			import java.util.List;
			
			@WebServlet("*.do")   // Front Controller for all *.do requests
			public class FrontController extends HttpServlet {
			    private EmployeeDAO empDAO;
			
			    @Override
			    public void init() {
			        empDAO = new EmployeeDAO();
			    }
			
			    @Override
			    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			            throws ServletException, IOException {
			        processRequest(req, resp);
			    }
			
			    @Override
			    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			            throws ServletException, IOException {
			        processRequest(req, resp);
			    }
			
			    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			            throws ServletException, IOException {
			
			        String action = req.getServletPath();   // e.g. /list.do, /insert.do
			        System.out.println("Action: " + action);
			
			        if (action == null) action = "/list.do";
			
			        try {
			            switch (action) {
			                case "/new.do":
			                    RequestDispatcher rdNew = req.getRequestDispatcher("/WEB-INF/views/employee-form.jsp");
			                    rdNew.forward(req, resp);
			                    break;
			
			                case "/insert.do":
			                    insertEmployee(req, resp);
			                    break;
			
			                case "/edit.do":
			                    showEditForm(req, resp);
			                    break;
			
			                case "/update.do":
			                    updateEmployee(req, resp);
			                    break;
			
			                case "/delete.do":
			                    deleteEmployee(req, resp);
			                    break;
			                    
			                case "/showLogin.do":
			                    RequestDispatcher rdLogin = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			                    rdLogin.forward(req, resp);
			                    break;
			
			                case "/list.do":
			 
			                default:
			                    listEmployees(req, resp);
			                    break;
			            }
			        } catch (Exception e) {
			            throw new ServletException(e);
			        }
			    }
			
			    private void listEmployees(HttpServletRequest req, HttpServletResponse resp)
			            throws Exception {
			        List<Employee> list = empDAO.getAllEmployees();
			        req.setAttribute("empList", list);
			        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/employee-list2.jsp");
			        rd.forward(req, resp);
			    }
			
			    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp)
			            throws Exception {
			        String name = req.getParameter("name");
			        double salary = Double.parseDouble(req.getParameter("salary"));
			        String dept = req.getParameter("department");
			
			        Employee emp = new Employee(0, name, salary, dept);
			        empDAO.insertEmployee(emp);
			
			        resp.sendRedirect(req.getContextPath() + "/list.do");
			    }
			
			    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			            throws Exception {
			        int id = Integer.parseInt(req.getParameter("id"));
			        Employee existingEmp = empDAO.getEmployeeById(id);
			        req.setAttribute("employee", existingEmp);
			
			        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/edit-employee.jsp");
			        rd.forward(req, resp);
			    }
			
			    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp)
			            throws Exception {
			        int id = Integer.parseInt(req.getParameter("id"));
			        String name = req.getParameter("name");
			        double salary = Double.parseDouble(req.getParameter("salary"));
			        String dept = req.getParameter("department");
			
			        Employee emp = new Employee(id, name, salary, dept);
			        empDAO.updateEmployee(emp);
			
			        resp.sendRedirect(req.getContextPath() + "/list.do");
			    }
			
			    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp)
			            throws Exception {
			        int id = Integer.parseInt(req.getParameter("id"));
			        empDAO.deleteEmployee(id);
			
			        resp.sendRedirect(req.getContextPath() + "/list.do");
			    }
			}
