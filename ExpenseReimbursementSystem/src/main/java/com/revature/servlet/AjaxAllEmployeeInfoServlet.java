package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.ErsService;

@WebServlet("/ajaxAllEmployeeInfo")
public class AjaxAllEmployeeInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 5878034008465287840L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxAllEmployeeInfoServlet -GET");
		List<User> emps = new ErsService().getAllEmployees();
		
		System.out.println(emps);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(emps);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
		out.flush();
	}
	

}
