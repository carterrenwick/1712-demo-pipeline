package com.revature.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ErsService;

@MultipartConfig
@WebServlet("/ajaxRequestReimbursement")
public class AjaxRequestReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 3730831085297290586L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AjaxRequestReimbursement -GET");
		request.getRequestDispatcher("features/reimbursement/requestReimbursement.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.err.println("AjaxRequestReimbursementServlet -POST");
	
	String rr = request.getParameter("rr");
	System.out.println(rr);
	//API for converting our JSON into a Java Object
	ObjectMapper jackson = new ObjectMapper();
		
	//Convert the JSON String into the Class specified in the second argument
	Reimbursement r = jackson.readValue(rr, Reimbursement.class);
	
	HttpSession session = request.getSession();
	User u = (User) session.getAttribute("user"); //the variable we used when the user logged in, in the login servlet
	System.out.println("the user's id:" + u.getId());
	
	r.setAuthorId(u.getId());
	try {
		Part filePart = request.getPart("file");
		
		InputStream fileContent = filePart.getInputStream();
	    
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    
	    int nRead;
	    byte[] data = new byte[16384];	
	    while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
	      buffer.write(data, 0, nRead);
	    }
	    buffer.flush();
	    byte[] bytes = buffer.toByteArray();
		r.setReceipt(bytes);
	} catch(NullPointerException n) {
		System.out.println("Receipt not uploaded!");
	}
	
	new ErsService().submitReimbursement(r);
	
	}
	
}
