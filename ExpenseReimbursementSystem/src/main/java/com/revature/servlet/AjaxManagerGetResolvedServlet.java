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
import com.revature.model.Reimbursement;
import com.revature.service.ErsService;

@WebServlet("/ajaxManagerGetResolved")
public class AjaxManagerGetResolvedServlet extends HttpServlet{

	private static final long serialVersionUID = 3885109209807546945L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxManagerGetPendingServlet -GET");
		List<Reimbursement> r = new ErsService().getAllResolvedRequests();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(r);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		out.write(json);
		out.flush();
	}
}
