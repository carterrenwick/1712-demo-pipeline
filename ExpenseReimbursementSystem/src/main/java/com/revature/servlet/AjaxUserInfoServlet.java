package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;

@WebServlet("/ajaxUserInfo")
public class AjaxUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 856206655060292674L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxUserInfoServlet -GET");
		
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		
		if(sessionUser != null){
				ObjectMapper mapper = new ObjectMapper();//Jackson API-converts Java Objects to JSON Objects and vice-versa
				
				String json = mapper.writeValueAsString(sessionUser);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				out.write(json);
				out.flush();
			}else{
				response.setStatus(418);
			}
		
	}

	
	
}
