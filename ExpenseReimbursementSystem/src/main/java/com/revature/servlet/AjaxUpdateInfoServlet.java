package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.ErsService;

@WebServlet("/ajaxUpdateInfo")
public class AjaxUpdateInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -7399467019389271762L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxUpdateInfoServlet -GET");
		req.getRequestDispatcher("features/dashboard/updateInfo.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxUpdateInfoServlet -POST");
		
		Map<String, String[]> myMap = req.getParameterMap();

		Set<String> rObject = myMap.keySet();
	
		Object obj = rObject.toArray()[0];
		
		ObjectMapper jackson = new ObjectMapper();
			
		User u = jackson.readValue(((String)obj), User.class);
		
		System.out.println(u);
		
		HttpSession session = req.getSession();
		//get session user's id and set to User u for update
		u.setId(((User) session.getAttribute("user")).getId()); 
		u.setRole(((User) session.getAttribute("user")).getRole());
		
		//For empty input fields, reset to original value
		if(u.getPassword().isEmpty())
			u.setPassword(((User) session.getAttribute("user")).getPassword());
		if(u.getFirstname().isEmpty())
			u.setFirstname(((User) session.getAttribute("user")).getFirstname());
		if(u.getLastname().isEmpty())
			u.setLastname(((User) session.getAttribute("user")).getLastname());
		
		int outcome = new ErsService().updateUser(u);
		
		//For empty username and email, reset to original value AFTER update
		if(u.getUsername().isEmpty())
			u.setUsername(((User) session.getAttribute("user")).getUsername());
		if(u.getEmail().isEmpty())
			u.setEmail(((User) session.getAttribute("user")).getEmail());
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		
		if(outcome == 1) {
			out.write("success");
			session.setAttribute("user", u);
		}
		else
			out.write("failure");
		out.flush();
		
	}
}
