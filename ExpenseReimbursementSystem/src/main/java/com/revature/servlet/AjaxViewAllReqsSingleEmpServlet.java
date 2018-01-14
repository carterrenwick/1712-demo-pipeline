package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxViewAllReqsSingleEmp")
public class AjaxViewAllReqsSingleEmpServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8491205750045429667L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AjaxViewAllReqsSingleEmpServlet -GET");
		req.getRequestDispatcher("features/reimbursement/managerViewReimbursements.html").forward(req, resp);
	}

}
