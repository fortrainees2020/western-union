<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-top: 30px;
        }

        .form-container {
            width: 400px;
            margin: 30px auto;
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.15);
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
            transition: border 0.3s;
        }

        input[type="text"]:focus {
            border-color: #007BFF;
        }

        .btn-submit {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-submit:hover {
            background-color: #218838;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Add New Employee</h2>
    <div class="form-container">
        <form action="${pageContext.request.contextPath}/insert.do" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" required>
            </div>

            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="text" name="salary" id="salary" required>
            </div>

            <div class="form-group">
                <label for="department">Department:</label>
                <input type="text" name="department" id="department" required>
            </div>

            <input type="submit" value="Save" class="btn-submit">
        </form>
        <a href="${pageContext.request.contextPath}/list.do" class="back-link">⬅ Back to List</a>
    </div>
</body>
</html>
