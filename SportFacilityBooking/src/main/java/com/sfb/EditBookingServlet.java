package com.sfb;

import jakarta.servlet.RequestDispatcher;
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

public class EditBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String sql = "UPDATE bookings SET date = ? WHERE bookings_id=?";
		 String bid = request.getParameter("bid");
		 String date = request.getParameter("bookingDate");
		try{	
	        	Connection conn = DBConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            
	            stmt.setString(1, date);
	            stmt.setString(2, bid);
	            stmt.executeUpdate();
	            stmt.close();
	            conn.close();
	            response.sendRedirect("bookingconfirmation.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        HttpSession session = request.getSession();
	        int user_id = (int) session.getAttribute("user_id");
			String bid = request.getParameter("bid");
			List<Booking> bList = getBookingDetailById(bid);
			Booking b = bList.get(0);
			int fid = b.getFacility_id();
			List<Facility> fList= facilityDetailById(fid);
			Facility f = fList.get(0);
			RequestDispatcher rd = request.getRequestDispatcher("/viewdetails.jsp");
			request.setAttribute("editedbooking", b);
			request.setAttribute("editedbookingF", f);
			
			
			String referer = request.getHeader("Referer");
		    
		    if (referer != null && !referer.isEmpty()) {
		        // The request has a referring URL
		        // Do something with the referer value
		    	if(referer.contains("/editBooking?")) {
					int capacity = Integer.parseInt(request.getParameter("cap"));
			        RequestDispatcher rd2 = request.getRequestDispatcher("/viewdetails.jsp");
			        String checkCapacity = "SELECT COUNT(*) as count FROM bookings WHERE DATE(date) = ? and facility_id = ? "; 
						try{
							
				        	String date = request.getParameter("bookingDate");
				        	Connection conn = DBConnection.getConnection();
				            PreparedStatement stmt = conn.prepareStatement(checkCapacity);
				            stmt.setString(1, date);
				            stmt.setInt(2, fid);
				            ResultSet rs = stmt.executeQuery();
				            while(rs.next()) {
					            int count = rs.getInt("count");
					            
					            int available = capacity - count;
					            if (available > 0) {
					            request.setAttribute("checkCapacity", available);
					            session.setAttribute("edit", true);
					            rd2.forward(request, response);
					            }	
				            }
				            conn.close();
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
		    		}else {
		    			rd.forward(request, response);
		    		}
		    }
			
	        }catch(Exception e) {}
	    }
			private List<Booking> getBookingDetailById(String query) {
				List<Booking> bookList = new ArrayList<>();
				String sql = "SELECT * FROM bookings WHERE bookings_id= ?";
				
		        try{
		        	
		        	Connection conn = DBConnection.getConnection();
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            stmt.setString(1, query);
		            ResultSet rs = stmt.executeQuery();
		            
		            while (rs.next()) {
		            	int b_id = rs.getInt("bookings_id");
		            	String f_name = rs.getString("facility_name");
		            	String s_t = rs.getString("sport_type");
		            	Date date = rs.getDate("date");
		            	int uid = rs.getInt("user_id");
		            	int fid= rs.getInt("facility_id");
		            	Booking book = new Booking(b_id,f_name,s_t,date,uid,fid);
		            	bookList.add(book);
		            }
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return bookList;
			}
	    	
	    	private List<Facility> facilityDetailById(int query) {
	    		List<Facility> facility = new ArrayList<>();
	    		String sql = "SELECT * FROM facility WHERE facility_id= ?";
		        try{
		        	
		        	Connection conn = DBConnection.getConnection();
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            stmt.setInt(1, query);
		            ResultSet rs = stmt.executeQuery();
		            
		            while (rs.next()) {
		            	int f_id = rs.getInt("facility_id");
		            	String f_name = rs.getString("facility_name");
		            	String s_t = rs.getString("sport_type");
		            	int cpty = rs.getInt("capacity");
		            	String desc = rs.getString("description");
		            	int stat = rs.getInt("status");
		            	
		            	Facility fclty = new Facility( f_id, f_name, s_t, cpty, desc, stat);	        
		                facility.add(fclty);
		                //System.out.println(fclty.getFacility_name());
		            }
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return facility;
	    	}
	    	
	
}
