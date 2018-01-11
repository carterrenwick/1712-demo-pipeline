package com.revature.model;

import java.sql.Blob;

public class Reimbursement {

	private int id;
	private double amount;
	private String description;
	private Blob receipt;
	private String dateSubmitted;
	private String dateResolved;
	private int authorId;
	private int resolverId;
	private int type;
	private int status;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, double amount, String description, String dateSubmitted, String dateResolved,
			int authorId, int resolverId, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}

	
	
	public Reimbursement(int id, double amount, String description, Blob receipt, String dateSubmitted,
			String dateResolved, int authorId, int resolverId, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", receipt="
				+ receipt + ", dateSubmitted=" + dateSubmitted + ", dateResolved=" + dateResolved + ", authorId="
				+ authorId + ", resolverId=" + resolverId + ", type=" + type + ", status=" + status + "]";
	}


	
}
