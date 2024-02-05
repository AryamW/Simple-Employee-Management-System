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

<%@ page import="com.sfb.Booking" %>
<%@ page import="java.util.List" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/bootstrap.bundle.js"></script>
</head>
<body class="bg-light">

<jsp:include page="/bookingHistory" />

<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1><%= username %>, Welcome to Your User Dashboard</h1>

    <div class="btn-group mb-4">
        <button id="upc" class="btn btn-outline-primary btn-lg me-3" onclick="showBookings('upcoming')">Upcoming Bookings</button>
        <button id="ps" class="btn btn-secondary btn-lg me-3" onclick="showBookings('past')">Past Bookings</button>
    </div>
    
    <div id="upcomingBookingsTable">
        <h2>Upcoming Bookings</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">No.</th>
                    <th scope="col">Facility Name</th>
                    <th scope="col">Sport</th>
                    <th scope="col">Date</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody id="upcomingTable">
            </tbody>
        </table>
    </div>
    
    <div id="pastBookingsTable" style="display: none;">
        <h2>Past Bookings</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">No.</th>
                    <th scope="col">Facility Name</th>
                    <th scope="col">Sport</th>
                    <th scope="col">Date</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody id="pastTable">
            </tbody>
        </table>
    </div>

    <a href="search.jsp" class="btn btn-primary btn-lg me-3">Book Now</a>
</div>

<script>
    var objlst = [];
    <% @SuppressWarnings("unchecked")
        List<Booking> sss = (List<Booking>) session.getAttribute("bookings");
    %>
    <% if (sss != null) {
        for (Booking obj : sss) {
    %>
        var objct = {
            bid: <%= obj.getBooking_id() %>,
            name: "<%= obj.getFacility_name() %>",
            sport: "<%= obj.getSport_type() %>",
            date: "<%= obj.getDate() %>",
            uid: "<%= obj.getUser_id() %>",
            fid: <%= obj.getFacility_id()%>
        }
        objlst.push(objct);
    <% }
    }
    %>
    
    function bookdate(objlist){
        var upcoming = [];
        var past = [];
        for (var obj of objlist){
            d1 = new Date(obj.date);
            d2 = new Date();
            d2 = d2.setDate(d2.getDate()-1);
            if (d1<d2){
                past.push(obj);
            }else{
                upcoming.push(obj)
            }   
        }
        return {upcoming, past};
    }

    var {upcoming, past} = bookdate(objlst);
    function datesort(a,b){
        var d1 = new Date(a.date);
        var d2 = new Date(b.date);
        return d2-d1;
    }
    
    function displayValue(objlst, val) {
        objlst.sort(datesort);
        const dayOfWeek = ["Sun ","Mon ","Tues ","Wed ","Thu ","Fri ","Sat "];
        try{
            var tdata = '';
            for (let i=0; i<objlst.length; i++){
                var d = new Date(objlst[i]["date"]);
                day = dayOfWeek[d.getDay()];
                d = d.toLocaleDateString();
                tdata += '<tr>';
                tdata += '<td>'+(i+1)+'</td>';
                tdata += '<td>'+objlst[i]["name"]+'</td>';
                tdata += '<td>'+objlst[i]["sport"]+'</td>';
                tdata += '<td>'+day+d+'</td>';
                tdata += '<td>';
                tdata += '<a href="editBooking?bid='+objlst[i]["bid"]+'">'+'Edit</a> | ';
                tdata += '<a href="deleteBooking?bid='+objlst[i]["bid"]+'">'+'Delete</a>';
                tdata +='</td>';
                tdata += '</tr>';
            }
            if (val == 1){
                document.getElementById("upcomingTable").innerHTML = tdata;
            } else {
                document.getElementById("pastTable").innerHTML = tdata;
            }
        } catch(err){}
    }

    window.onload = displayValue(upcoming,1);

    function showBookings(bookingsType) {
        var upcomingBookingsTable = document.getElementById("upcomingBookingsTable");
        var pastBookingsTable = document.getElementById("pastBookingsTable");
        var clr1="btn btn-outline-primary btn-lg me-3";
        var clr2="btn btn-secondary btn-lg me-3";
        if (bookingsType === "upcoming") {
            upcomingBookingsTable.style.display = "block";
            pastBookingsTable.style.display = "none";
            displayValue(upcoming,1);
            document.getElementById("upc").className = clr1;
            document.getElementById("ps").className = clr2
        } else if (bookingsType === "past") {
            upcomingBookingsTable.style.display = "none";
            pastBookingsTable.style.display = "block";
            displayValue(past);
            document.getElementById("ps").className = clr1;
            document.getElementById("upc").className = clr2
        }
    }
</script>

</body>
</html>
