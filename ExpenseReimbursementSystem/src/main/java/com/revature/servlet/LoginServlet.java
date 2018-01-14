package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.service.ErsService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 817105812389880890L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -GET");
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -POST");
		User clientUser = new User();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		clientUser.setUsername(username);
		clientUser.setPassword(password);
		
		clientUser = new ErsService().validateUser(clientUser);
		
		if(clientUser != null){
			
			HttpSession session = req.getSession(); 
			
			session.setAttribute("user", clientUser);	//user is a variable the developer defined

			req.getRequestDispatcher("app.html").forward(req, resp);
		}else{
			System.err.println("invalid credentials -sending user back to login.html");
			resp.sendRedirect("login.html");
		}
		
	}
}
