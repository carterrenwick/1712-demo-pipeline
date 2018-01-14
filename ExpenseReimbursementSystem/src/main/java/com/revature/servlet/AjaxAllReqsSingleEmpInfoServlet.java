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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.ErsService;
import com.revature.service.ErsServiceContract;

@WebServlet("/ajaxAllReqsSingleEmpInfo")
public class AjaxAllReqsSingleEmpInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 598760815630890865L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxAllReqsSingleEmpInfoServlet -GET");
		int empId = Integer.parseInt(req.getParameter("empId"));
		
		ErsServiceContract ers = new ErsService();
		
		List<Reimbursement> pending = ers.getReimbursementsByIdAndStatus(empId, 0);
		List<Reimbursement> accepted = ers.getReimbursementsByIdAndStatus(empId, 1);
		List<Reimbursement> denied = ers.getReimbursementsByIdAndStatus(empId, -1);
		
		List<Reimbursement> joined = new ArrayList<>();
		joined.addAll(pending);
		joined.addAll(accepted);
		joined.addAll(denied);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(joined);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
		out.flush();
	}

}
