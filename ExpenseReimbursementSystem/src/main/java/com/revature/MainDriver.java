package com.revature;

import com.revature.service.ErsService;
import com.revature.service.ErsServiceContract;

public class MainDriver {
	
	public static void main(String[] args) {
		ErsServiceContract ers = new ErsService();
		System.out.println(ers.getReimbursementsByIdAndStatus(1, 0));
   }

}

