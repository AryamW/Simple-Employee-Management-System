<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sfb.Facility" %>
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
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Booking</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>

<jsp:include page="/facilitydetail" />
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <h1 class="mb-4">Facility Booking</h1>
    <form action="book" method="GET">
        <div class="mb-3">
            <label for="dropdown" class="form-label">Select a Facility:</label>
            <select name="dropdown" id="dropdown" onchange="displayValue()" class="form-select" required>
                <option value="" selected disabled>Select an option</option>
                <% @SuppressWarnings("unchecked")
                    List<Facility> facilities = (List<Facility>) session.getAttribute("facilitydetail");
                    for (Facility facility : facilities) {%>
                        <option value="<%= facility.getFacility_name() %>"><%= facility.getFacility_name() %></option>
                <% } %>
            </select>
        </div>
        
        <div id="display" class="mb-3"></div>
        
        <input type="hidden" name="facility_id" id="fid">
        <input type="hidden" name="cap" id="cap">
        <input type="hidden" name="sport" id="sport" value="<%= request.getParameter("sport_type") %>">
        
        <div class="mb-3">
            <label for="bookingDate" class="form-label">Booking Date:</label>
            <input type="date" id="bookingDate" name="bookingDate" class="form-control" required>
            <script>
                bookingDate.min = new Date().toISOString().split("T")[0];
                var d = new Date();
                d.setDate(d.getDate() + 30);
                bookingDate.max = d.toISOString().split("T")[0];
            </script>
        </div>

        <button type="submit" class="btn btn-primary">Check Availability</button>
    </form>
</div>

<script>
    <% @SuppressWarnings("unchecked")
        List<Facility> facilitiess = (List<Facility>) session.getAttribute("facilitydetail");
    %>
    var facilityList = [];
    <% if (facilitiess != null) {
        for (Facility facility : facilitiess) { %>
            var facilityObject = {
                id: <%= facility.getFacility_id() %>,
                name: "<%= facility.getFacility_name() %>",
                sport_type: "<%= facility.getSport_type() %>",
                capacity: <%= facility.getCapacity() %>,
                description: "<%= facility.getDescription() %>",
                status: <%= facility.getStatus() %>
            }
            facilityList.push(facilityObject);
    <% }
    } %>

    function displayValue() {
        try {
            var selectedIndex = document.getElementById("dropdown").selectedIndex - 1;
            var selectedFacility = facilityList[selectedIndex];
            var displayElement = document.getElementById("display");

            displayElement.innerHTML = "<p>Description: "+selectedFacility.description+"</p>"+"<p>Capacity: "+selectedFacility.capacity+"</p>";

            var facilityIdInput = document.getElementById("fid");
            facilityIdInput.value = selectedFacility.id;

            var capacityInput = document.getElementById("cap");
            capacityInput.value = selectedFacility.capacity;
        } catch (err) {}
    }
</script>

</body>
</html>
