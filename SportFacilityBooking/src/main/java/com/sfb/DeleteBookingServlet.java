package com.sfb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String sql = "DELETE FROM bookings WHERE bookings_id=? and user_id=?";
		 String bid = request.getParameter("bid");
		 
		 try{	
				HttpSession session = request.getSession();
				int user_id = (int) session.getAttribute("user_id");
	        	Connection conn = DBConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, bid);
	            stmt.setInt(2, user_id);
	            stmt.executeUpdate();
	            stmt.close();
	            conn.close();
	            response.sendRedirect("bookingconfirmation.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	
		
	}

}
