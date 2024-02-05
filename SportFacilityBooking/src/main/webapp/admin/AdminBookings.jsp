<%@ page import="java.util.List" %>
<%@ page import="com.sfb.Booking" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Bookings</title>
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

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Admin Bookings</h2>

    <form action="${pageContext.request.contextPath}/admin/bookings/search" method="GET">
        <label for="search">Search:</label>
        <input type="text" name="search" id="search">
        <button type="submit">Search</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Facility Name</th>
                <th>Sport Type</th>
                <th>Date</th>
                <th>User</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td>${booking.booking_id}</td>
                    <td>${booking.facility_name}</td>
                    <td>${booking.sport_type}</td>
                    <td>${booking.date}</td>
                    <td>${booking.user != null ? booking.user.userId : "-"}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/bookings/delete?id=${booking.booking_id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
