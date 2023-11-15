package com.sems;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query = "update employees set name=?, designation=?, salary=? where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	PrintWriter pw = resp.getWriter();
	resp.setContentType("text/html");
	int id = Integer.parseInt(req.getParameter("id"));
	String name = req.getParameter("name");
	String designation = req.getParameter("designation");
	float salary = Float.parseFloat(req.getParameter("salary"));
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException cnf) {
	cnf.printStackTrace();
	}
	try {
	Connection conn = DriverManager.getConnection(
	 "jdbc:mysql:///new_database", "root", "root");
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setString(1, name);
	ps.setString(2, designation);
	ps.setFloat(3, salary);
	ps.setInt(4, id);
	int count = ps.executeUpdate();
	if(count == 1) {
	pw.println("<h2>Employee record is edited successfully.</h2>");
	}else {
	pw.println("<h2>Employee record not edited.</h2>");
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
	pw.println("<a href='viewEmployee'>View Employees</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
	}

}
