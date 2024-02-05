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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
	        HttpSession session = request.getSession();
	        int query = (int) session.getAttribute("user_id");
	        List<Booking> booking = bookingDetail(query);
	        session.setAttribute("bookings", booking);
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	}
	
	private List<Booking> bookingDetail(int query) {
    	List<Booking> booking = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id= ?";
        try{
        	
        	Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	int b_id = rs.getInt("bookings_id");
            	String f_name = rs.getString("facility_name");
            	String s_t = rs.getString("sport_type");
            	Date date = rs.getDate("date");
            	int u_id = rs.getInt("user_id");
            	int f_id = rs.getInt("facility_id");
            	
            	Booking book = new Booking(b_id,f_name,s_t,date,u_id,f_id);	        
                booking.add(book);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    
    	}

	
}
