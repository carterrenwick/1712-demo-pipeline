package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ErsService;

@WebServlet("/ajaxApproveOrDenyReq")
public class AjaxApproveOrDenyReqServlet extends HttpServlet {

	private static final long serialVersionUID = -5256133832137679575L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxApproveOrDenyReqServlet -GET");
		
		int reqId = Integer.parseInt(req.getParameter("reqId"));
		String choice = req.getParameter("choice");
		int resolverId = ((User)(req.getSession().getAttribute("user"))).getId();
		
		Reimbursement r = new Reimbursement();
		
		r.setId(reqId);
		r.setResolverId(resolverId);
		if(choice.equals("approve"))
			r.setStatus(1);
		else
			r.setStatus(-1);
		
		new ErsService().resolveReimbursement(r);
		
		req.getRequestDispatcher("features/reimbursement/managerViewReimbursements.html").forward(req, resp);
	}
	
}
