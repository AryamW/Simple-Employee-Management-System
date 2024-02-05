package com.sfb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Import your DBConnection class
import com.sfb.DBConnection;

public class AdminDashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
    }

    public int getTodayBookingsCount() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT COUNT(*) FROM bookings WHERE date = CURDATE()";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        conn.close();
        return count;
    }

    public int getPastBookingsCount() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT COUNT(*) FROM bookings WHERE date < CURDATE()";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        conn.close();
        return count;
    }

    public int getUsersCount() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT COUNT(*) FROM users";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        conn.close();
        return count;
    }
}
