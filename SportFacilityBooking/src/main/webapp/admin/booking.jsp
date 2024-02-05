<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sfb.Booking" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bookings</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            padding-top: 60px;
        }
        main {
            padding: 20px;
        }
        .filters {
            list-style: none;
            padding: 0;
        }
        .filters li {
            display: inline-block;
            margin-right: 10px;
        }
        form {
            margin-top: 20px;
        }
        form label {
            margin-right: 10px;
        }
        table {
            margin-top: 20px;
        }
        table th, table td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Bookings</h2>

    <ul class="filters">
        <li><a href="${pageContext.request.contextPath}/admin/bookings/all">All Bookings</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/bookings/past">Past Bookings</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/bookings/upcoming">Upcoming Bookings</a></li>
    </ul>

    <form action="${pageContext.request.contextPath}/admin/bookings/search" method="GET" class="form-inline">
        <label for="search">Search:</label>
        <input type="text" name="search" id="search" class="form-control">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

</main>
</body>
</html>
