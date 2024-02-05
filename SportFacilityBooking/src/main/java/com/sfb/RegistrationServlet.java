package com.sfb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("first_name");
		String lname = req.getParameter("last_name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String tel = req.getParameter("tel");
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			if(conn!=null) {
				String query = "insert into users(first_name, last_name, email, password, tel) values(?,?,?,?,?)"; 
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setString(5, tel);
				ps.executeUpdate();
				
				conn.close();
				
				resp.sendRedirect("confirmation.jsp");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
