<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   String username = (String) session.getAttribute("userName");
   
   if (username != null) {
       // User ID is stored in the session attribute
       // Perform the necessary actions for an authenticated user
       // ...
   } else {
       response.sendRedirect("login.jsp");
   }
%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmed</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body class="bg-light">
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">Your Booking has been Confirmed</h1>
    <p class="lead">
        Your booking is complete. You can now go to your
        <a href="dashboard.jsp" class="btn btn-primary">Dashboard</a>
        to view your bookings.
    </p>
</div>

</body>
</html>
