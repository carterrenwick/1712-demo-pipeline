package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;

@WebServlet("/ajaxNavbar")
public class AjaxNavbarServlet extends HttpServlet{

	
	private static final long serialVersionUID = -2955742662783442983L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxNavBarServlet -GET");
		User u = (User)request.getSession().getAttribute("user");
		if(u.getRole() == 1)	//Employee
			request.getRequestDispatcher("features/navbar/navbar.html").forward(request, response);
		else					//Manager
			request.getRequestDispatcher("features/navbar/managerNavbar.html").forward(request, response);
	}
		

}
