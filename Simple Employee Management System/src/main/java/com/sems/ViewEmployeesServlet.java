package com.sems;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/viewEmployee")
public class ViewEmployeesServlet extends HttpServlet {
	
	private static final String query = "select id, name, designation, salary from employees";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql:///new_database", "root", "root");

					PreparedStatement ps = conn.prepareStatement(query);
					
					ResultSet rs = ps.executeQuery();
					pw.println("<!DOCTYPE html>");
					pw.println("<html>");
					pw.println("<head>");
					pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
					pw.println("</head>");
					pw.println("<body>");

					pw.println("<table border='1' class='table-hover table-striped'>");
					pw.println("<tr>");
					pw.println("<th>Employee Id</th>");
					pw.println("<th>Name</th>");
					pw.println("<th>Designation</th>");
					pw.println("<th>Salary</th>");
					
					pw.println("<th>Edit</th>");
					pw.println("<th>Delete</th>");
					
					pw.println("</tr>");
					while(rs.next()) {
						pw.println("<tr>");
						pw.println("<td>"+ rs.getInt(1) +"</td>");
						pw.println("<td>"+ rs.getString(2) +"</td>");
						pw.println("<td>"+ rs.getString(3) +"</td>");
						pw.println("<td>"+ rs.getString(4) +"</td>");
						pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "'>edit</a></td>");
						pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>delete</a></td>");
						pw.println("</tr>");
					}
					pw.println("</table>");
					
					pw.println("</body>");
					pw.println("</html>");

			conn.close();	
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='Home.html'>Home</a>");
    }
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	
}
