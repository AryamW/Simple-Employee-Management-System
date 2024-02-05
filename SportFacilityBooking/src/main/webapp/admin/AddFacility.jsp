<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Facility</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Add your additional stylesheets and scripts here -->
    <style>
        body {
            padding: 20px;
        }

        main {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #007bff;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Add New Facility</h2>

    <form action="${pageContext.request.contextPath}/admin/facility/add" method="post">
        <!-- Include form fields for facility details -->
        <label for="facilityName">Facility Name:</label>
        <input type="text" name="facilityName" id="facilityName" required>

        <label for="sportType">Sport Type:</label>
        <input type="text" name="sportType" id="sportType" required>

        <label for="capacity">Capacity:</label>
        <input type="number" name="capacity" id="capacity" required>

        <label for="description">Description:</label>
        <input type="text" name="description" id="description" required>

        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </select>

        <button type="submit">Add Facility</button>
    </form>
</main>

</body>
</html>
