package com.sems;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editScreen")
public class EditEmployeeServlet extends HttpServlet {
	private static final String query = "select name, designation, salary from employees where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");
			int id = Integer.parseInt(req.getParameter("id"));
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			}
			try {
			Connection conn = 
			 DriverManager.getConnection("jdbc:mysql:///new_database", "root", "root");
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
				pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("</head>");
			pw.println("<body>");
			
			pw.println("<form action='editurl?id="+id+"' method='post'>");
				pw.println("<table class='table-hover table-striped'>");
					pw.println("<tr>");
						pw.println("<td>Employee ID</td>");
						pw.println("<td><p>" + id +"</p></td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td>Employee Name</td>");
						pw.println("<td><input type = 'text', name = 'name', value = '" + rs.getString(1)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td>Employee Designation</td>");
						pw.println("<td><input type = 'text', name = 'designation', value = '" + rs.getString(2)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td>Salary</td>");
						pw.println("<td><input type = 'text', name = 'salary', value = '" + rs.getFloat(3)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td><input type = 'submit', value = 'Edit'");
						pw.println("<td><input type = 'reset', value = 'Cancel'");
					pw.println("</tr>");
				pw.println("</table>");
			pw.println("</form>");
			
			pw.println("</body>");
			pw.println("</html>");

			} catch (SQLException se) {
					se.printStackTrace();
					pw.println("<h1>" + se.getMessage() + "</h1>");
			} catch (Exception e) {
					e.printStackTrace();
					pw.println("<h1>" + e.getMessage() + "</h1>");
			}
					pw.println("<a href='Home.html'>Home</a>");
					pw.println("<br>");
					pw.println("<a href='viewEmployee'>View Employees</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
					doGet(req, resp);
					}
}

