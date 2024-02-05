<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Users</title>
    <!-- Add your stylesheets and scripts here -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            padding: 20px; /* Add some padding to the body */
        }

        h1 {
            margin-bottom: 20px; /* Add margin to the heading */
        }

        .card {
            margin-bottom: 20px; /* Add margin to the cards */
            padding: 15px; /* Add padding to the cards */
            border: 1px solid #ddd; /* Add a border to the cards */
            border-radius: 5px; /* Add border-radius to round the corners */
            background-color: #f8f9fa; /* Add background color to the cards */
        }

        table {
            width: 100%;
            margin-top: 20px; /* Add margin to the table */
            border-collapse: collapse; /* Collapse table borders */
        }

        th, td {
            padding: 10px; /* Add padding to table cells */
            border: 1px solid #ddd; /* Add border to table cells */
            text-align: left; /* Align text to the left in cells */
        }

        th {
            background-color: #007bff; /* Add background color to header cells */
            color: #fff; /* Set text color for header cells */
        }

        tr:nth-child(even) {
            background-color: #f2f2f2; /* Add background color to even rows */
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>
<div class="container">
    <h1>Admin Users</h1>

    <div class="card">
        <h2>All Users: ${allUsers}</h2>
    </div>

    <div class="card">
        <h2>Admin Users: ${adminUsers}</h2>
    </div>

    <!-- Additional elements for displaying users -->
    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Role</th>
                <!-- Add more headers if needed -->
            </tr>
        </thead>
        <tbody>
            <!-- Loop through a list of users (assuming you have a list of users in the request) -->
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.roleName}</td>
                    <!-- Add more columns if needed -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
