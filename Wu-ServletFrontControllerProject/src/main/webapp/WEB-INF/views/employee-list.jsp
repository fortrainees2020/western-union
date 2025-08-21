<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 80%; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; padding: 5px 10px; border-radius: 4px; }
        .edit { background: #ffc107; color: #fff; }
        .delete { background: #dc3545; color: #fff; }
        .add-btn { background: #28a745; color: #fff; padding: 6px 12px; }
    </style>
</head>
<body>
    <h2>Employee List</h2>
    <a class="add-btn" href="${pageContext.request.contextPath}/new.do">Add New Employee</a>

    <c:if test="${not empty empList}">
        <table>
            <tr>
                <th>ID</th><th>Name</th><th>Salary</th><th>Department</th><th>Action</th>
            </tr>

            <c:forEach var="e" items="${empList}">
                <tr>
                    <td>${e.id}</td> <!-- If you try to access a variable that is null, EL does not throw an exception. -->
                    <td>${e.name}</td>
                    <td>${e.salary}</td>
                    <td>${e.department}</td>
                    <td>
                        <a class="edit" href="${pageContext.request.contextPath}/edit.do?id=${e.id}">Edit</a>
                        <a class="delete" href="${pageContext.request.contextPath}/delete.do?id=${e.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty empList}">
        <p>No employees found.</p>
    </c:if>
</body>
</html>
