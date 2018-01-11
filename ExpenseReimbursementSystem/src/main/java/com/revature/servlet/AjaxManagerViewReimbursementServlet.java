package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxManagerViewReimbursement")
public class AjaxManagerViewReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 296161556342751443L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxManagerViewReimbursementServlet -GET");
		req.getRequestDispatcher("features/reimbursement/managerViewReimbursements.html").forward(req, resp);
	}
}
