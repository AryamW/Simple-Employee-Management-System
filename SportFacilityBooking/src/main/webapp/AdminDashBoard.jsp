<%@ page import="com.sfb.AdminDashBoardServlet" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Include Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Include your custom CSS and JS files if needed -->
    <link rel="stylesheet" href="styles.css">
    <script src="script.js"></script>
</head>
<body>
    <%@ include file="adminHeader.jsp" %>

    <main class="container mt-4">
        <h2>Dashboard</h2>
        <div class="card-deck">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Today's Bookings</h3>
                    <%
                        try {
                            AdminDashBoardServlet dashboardServlet = new AdminDashBoardServlet();
                            int todayBookings = dashboardServlet.getTodayBookingsCount();
                    %>
                    <p class="card-text"><%= todayBookings %></p>
                    <%
                        } catch (SQLException e) {
                            // Handle the exception as needed
                            e.printStackTrace();
                        }
                    %>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Past Bookings</h3>
                    <%
                        try {
                            AdminDashBoardServlet dashboardServlet = new AdminDashBoardServlet();
                            int pastBookings = dashboardServlet.getPastBookingsCount();
                    %>
                    <p class="card-text"><%= pastBookings %></p>
                    <%
                        } catch (SQLException e) {
                            // Handle the exception as needed
                            e.printStackTrace();
                        }
                    %>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Users</h3>
                    <%
                        try {
                            AdminDashBoardServlet dashboardServlet = new AdminDashBoardServlet();
                            int usersCount = dashboardServlet.getUsersCount();
                    %>
                    <p class="card-text"><%= usersCount %></p>
                    <%
                        } catch (SQLException e) {
                            // Handle the exception as needed
                            e.printStackTrace();
                        }
                    %>
                </div>
            </div>
        </div>
    </main>

    <!-- Include Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
