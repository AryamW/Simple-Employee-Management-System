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
import java.util.List;

public class FacilityDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//System.out.println(request.getParameter("sport_type"));
		String query = request.getParameter("sport_type");
		 //String checkCapacity = "SELECT COUNT(*) as count FROM bookings WHERE DATE(date) = '?' and facility_id =(?)"; 
	        List<Facility> facility = facilityDetail(query);
	        HttpSession session = request.getSession();
	        session.setAttribute("facilitydetail", facility);
	        }catch(Exception e) {}
	    }

	    	private List<Facility> facilityDetail(String query) {
	    	List<Facility> facility = new ArrayList<>();
	        String sql = "SELECT * FROM facility WHERE sport_type= ? AND status='1'";
	        try{
	        	
	        	Connection conn = DBConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, query);
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
