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

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = '1'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
				int user_id = resultSet.getInt(1);
            	String userName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
                HttpSession session = request.getSession();
                session.setAttribute("isAdminLoggedIn", true);
                session.setAttribute("userName", userName);
				session.setAttribute("lastName", lastName);
				session.setAttribute("user_id", user_id);
                response.sendRedirect("AdminDashBoard.jsp");
            } else {
                response.sendRedirect("adminlogin.jsp?error=invalidCredentials");
            }     
        } catch (SQLException e) {
            e.printStackTrace();
            // soemthing to handle database errors
        }
    }
}

