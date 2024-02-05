<%@ page import="com.sfb.User" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Add your additional stylesheets and scripts here -->
    <style>
        body {
            padding: 20px;
        }

        main {
            max-width: 800px;
            margin: 0 auto;
        }

        h2 {
            color: #007bff;
            margin-bottom: 20px;
        }

        ul.filters {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
        }

        ul.filters li {
            display: inline-block;
            margin-right: 10px;
        }

        ul.filters li a {
            text-decoration: none;
            padding: 8px 12px;
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
        }

        ul.filters li a:hover {
            background-color: #0056b3;
        }

        form {
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        a {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Users</h2>
    <ul class="filters">
        <li><a href="${pageContext.request.contextPath}/admin/users/viewAll">All Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/users/admin">Admin Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/users/normal">Normal Users</a></li>
    </ul>
    <form action="${pageContext.request.contextPath}/admin/users/search" method="post">
        <label for="search">Search:</label>
        <input type="text" name="search" id="search" placeholder="Enter user ID, name, email, phone number, or role">
        <button type="submit">Search</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.fname} - ${user.lname}</td>
                    <td>${user.email}</td>
                    <td>0${user.tel}</td>
                    <td>${user.role}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.userId}">Edit</a>
                        <a href="${pageContext.request.contextPath}/admin/users/delete?id=${user.userId}">Delete</a>
                        <a href="${pageContext.request.contextPath}/admin/users/promote?id=${user.userId}">Promote</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
