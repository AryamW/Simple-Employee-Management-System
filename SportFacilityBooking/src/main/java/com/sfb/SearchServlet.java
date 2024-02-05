package com.sfb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String query = request.getParameter("query");

	        List<String> searchResults = performSearch(query);

	        request.setAttribute("searchResults", searchResults);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
	        dispatcher.forward(request, response);
	    }

	    	private List<String> performSearch(String query) {
	    	List<String> searchResults = new ArrayList<>();
	        String sql = "SELECT DISTINCT sport_type FROM facility WHERE sport_type LIKE ?";

	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, "%" + query + "%");
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                String sport = rs.getString("sport_type");
	                searchResults.add(sport);
	            }
	            //System.out.println(searchResults);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return searchResults;
	    }

}
