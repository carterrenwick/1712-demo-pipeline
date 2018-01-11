package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ErsDao implements ErsDaoContract {

	private String url = "jdbc:oracle:thin:@usfdbjava.cjvxmxi6saw0.us-east-2.rds.amazonaws.com:1521:orcl";
	private String username = "revature";
	private String password = "pass1234";
	
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User selectUserByUsername(String uName) {
		User u = null;
		try (Connection con = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT * FROM ERS_User WHERE u_username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("FOUND A USER!");
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	return u;
	}
	
	/*
	 * Return userid given a username & password
	 * Returns 0 if username & password do NOT match any record
	 */
	@Override
	public int selectUserId(String uName, String pWord) {
		int userId = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call check_credentials(?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, uName);
			cs.setString(2, pWord);
			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
			
			cs.executeUpdate();
			
			userId = cs.getInt(3);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	@Override
	public List<Reimbursement> selectAllPendingRequests() {
		List<Reimbursement> reqs = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT * FROM ERS_Reimbursement WHERE r_status = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				reqs.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getString(3),
						rs.getBlob(4),rs.getString(5),rs.getString(6),rs.getInt(7),
						rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reqs;
	}

	@Override
	public List<Reimbursement> selectAllResolvedRequests() {
		List<Reimbursement> reqs = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT * FROM ERS_Reimbursement WHERE r_status IN(-1, 1)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				reqs.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getString(3),
						rs.getBlob(4),rs.getString(5),rs.getString(6),rs.getInt(7),
						rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reqs;
	}

	@Override
	public List<Reimbursement> selectRequestsByUserId(int userId, int status) {
		List<Reimbursement> reqs = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT * FROM ERS_Reimbursement WHERE u_id_author = ? AND r_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			ps.setInt(2, status);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				reqs.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getString(3),
						rs.getBlob(4),rs.getString(5),rs.getString(6),rs.getInt(7),
						rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reqs;
	}
	
	@Override
	public List<User> selectAllEmployees() {
		List<User> emps = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT * FROM ERS_User WHERE ur_id = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				emps.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return emps;
	}

	@Override
	public void insertReimbursement(Reimbursement r) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call insert_reimbursement(?,?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setDouble(1, r.getAmount());
			cs.setString(2, r.getDescription());
			cs.setInt(3, r.getAuthorId());
			cs.setInt(4, r.getType());
			
			cs.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> selectAllUsernames() {
		List<String> uNames = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT u_username FROM ERS_User";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				uNames.add(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return uNames;
	}

	@Override
	public List<String> selectAllEmails() {
		List<String> emails = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "SELECT u_email FROM ERS_User";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				emails.add(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return emails;
	}

	@Override
	public void updateUser(User u) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call update_user(?,?,?,?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, u.getId());
			cs.setString(2, u.getUsername());
			cs.setString(3, u.getPassword());
			cs.setString(4, u.getFirstname());
			cs.setString(5, u.getLastname());
			cs.setString(6, u.getEmail());
			
			cs.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void updateUserIgnoreUsernameAndEmail(User u) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call update_user_ignore_uname_email(?,?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, u.getId());
			cs.setString(2, u.getPassword());
			cs.setString(3, u.getFirstname());
			cs.setString(4, u.getLastname());
			
			cs.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserIgnoreUsername(User u) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call update_user_ignore_uname(?,?,?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, u.getId());
			cs.setString(2, u.getPassword());
			cs.setString(3, u.getFirstname());
			cs.setString(4, u.getLastname());
			cs.setString(5, u.getEmail());
			
			cs.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserIgnoreEmail(User u) {
		try(Connection conn = DriverManager.getConnection(url, username, password);){
			String sql = "call update_user_ignore_uname(?,?,?,?,?)";
		
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, u.getId());
			cs.setString(2, u.getUsername());
			cs.setString(3, u.getPassword());
			cs.setString(4, u.getFirstname());
			cs.setString(5, u.getLastname());
			
			
			cs.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
