<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sfb.Facility" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Facilities</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            padding: 20px;
        }

        main {
            max-width: 800px;
            margin: auto;
        }

        h2 {
            margin-bottom: 20px;
        }

        .filters {
            list-style: none;
            padding: 0;
            margin-bottom: 20px;
        }

        .filters li {
            display: inline-block;
            margin-right: 10px;
        }

        .table {
            width: 100%;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f8f9fa;
        }

        .actions a {
            margin-right: 10px;
            text-decoration: none;
            color: #007bff;
        }

        .actions a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<%@ include file="../adminHeader.jsp" %>

<main>
    <h2>Facilities</h2>
    <ul class="filters">
        <li><a href="${pageContext.request.contextPath}/admin/facility/all">All Facilities</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/facility/active">Active Facilities</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/facility/inactive">Inactive Facilities</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/AddFacility.jsp">Add New Facility</a></li>
    </ul>
    <table class="table">
        <thead>
            <tr>
                <th>Facility ID</th>
                <th>Facility Name</th>
                <th>Sport Type</th>
                <th>Capacity</th>
                <th>Description</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% List<Facility> facilities = (List<Facility>) request.getAttribute("facilities");
                if (facilities != null) {
                    for (Facility facility : facilities) {
            %>
                        <tr>
                            <td><%= facility.getFacility_id() %></td>
                            <td><%= facility.getFacility_name() %></td>
                            <td><%= facility.getSport_type() %></td>
                            <td><%= facility.getCapacity() %></td>
                            <td><%= facility.getDescription() %></td>
                            <td><%= facility.getStatus() ? "Active" : "Inactive" %></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admin/facility/edit?id=<%= facility.getFacility_id() %>">Edit</a>
                                <a href="${pageContext.request.contextPath}/admin/facility/deactivate?id=<%= facility.getFacility_id() %>">Deactivate</a>
                            </td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</main>

</body>
</html>
