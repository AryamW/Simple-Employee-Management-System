package com.sfb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
			//System.out.println(request.getParameter("sport_type"));
			String f_id = request.getParameter("facility_id");
			String date = request.getParameter("bookingDate");
			String checkCapacity = "SELECT COUNT(*) as count FROM bookings WHERE DATE(date) = ? and facility_id = ? "; 
			int capacity = Integer.parseInt(request.getParameter("cap"));
	        RequestDispatcher rd = request.getRequestDispatcher("/check.jsp");
	       
				try{
		        	
		        	Connection conn = DBConnection.getConnection();
		            PreparedStatement stmt = conn.prepareStatement(checkCapacity);
		            stmt.setString(1, date);
		            stmt.setString(2, f_id);
		            ResultSet rs = stmt.executeQuery();
		            while(rs.next()) {
			            int count = rs.getInt("count");
			            
			            int available = capacity - count;
			            if (available > 0) {
			            request.setAttribute("checkCapacity", available);
			            rd.forward(request, response);
			            }	
		            }
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");
		
		String f_name = request.getParameter("dropdown");
		String sport = request.getParameter("sport");
		String date = request.getParameter("bookingDate");
		String f_id = request.getParameter("facility_id");
		

		Connection conn = null;
	    try {
			conn = DBConnection.getConnection();
			if(conn!=null) {
				String query = "insert into bookings(facility_name, sport_type, date, user_id, facility_id) values(?,?,?,?,?)"; 
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, f_name);
				ps.setString(2, sport);
				ps.setString(3, date);
				ps.setInt(4, user_id);
				ps.setString(5, f_id);
				ps.executeUpdate();
				
				conn.close();
				
				response.sendRedirect("bookingconfirmation.jsp");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
