package com.revature.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

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

@WebServlet("/ajaxProcessReimbursement")
public class AjaxProcessReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 550071286347037948L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//What is the objective?????
		//receive the JSON object with the tx data and persist to the db
	System.err.println("AjaxProcessReimbursementServlet -POST");
	
	//Grab all paramenters, in this case only 1 JSON String
	Map<String, String[]> myMap = request.getParameterMap();

	//Get the the keySet from the map, returns a Set
	Set<String> rObject = myMap.keySet();
	
	//Convert the the keySet into an array, then get the first element (index 0) from that set
	Object obj = rObject.toArray()[0];
	
	//API for converting our JSON into a Java Object
	ObjectMapper jackson = new ObjectMapper();
		
	//Convert the JSON String into the Class specified in the second argument
	Reimbursement r = jackson.readValue(((String)obj), Reimbursement.class);
	System.out.println(r);
	
	HttpSession session = request.getSession();
	User u = (User) session.getAttribute("user"); //the variable we used when the user logged in, in the login servlet
	System.out.println("the user's id:" + u.getId());
	
	r.setAuthorId(u.getId());
	/*
	 * 
	 * use service class to process the transaction
	 */
	
	new ErsService().submitReimbursement(r);
	}
	
}
