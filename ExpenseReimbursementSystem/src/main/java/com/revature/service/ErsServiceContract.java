package com.revature.service;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface ErsServiceContract {
	
	public User validateUser(User ersUser);
	
	public List<Reimbursement> getReimbursementsByIdAndStatus(int id, int status);
	public List<Reimbursement> getAllPendingRequests();
	public List<Reimbursement> getAllResolvedRequests();
	public List<User> getAllEmployees();
	
	public void submitReimbursement(Reimbursement r);
	public int registerEmployee(User ersUser);
	
	public int updateUser(User ersUser);
	public void resolveReimbursement(Reimbursement r);
	
}
