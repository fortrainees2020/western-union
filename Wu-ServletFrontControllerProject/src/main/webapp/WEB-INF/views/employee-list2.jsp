<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.employee.model.Employee, java.util.List" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h2>Employee List</h2>
    <a href="${pageContext.request.contextPath}/new.do">Add New Employee</a>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th><th>Name</th><th>Salary</th><th>Department</th><th>Action</th>
        </tr>
        <%
            List<Employee> empList = (List<Employee>) request.getAttribute("empList");
            if (empList != null) {
                for (Employee e : empList) {
        %>
        <tr>
            <td><%= e.getId() %></td>
            <td><%= e.getName() %></td>
            <td><%= e.getSalary() %></td>
            <td><%= e.getDepartment() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/delete.do?id=<%= e.getId() %>">Delete</a>
            </td>
        </tr>
        <%      }
            }
        %>
    </table>
</body>
</html>
