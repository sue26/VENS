package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ReviewBean;
import bean.UserBean;

public class ReviewDAO {
	DataSource ds;
	ReviewBean book;

	public ReviewDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, ReviewBean> BookReviews(String bid) throws SQLException {
		String query = "select * from REVIEW where bid=\'" + bid + "\'"; 
		HashMap<String, ReviewBean> rb = new HashMap<String, ReviewBean>();
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()) {
			query = "select username from CUSTOMER where userid=" + r.getInt("USERID");
			PreparedStatement p1 = con.prepareStatement(query);
			ResultSet r1 = p1.executeQuery();
			
			r1.next();
			
			book = new ReviewBean(r.getString("BID"), r1.getString("USERNAME"), r.getInt("RATING"), r.getString("REVIEW")); 
			rb.put("" + ++count, book);
		}
		
		r.close();
		p.close();
		con.close();
		return rb;
	}
	
	public void addReview(String bid, String userName, int rating, String review) throws SQLException {
		ReviewBean bean = new ReviewBean(bid, userName, rating, review);
		try {
			Connection con = this.ds.getConnection();
			Statement sta = con.createStatement();
			ResultSet r = sta.executeQuery("SELECT count(*) FROM review");

			// get the number of rows from the result set
			r.next();
		    int rowCount = r.getInt(1);
		    
		    r.close();
		    
		    //get userid from customer table
		    ResultSet r1 = sta.executeQuery("select userid from customer where username=\'" + userName + "\'");
		    r1.next();
		    int userid = r1.getInt("USERID");
		    r1.close();
		    
		    rowCount++;
			sta.executeUpdate("INSERT INTO review " + "VALUES (" + rowCount + ", \'" + bid + "\', " + userid 
					+ ", " + rating + ", \'" + review + "\')");
			
		
			sta.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
