package DAO;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CartBean;
public class ReportDAO {
	/*********Attributes************/
	DataSource ds;
	
	/*********Constructor************/
	public ReportDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/*********Methods************/
	public ArrayList<CartBean> generateReport(String month) throws SQLException {
		//List of bid's that were purchased in the month specified by the user
		ArrayList<String> arrayList = new ArrayList<String>();
		//Map of bookbeans with bid as key
		ArrayList<CartBean> finalList = new ArrayList<CartBean>();
		
		//1. Check VisitEvent table first and check the eventtype = purchase
		String sql1 = "select * from VisitEvent V where V.eventtype='purchase'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(sql1);
		ResultSet r = p.executeQuery();
		while(r.next()){
			
			//2. Add to the arrayList books that were purchased in the month passed in the parameter
			
			String date = r.getString("day");
			String mo = date.substring(0, 2);
System.out.println(month +  " " + mo);
			if(month.equals(mo)){
				arrayList.add(r.getString("BID"));
			}
			
		}
		r.close();
		p.close();
		
		//3. Now we have the list, and we use the bids in this list to map to the book table and get more information about the book
		for (String bid : arrayList){
			String query = "select * FROM BOOK B where B.bid='" +bid+"'";
			p = con.prepareStatement(query);
			r = p.executeQuery();
			//String bid, String title, String author, int price, String category, String url, String about
			while (r.next()) {
				finalList.add( new CartBean(r.getString("bid"),r.getString("url"), 
						r.getString("title"), 0, r.getInt("price")));
			}
		
		}
	
		r.close();
		p.close();
		con.close();
		
		for(CartBean b : finalList){
			System.out.println("BID: " + b.getBid());
		}
		
		return finalList;
	}
	
	public void updateVisitEvent(String date, String bid, String page) throws SQLException {
		try {
			Connection con = this.ds.getConnection();
			
			int indexNumber = 0;
			// 1. Get the number of rows already in the table
			String query = "SELECT * FROM VISITEVENT";
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			while(r.next()){
				indexNumber++;
			}
			
			r.close();
			p.close();
			
			Statement sta = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			r = sta.executeQuery("SELECT * FROM VISITEVENT WHERE 1=2");
			if (r.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
				// System.out.println("ResultSet non-updatable.");
			} else {
				// System.out.println("ResultSet updatable.");
			}
			r.moveToInsertRow();
			//
			// Set the new first name and last name
			r.updateString("ind", Integer.toString(indexNumber+1));
			r.updateString("day", date);
			r.updateString("bid", bid);
			r.updateString("eventtype", page);
			r.insertRow();
			r.moveToCurrentRow();
			
			r.close();
			sta.close();
			con.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}