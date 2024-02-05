<%@ page import="com.sfb.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            padding: 20px;
        }

        form {
            max-width: 400px;
            margin: auto;
        }

        label {
            margin-top: 10px;
        }

        select,
        input {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <div class="container">
        <h2>Edit User</h2>
 		<% User user = (User) request.getAttribute("users"); %>
        <form action="${pageContext.request.contextPath}/admin/users/edit" method="post">
            <input type="hidden" name="userId" value="<%= user.getUserId() %>">

            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" class="form-control" name="firstName" id="firstName" value="<%= user.getFname() %>" required>
            </div>

            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" class="form-control" name="lastName" id="lastName" value="<%= user.getLname() %>" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" name="email" id="email" value="<%= user.getEmail() %>" required>
            </div>

            <div class="form-group">
                <label for="tel">Phone Number:</label>
                <input type="tel" class="form-control" name="tel" id="tel" value="<%= Long.toString(user.getTel()) %>" required>
            </div>

            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-control" name="role" id="role" required>
                    <option value="0" <%= user.getRole() == 0 ? "selected" : "" %>>User</option>
                    <option value="1" <%= user.getRole() == 1 ? "selected" : "" %>>Admin</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Update User</button>
        </form>
    </div>
</main>

</body>
</html>
