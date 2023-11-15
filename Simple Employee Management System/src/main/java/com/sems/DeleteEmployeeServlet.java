package com.sems;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/deleteurl")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final String query = "Delete from employees where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, 
	HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw = resp.getWriter();
	resp.setContentType("text/html");
	int id = Integer.parseInt(req.getParameter("id"));
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException cnf) {
	cnf.printStackTrace();
	}
	try {
	Connection conn = DriverManager.getConnection("jdbc:mysql:///new_database", "root", "root");
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setInt(1, id);
	int count = ps.executeUpdate();
	if(count == 1) {
	pw.println("<h2>Employee record is deleted successfully.</h2>");
	}else {
	pw.println("<h2>Employee record not deleted.</h2>");
	}
	} catch (SQLException se) {
	se.printStackTrace();
	pw.println("<h1>" + se.getMessage() + "</h1>");
	} catch (Exception e) {
	e.printStackTrace();
	pw.println("<h1>" + e.getMessage() + "</h1>");
	}
	pw.println("<a href='Home.html'>Home</a>");
	pw.print("<br>");
	pw.println("<a href='viewEmployee'>view Employees</a>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
	}
}
