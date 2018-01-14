package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.ErsService;
import com.revature.util.EmailUtil;

@WebServlet("/ajaxRegisterEmp")
public class AjaxRegisterEmpServlet extends HttpServlet{
	
	private static final long serialVersionUID = -7595043526294855830L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxRegisterEmpServlet -GET");
		req.getRequestDispatcher("features/register/register.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxRegisterEmpServlet -POST");

		ObjectMapper jackson = new ObjectMapper();
		
		String re = req.getParameter("re");
			
		User u = jackson.readValue(re, User.class);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		
		// If any field is empty, registration fails
		if(u.getUsername().isEmpty() || u.getFirstname().isEmpty() ||u.getLastname().isEmpty() 
				|| u.getPassword().isEmpty() || u.getEmail().isEmpty()) {
			out.write("failure");
			out.flush();
			return;
		}
		
		int outcome = new ErsService().registerEmployee(u);
		
		if(outcome == 1) {
			out.write("success");
			EmailUtil.sendRegistrationEmail(u.getUsername(), u.getPassword(), u.getEmail());
		} else 
			out.write("failure");
		
		out.flush();
			
	}

}
