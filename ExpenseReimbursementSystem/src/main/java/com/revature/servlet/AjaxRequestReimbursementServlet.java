package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajaxRequestReimbursement")
public class AjaxRequestReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 3730831085297290586L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxRequestReimbursement -GET");
		request.getRequestDispatcher("features/reimbursement/requestReimbursement.html").forward(request, response);
	}
	
}
