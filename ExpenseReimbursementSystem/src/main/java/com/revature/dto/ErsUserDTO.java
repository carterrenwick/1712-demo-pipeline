package com.revature.dto;

import com.revature.model.User;

public class ErsUserDTO extends User {
	
	public ErsUserDTO(int id, String username, String firstname, String lastname, String email, int role) {
		super(id, username, null, firstname, lastname, email, role);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
