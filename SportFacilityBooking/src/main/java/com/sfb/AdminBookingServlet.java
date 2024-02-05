package com.sfb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
public class AdminBookingServlet extends HttpServlet {

    private static final String ALL_BOOKINGS_QUERY = "SELECT * FROM bookings";
    private static final String PAST_BOOKINGS_QUERY = "SELECT * FROM bookings WHERE date < CURDATE()";
    private static final String UPCOMING_BOOKINGS_QUERY = "SELECT * FROM bookings WHERE date >= CURDATE()";
    private static final String BOOKING_BY_FACILITY_QUERY = "SELECT * FROM bookings WHERE facility_id = ?";
    private static final String BOOKING_BY_SPORT_TYPE_QUERY = "SELECT * FROM bookings WHERE sport_type = ?";
    private static final String SEARCH_BOOKINGS_QUERY = "SELECT * FROM bookings WHERE facility_name LIKE ? OR sport_type LIKE ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        List<Booking> bookings = null;
        System.out.println(action);
        System.out.println("1 "+ request.getQueryString());
        System.out.println("1 "+ request.getRequestURI());
        System.out.println("1 "+ request.getRequestURL());
        System.out.println("1 "+ request.getContextPath());
        try (Connection conn = DBConnection.getConnection()) {
            String query;
            if ("/past".equals(action)) {
                query = PAST_BOOKINGS_QUERY;
                bookings = getBookings(conn, query);
            } else if ("/upcoming".equals(action)) {
                query = UPCOMING_BOOKINGS_QUERY;
                bookings = getBookings(conn, query);
            } else if ("/facility".equals(action)) {
                int facilityId = Integer.parseInt(request.getParameter("facilityId"));
                query = BOOKING_BY_FACILITY_QUERY;
                bookings = getBookingsByFacility(conn, facilityId);
            } else if ("/sportType".equals(action)) {
                String sportType = request.getParameter("sportType");
                query = BOOKING_BY_SPORT_TYPE_QUERY;
                bookings = getBookingsBySportType(conn, sportType);
            } else if ("/search".equals(action)) {
                String searchTerm = request.getParameter("search");
                query = SEARCH_BOOKINGS_QUERY;
                bookings = searchBookings(conn, searchTerm);
            } else if ("/delete".equals(action)) {
                deleteBooking(request, conn);
                request.getRequestDispatcher("/admin/AdminBookings.jsp").forward(request, response);
            } else {
                query = ALL_BOOKINGS_QUERY;
                bookings = getBookings(conn, query);
            }

            request.setAttribute("bookings", bookings);
	        RequestDispatcher rd = request.getRequestDispatcher("/admin/AdminBookings.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error executing SQL query", e);
        }
    }
    private List<Booking> getBookings(Connection conn, String query) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Create Booking object using constructor with parameters
                Booking booking = new Booking(
                    rs.getInt("bookings_id"),
                    rs.getString("facility_name"),
                    rs.getString("sport_type"),
                    rs.getDate("date"),
                    rs.getInt("user_id"),
                    rs.getInt("facility_id")
                );

                // Set the user_id directly if it's not 0
                if (rs.getInt("user_id") != 0) {
                    User user = getUserById(conn, rs.getInt("user_id"));
                    booking.setUser_id(rs.getInt("user_id"));
                    booking.setUser(user);
                }

                bookings.add(booking);
            }
        }
        return bookings;
    }

    private List<Booking> getBookingsByFacility(Connection conn, int facilityId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(BOOKING_BY_FACILITY_QUERY)) {
            stmt.setInt(1, facilityId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create Booking object using existing constructor
                    Booking booking = new Booking(
                            rs.getInt("bookings_id"),
                            rs.getString("facility_name"),
                            rs.getString("sport_type"),
                            rs.getDate("date"),
                            rs.getInt("user_id"),
                            rs.getInt("facility_id")
                    );

                    // Set the user if user_id is not 0
                    if (rs.getInt("user_id") != 0) {
                        User user = getUserById(conn, rs.getInt("user_id"));
                        booking.setUser(user);
                    }

                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }


    private List<Booking> getBookingsBySportType(Connection conn, String sportType) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(BOOKING_BY_SPORT_TYPE_QUERY)) {
            stmt.setString(1, sportType);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create Booking object using constructor with parameters
                    Booking booking = new Booking(
                            rs.getInt("bookings_id"),
                            rs.getString("facility_name"),
                            rs.getString("sport_type"),
                            rs.getDate("date"),
                            rs.getInt("user_id"),
                            rs.getInt("facility_id")
                    );
                    // Set the user_id directly if it's not 0
                    if (rs.getInt("user_id") != 0) {
                        User user = getUserById(conn, rs.getInt("user_id"));
                        booking.setUser_id(rs.getInt("user_id"));
                        booking.setUser(user);
                    }
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }

    private List<Booking> searchBookings(Connection conn, String searchTerm) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SEARCH_BOOKINGS_QUERY)) {
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
        	System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // Create Booking object using constructor with parameters
                    Booking booking = new Booking(
                            rs.getInt("bookings_id"),
                            rs.getString("facility_name"),
                            rs.getString("sport_type"),
                            rs.getDate("date"),
                            rs.getInt("user_id"),
                            rs.getInt("facility_id")
                    );
                    // Set the user_id directly if it's not 0
                    if (rs.getInt("user_id") != 0) {
                        User user = getUserById(conn, rs.getInt("user_id"));
                        booking.setUser_id(rs.getInt("user_id"));
                        booking.setUser(user);
                    }
                    bookings.add(booking);
                }
            
        }
        return bookings;
    }

    private User getUserById(Connection conn, int userId) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create User object using result set data
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFname(rs.getString("first_name"));
                    user.setLname(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setTel(rs.getLong("tel"));
                    user.setRole(rs.getInt("role"));
                }
            }
        }
        return user;
    }
    private void deleteBooking(HttpServletRequest request, Connection conn) throws SQLException {
        int bId = Integer.parseInt(request.getParameter("id"));
        String query = "DELETE FROM bookings WHERE bookings_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bId);
            stmt.executeUpdate();
        }
    }
}
