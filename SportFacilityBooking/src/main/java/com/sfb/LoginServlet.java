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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if(conn!=null) {
				String query = "select * from users where email = ? And password = ?"; 
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				ps.setString(2, password);
	
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					String userName = rs.getString(2);
					String lastName = rs.getString(3);
					int user_id = rs.getInt(1);
					HttpSession session = req.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("lastName", lastName);
					session.setAttribute("user_id", user_id);
					conn.close();
					resp.sendRedirect("dashboard.jsp");
				}else {
					conn.close();
					resp.sendRedirect("login.jsp");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
