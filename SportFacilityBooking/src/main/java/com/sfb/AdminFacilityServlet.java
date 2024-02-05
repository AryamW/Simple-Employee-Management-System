package com.sfb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sfb.DBConnection; 
import com.sfb.Facility; 

public class AdminFacilityServlet extends HttpServlet {

    private static final String ALL_FACILITIES_QUERY = "SELECT * FROM facility";
    private static final String ACTIVE_FACILITIES_QUERY = "SELECT * FROM facility WHERE status = 1";
    private static final String INACTIVE_FACILITIES_QUERY = "SELECT * FROM facility WHERE status = 0";
    private static final String GET_FACILITY_QUERY = "SELECT * FROM facility WHERE facility_id =";
    private static final String ADD_FACILITY_QUERY = "INSERT INTO facility (facility_name, sport_type, capacity, description, status) VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_FACILITY_QUERY = "UPDATE facility SET facility_name = ?, sport_type = ?, capacity = ?, description = ?, status = ? WHERE facility_id = ?";
    private static final String DEACTIVATE_FACILITY_QUERY = "UPDATE facility SET status = 0 WHERE facility_id = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        List<Facility> facilities = null;

        try (Connection conn = DBConnection.getConnection()) {
            String query;
            if ("/active".equals(action)) {
                query = ACTIVE_FACILITIES_QUERY;
            } else if ("/inactive".equals(action)) {
                query = INACTIVE_FACILITIES_QUERY;
            } else if ("/edit".equals(action)) {
                query = GET_FACILITY_QUERY + request.getParameter("id");
                Facility facility = getFacilityById(conn, query);
                request.setAttribute("facility", facility);
                request.getRequestDispatcher("/admin/EditFacility.jsp").forward(request, response);
            }  else {
                query = ALL_FACILITIES_QUERY;
            }
            
            if ("/deactivate".equals(action)) {
                try {
    				deactivateFacility(request);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }
            facilities = getFacilities(conn, query);

            request.setAttribute("facilities", facilities);
            request.getRequestDispatcher("/admin/Facilities.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error executing SQL query", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if ("/add".equals(action)) {
            try {
				addFacility(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("/edit".equals(action)) {
            try {
				editFacility(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 

        // Redirect back to the facilities page after performing the action
        response.sendRedirect(request.getContextPath() + "/admin/Facilities.jsp");
    }

    private List<Facility> getFacilities(Connection conn, String query) throws SQLException {
        List<Facility> facilities = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Facility facility = new Facility();
                facility.setFacility_id(rs.getInt("facility_id"));
                facility.setFacility_name(rs.getString("facility_name"));
                facility.setSport_type(rs.getString("sport_type"));
                facility.setCapacity(rs.getInt("capacity"));
                facility.setDescription(rs.getString("description"));
                facility.setStatus(rs.getInt("status") == 1);
                facilities.add(facility);
            }
        }
        return facilities;
    }

    private void addFacility(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ADD_FACILITY_QUERY)) {
            stmt.setString(1, request.getParameter("facilityName"));
            stmt.setString(2, request.getParameter("sportType"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("capacity")));
            stmt.setString(4, request.getParameter("description"));
            stmt.setInt(5, Integer.parseInt(request.getParameter("status")));
            stmt.executeUpdate();
        }
    }

    private void editFacility(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(EDIT_FACILITY_QUERY)) {
            stmt.setString(1, request.getParameter("facilityName"));
            stmt.setString(2, request.getParameter("sportType"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("capacity")));
            stmt.setString(4, request.getParameter("description"));
            stmt.setInt(5, Integer.parseInt(request.getParameter("status")));
            stmt.setInt(6, Integer.parseInt(request.getParameter("facilityId")));
            stmt.executeUpdate();
        }
    }

    private void deactivateFacility(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DEACTIVATE_FACILITY_QUERY)) {
            stmt.setInt(1, Integer.parseInt(request.getParameter("id")));
            stmt.executeUpdate();
        }
    }
    
    
    private Facility getFacilityById(Connection conn, String query) throws SQLException {
        Facility facility = new Facility();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    facility.setFacility_id(rs.getInt("facility_id"));
                    facility.setFacility_name(rs.getString("facility_name"));
                    facility.setSport_type(rs.getString("sport_type"));
                    facility.setCapacity(rs.getInt("capacity"));
                    facility.setDescription(rs.getString("description"));
                    facility.setStatus(rs.getInt("status") == 1);
                }
            }
        }
        return facility;
    }
}
