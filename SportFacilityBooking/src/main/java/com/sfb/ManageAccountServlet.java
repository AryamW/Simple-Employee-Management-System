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
import java.util.Enumeration;
import java.util.List;

public class ManageAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 int user_id = 0;
		try {
			user_id += (int) session.getAttribute("user_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("login.jsp");
		}
		 String sql = "SELECT first_name, last_name, email, tel FROM users WHERE user_id=?";
		if(user_id!=0) {
		 try{	
				
	        	Connection conn = DBConnection.getConnection();
	            RequestDispatcher rd = request.getRequestDispatcher("/manageAccount.jsp");
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, user_id);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            User user = new User();
	            user.setFname(rs.getString("first_name"));
	            user.setLname(rs.getString("last_name"));
	            user.setEmail(rs.getString("email"));
	            user.setTel(rs.getLong("tel"));
	            request.setAttribute("user", user);
	            }
	            rd.forward(request, response);
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> att = request.getParameterNames();
		String sql = "UPDATE users SET ";
		List<String> parameters = new ArrayList<>();
		int comma = 0;
		while (att.hasMoreElements()) {
		String attribute ="";
		if(comma!=0) {attribute +=", ";}
		String element = att.nextElement();
		attribute += element;
		parameters.add(request.getParameter(element));
		attribute += "=?";
		sql+=attribute;
		comma ++;
		}
		sql += " WHERE user_id=?";
		System.out.println(sql);
		if (!parameters.isEmpty()){try{	
			
        	Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            int parameterIndex = 1;
			for (String values: parameters) {
				
				stmt.setString(parameterIndex, values);
				parameterIndex++;
			}
			HttpSession session = request.getSession();
	        int user_id = (int) session.getAttribute("user_id");
			stmt.setInt(parameterIndex, user_id);
			System.out.println(stmt);
			stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }	
		}
		
		
		doGet(request,response);
	}

}
