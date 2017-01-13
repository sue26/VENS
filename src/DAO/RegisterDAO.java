package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class RegisterDAO {
	/*******Attributes********/
	DataSource ds;
	UserBean user;

	/******Constructor********/
	public RegisterDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**********Methods*********/
	public UserBean addNewUser(String username, String pwd, String fName, String lName, String street, String province, String city,
			String country, String zip, String phoneNumber) throws SQLException {
		try {
			Connection con = this.ds.getConnection();
			Statement sta = con.createStatement();
			
			// get the number of rows from the result set
			ResultSet r = sta.executeQuery("SELECT count(*) FROM customer");
			r.next();
		    int rowCount = r.getInt(1);   
		    r.close();
		 
		    rowCount++;
		    
			//   1.  Update Customer Table
			sta.executeUpdate("INSERT INTO customer " + "VALUES (" + rowCount + ", \'" + username + "\', \'" + pwd 
					+ "\', \'" + fName + "\', \'" + lName + "\')");
			user = new UserBean(rowCount, username, pwd, fName, lName);
			
			
			////////////////////////////////////////////
			//   2. Update Address Table
			sta.executeUpdate("INSERT INTO address " + "VALUES (" + rowCount + ", \'" + street + "\', \'" + province 
					+ "\', \'" + city + "\', \'" + country  + "\', \'" + zip  + "\', \'" + phoneNumber + "\')");
			
			
			sta.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

}
