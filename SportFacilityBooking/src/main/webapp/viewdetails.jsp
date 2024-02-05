<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sfb.Booking" %>
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<%
	Booking b = (Booking) request.getAttribute("editedbooking");
	Facility f = (Facility) request.getAttribute("editedbookingF");
	%>
<div class="container mt-5">
    <form action="editBooking" method="GET">
        <div class="form-group">
            <label for="sport">Sport:</label>
            <input type="text" class="form-control" id="sport" value="<%= b.getSport_type() %>" disabled>
        </div>
        <div class="form-group">
            <label for="f_name">Facility:</label>
            <input type="text" class="form-control" id="f_name" value="<%= b.getFacility_name() %>" disabled>
        </div>
        <div id="display">
            <p>Description: <%= f.getDescription()%></p>
            <p>Capacity: <%= f.getCapacity()%></p>
            <p>Current booked date is: <%= b.getDate()%></p>
        </div>
        <div class="form-group">
            <label for="bookingDate">Booking Date:</label>
            <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="<%= request.getParameter("bookingDate") %>" required>
        </div>
        <input type="hidden" name="bid" id="bid" value="<%=  b.getBooking_id()%>">
        <input type="hidden" name="facility_id" id="fid" value="<%=  b.getFacility_id()%>">
        <input type="hidden" name="cap" id="cap" value="<%= f.getCapacity()%>">

        <button type="submit" class="btn btn-primary">Check Availability</button>
    </form>

    <% 
    if (request.getAttribute("checkCapacity")!=null){
    %>
        <h1>Remaining capacity: <%= request.getAttribute("checkCapacity")%></h1>
        <form action="editBooking" method="POST">
            <input type="hidden" name="bid" id="bid" value="<%= request.getParameter("bid") %>">
            <input type="hidden" name="bookingDate" id="bookingDate" value="<%= request.getParameter("bookingDate")%>">
            <button type="submit" class="btn btn-success">Finish Booking</button>
        </form>
    <% } %>

    <script>
        <% @SuppressWarnings("unchecked")
        List<Facility> sss = (List<Facility>) session.getAttribute("facilitydetail");
        %>

        var objlst = [];
        <%if (sss != null){
             for (Facility obj : sss) {%>
             var objct = {
                 id: <%= obj.getFacility_id() %>,
                 name : "<%= obj.getFacility_name() %>",
                 sport_type : "<%= obj.getSport_type() %>",
                 capacity : <%= obj.getCapacity() %>,
                 description : "<%= obj.getDescription() %>",
                 status : <%= obj.getStatus()%>
             }
             objlst.push(objct);
        <%    }
        }%>

        function displayValue() {
            try {
                var selectedIndex = document.getElementById("dropdown").selectedIndex - 1;
                var selectedObject = objlst[selectedIndex];
                var displayElement = document.getElementById("display");
                displayElement.innerHTML =  "Description: " + selectedObject["description"]+ "<br/>" + "Capacity: " + selectedObject["capacity"];
                var disp = document.getElementById("fid");
                disp.value = selectedObject["id"];
                var cap = document.getElementById("cap");
                cap.value = selectedObject["capacity"];
            } catch(err) {}
        }
    </script>
</div>

</body>
</html>
