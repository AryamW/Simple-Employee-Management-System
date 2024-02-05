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

public class AdminUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try (Connection conn = DBConnection.getConnection()) {
            if ("/viewAll".equals(action)) {
                List<User> users = getAllUsers(conn);
                request.setAttribute("users", users);
                request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
            } if ("/admin".equals(action)) {
                List<User> users = getAdminUsers(conn);
                request.setAttribute("users", users);
                request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
            }if ("/normal".equals(action)) {
                List<User> users = getNormalUsers(conn);
                request.setAttribute("users", users);
                request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
            }else if("/edit".equals(action)) {
            	int id =  Integer.parseInt(request.getParameter("id"));
                User users = getUserById(conn, id);
                request.setAttribute("users", users);
                request.getRequestDispatcher("/admin/EditUser.jsp").forward(request, response);
            } else if ("/promote".equals(action)) {
                promoteUser(request, conn);
                request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
            }else if ("/delete".equals(action)) {
                deleteUser(request, conn);
                request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
            }

            else {
                // Handle other actions or defaults
                response.sendRedirect("/AdminDashBoard.jsp"); // Redirect to dashboard if action is not recognized
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error executing SQL query", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try (Connection conn = DBConnection.getConnection()) {
            if ("/search".equals(action)) {
                String searchTerm = request.getParameter("search");
                List<User> users = searchUsers(conn, searchTerm);
                request.setAttribute("users", users);
            } else if ("/edit".equals(action)) {
                editUser(request, conn);
            } 

            // Handle other actions or defaults

            request.getRequestDispatcher("/admin/AdminUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error executing SQL query", e);
        }
    }

    private List<User> getAllUsers(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getLong("tel"),
                        rs.getInt("role")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving users from the database", e);
        }

        return users;
    }

    private List<User> getAdminUsers(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 1";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getLong("tel"),
                        rs.getInt("role")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving users from the database", e);
        }

        return users;
    }

    private List<User> getNormalUsers(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 0";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getLong("tel"),
                        rs.getInt("role")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving users from the database", e);
        }

        return users;
    }
    
    
    private List<User> searchUsers(Connection conn, String searchTerm) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR tel LIKE ? OR role LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 1; i <= 6; i++) {
                stmt.setString(i, "%" + searchTerm + "%");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getLong("tel"),
                            rs.getInt("role")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }

    private void editUser(HttpServletRequest request, Connection conn) throws SQLException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        long tel = Long.parseLong(request.getParameter("tel"));
        int role = Integer.parseInt(request.getParameter("role"));

        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, tel = ?, role = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setLong(4, tel);
            stmt.setInt(5, role);
            stmt.setInt(6, userId);

            stmt.executeUpdate();
        }
    }

    private void deleteUser(HttpServletRequest request, Connection conn) throws SQLException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    private void promoteUser(HttpServletRequest request, Connection conn) throws SQLException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String query = "UPDATE users SET role = 1 WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
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
}
