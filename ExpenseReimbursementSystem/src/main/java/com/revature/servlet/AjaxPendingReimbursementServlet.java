package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ErsService;

@WebServlet("/ajaxPendingReimbursement")
public class AjaxPendingReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 5669682456476488002L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxPendingReimbursementServlet -GET");
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		
		List<Reimbursement> r = new ErsService().getReimbursementsByIdAndStatus(sessionUser.getId(), 0);
		//System.out.println(r);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(r);
		
		//System.out.println(json);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.write(json);
		out.flush();
	}
}
