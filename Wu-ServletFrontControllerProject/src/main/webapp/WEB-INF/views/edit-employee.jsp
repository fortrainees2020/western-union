<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 30px;
        }
        h2 {
            color: #333;
        }
        form {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            width: 320px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type=text] {
            width: 95%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type=submit] {
            margin-top: 15px;
            background: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background: #45a049;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #0066cc;
        }
    </style>
</head>
<body>
    <h2>Edit Employee</h2>
    <form action="${pageContext.request.contextPath}/update.do" method="post">
        <!-- Hidden field for ID -->
        <input type="hidden" name="id" value="${employee.id}" />

        <label>Name:</label>
        <input type="text" name="name" value="${employee.name}" />

        <label>Salary:</label>
        <input type="text" name="salary" value="${employee.salary}" />

        <label>Department:</label>
        <input type="text" name="department" value="${employee.department}" />

        <input type="submit" value="Update" />
    </form>

    <a href="${pageContext.request.contextPath}/list.do">Back to List</a>
</body>
</html>
