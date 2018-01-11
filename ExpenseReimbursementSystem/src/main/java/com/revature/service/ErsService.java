package com.revature.service;

import java.util.List;

import com.revature.dao.ErsDao;
import com.revature.dao.ErsDaoContract;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ErsService implements ErsServiceContract {
	
	private ErsDaoContract ersDAO = new ErsDao();
	
	@Override
	public User validateUser(User ersUser) {
		User u = ersDAO.selectUserByUsername(ersUser.getUsername());
		
		if(u != null){
			if( u.getUsername().equals(ersUser.getUsername() )  
					&& u.getPassword().equals(ersUser.getPassword())){
				return u;
			}
		}
		return null;
	}

	@Override
	public int updateUser(User ersUser) {
		if(ersUser.getUsername().isEmpty() || ersUser.getEmail().isEmpty()) {
			if(ersUser.getUsername().isEmpty() && ersUser.getEmail().isEmpty()) {
				ersDAO.updateUserIgnoreUsernameAndEmail(ersUser);
				return 1;
			} else if(ersUser.getUsername().isEmpty()) {
				List<String> emails = ersDAO.selectAllEmails();
				for(String e: emails)
					if(e.compareTo(ersUser.getEmail()) == 0)
						return 0;
				ersDAO.updateUserIgnoreUsername(ersUser);
				return 1;
			} else {
				List<String> uNames = ersDAO.selectAllUsernames();
				for(String u: uNames)
					if(u.compareTo(ersUser.getUsername()) == 0)
						return 0;
				ersDAO.updateUserIgnoreEmail(ersUser);
				return 1;
			}
		} 
		
		ersDAO.updateUser(ersUser);
		return 1;
	}
	
	@Override
	public void submitReimbursement(Reimbursement r) {
		ersDAO.insertReimbursement(r);
	}

	@Override
	public List<Reimbursement> getReimbursementsByIdAndStatus(int id, int status) {
		return ersDAO.selectRequestsByUserId(id, status);
	}

	@Override
	public List<User> getAllEmployees() {
		return ersDAO.selectAllEmployees();
	}
	
	@Override
	public List<Reimbursement> getAllPendingRequests() {
		return ersDAO.selectAllPendingRequests();
	}

	@Override
	public List<Reimbursement> getAllResolvedRequests() {
		return ersDAO.selectAllResolvedRequests();
	}

}
