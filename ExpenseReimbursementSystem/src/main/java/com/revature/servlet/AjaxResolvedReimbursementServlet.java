package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.revature.service.ErsServiceContract;

@WebServlet("/ajaxResolvedReimbursement")
public class AjaxResolvedReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = -8837762724100935912L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxResolvedReimbursementServlet -GET");
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		
		ErsServiceContract ers = new ErsService();
		
		List<Reimbursement> accepted = ers.getReimbursementsByIdAndStatus(sessionUser.getId(), 1);
		List<Reimbursement> denied = ers.getReimbursementsByIdAndStatus(sessionUser.getId(), -1);
		
		List<Reimbursement> joined = new ArrayList<>();
		joined.addAll(accepted);
		joined.addAll(denied);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(joined);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.write(json);
		out.flush();
	}
	
}
