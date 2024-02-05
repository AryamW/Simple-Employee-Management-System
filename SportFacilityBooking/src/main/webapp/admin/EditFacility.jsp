<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sfb.Facility" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Facility</title>
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
        form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        select, input {
            margin-bottom: 10px;
        }
        button {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Edit Facility</h2>

    <form action="${pageContext.request.contextPath}/admin/facility/edit" method="post" class="form">
        <!-- Include form fields for editing facility details -->
        <input type="hidden" name="facilityId" value="<%= request.getParameter("id") %>">

        <%-- Import the Facility class and retrieve the facility object --%>
        <% Facility facility = (Facility) request.getAttribute("facility"); %>

        <div class="form-group">
            <label for="facilityName">Facility Name:</label>
            <input type="text" name="facilityName" id="facilityName" value="<%= facility.getFacility_name() %>" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="sportType">Sport Type:</label>
            <input type="text" name="sportType" id="sportType" value="<%= facility.getSport_type() %>" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="capacity">Capacity:</label>
            <input type="number" name="capacity" id="capacity" value="<%= facility.getCapacity() %>" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" id="description" value="<%= facility.getDescription() %>" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <select name="status" id="status" class="form-control" required>
                <option value="1" <%= facility.getStatus() ? "selected" : "" %>>Active</option>
                <option value="0" <%= !facility.getStatus() ? "selected" : "" %>>Inactive</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update Facility</button>
    </form>
</main>
</body>
</html>
