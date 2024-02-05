<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("userName");

    if (username != null) {
        // User ID is stored in the session attribute
        // Perform the necessary actions for an authenticated user
        // ...
    } else {
        response.sendRedirect(request.getContextPath() + "/adminlogin.jsp");
    }
%>
<%
    String uname = (String) session.getAttribute("userName");
    String lname = (String) session.getAttribute("lastName");
    String view = "";
    if (uname != null) {
        view += "" + uname.charAt(0) + lname.charAt(0);
        view = view.toUpperCase();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sports Facility Booking</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding-top: 56px;
        }

        header {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
    </style>
</head>
<body>

<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sports Facility Booking</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/AdminDashBoard.jsp">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/booking.jsp">Bookings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/Facilities.jsp">Facilities</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/AdminUsers.jsp">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/manageAdminAccount">Manage Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sign Out</a>
                    </li>
                    <li class="nav-item">
                        <p class="nav-link rounded-circle border border-success btn-dark"><%= view %></p>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Your page content goes here -->

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
