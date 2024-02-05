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
    <title>Check Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body class="bg-light">
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">Remaining Capacity: <%= request.getAttribute("checkCapacity") %></h1>

    <form action="" method="POST">
        <div class="mb-3">
            <label for="sport" class="form-label">Sport:</label>
            <input type="text" class="form-control" id="sport" name="sport" value="<%= request.getParameter("sport") %>" disabled>
        </div>

        <div class="mb-3">
            <label for="f_name" class="form-label">Facility:</label>
            <input type="text" class="form-control" id="f_name" name="f_name" value="<%= request.getParameter("dropdown") %>" disabled>
        </div>

        <div class="mb-3">
            <label for="bookDate" class="form-label">Booking Date:</label>
            <input type="date" class="form-control" id="bookDate" name="bookDate" value="<%= request.getParameter("bookingDate") %>" disabled>
        </div>

        <input type="hidden" name="facility_id" id="facility_id" value="<%= request.getParameter("facility_id") %>">
        <button type="submit" class="btn btn-primary">Finish Booking</button>
    </form>
</div>

</body>
</html>
