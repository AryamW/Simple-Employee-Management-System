<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <style>
        body {
            padding: 20px;
        }

        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        p {
            font-style: italic;
            color: #6c757d;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <h1 class="text-center">Search Page</h1>

    <form action="search" method="GET" class="mb-4">
        <div class="input-group">
            <input type="text" name="query" class="form-control" placeholder="Enter your search query">
            <div class="input-group-append">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

    <% @SuppressWarnings("unchecked")
    List<String> searchResults = (List<String>) request.getAttribute("searchResults");
    %>

    <% if (searchResults != null && !searchResults.isEmpty()) { %>
        <h2>Available Sports</h2>
        <ul>
            <% for (String result : searchResults) { %>
                <li><a href='booking.jsp?sport_type=<%= result %>'><%= result %></a></li>
            <% } %>
        </ul>
    <% } else if (request.getParameter("query") == null) { %>
        <p class="mt-3">Search for a sport.</p>
    <% } else { %>
        <p class="mt-3">No search results found.</p>
    <% } %>

</div>

</body>
</html>
