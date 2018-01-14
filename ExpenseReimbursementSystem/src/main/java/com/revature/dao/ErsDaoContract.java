package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface ErsDaoContract {
	
	public User selectUserByUsername(String username);
	public List<User> selectAllEmployees();
	public List<Reimbursement> selectAllPendingRequests();
	public List<Reimbursement> selectAllResolvedRequests();
	public List<Reimbursement> selectRequestsByUserId(int userId, int status);
	public List<String> selectAllUsernames();
	public List<String> selectAllEmails();
	
	public void updateUser(User u);
	public void updateUserIgnoreUsernameAndEmail(User u);
	public void updateUserIgnoreUsername(User u);
	public void updateUserIgnoreEmail(User u);
	public void updateReimbursement(Reimbursement r);
	
	
	public void insertUser(User u);
	public void insertReimbursement(Reimbursement r);
	public void insertReimbursementWithReceipt(Reimbursement r);
}
