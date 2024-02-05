<%@ page import="com.sfb.Booking" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>View Booking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            padding: 20px;
        }

        main {
            margin-top: 20px;
        }

        h2 {
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main class="container">
    <h2>View Booking</h2>

    <p><strong>Booking ID:</strong> ${booking.bookingId}</p>
    <p><strong>Facility Name:</strong> ${booking.facilityName}</p>
    <p><strong>Sport Type:</strong> ${booking.sportType}</p>
    <p><strong>Date:</strong> ${booking.date}</p>
    <p><strong>User:</strong> ${booking.user != null ? booking.user.name : "-"}</p>

    <a class="btn btn-primary" href="/admin/bookings">Back to Bookings</a>
</main>

</body>
</html>
