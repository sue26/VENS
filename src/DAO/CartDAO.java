package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CartBean;

public class CartDAO {
	/********Attributes*********/	
	DataSource ds;
	CartBean bean;
	
	/*********Constructor************/
	public CartDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/*********Methods************/
	public CartBean retrieveInformation(String bid) throws SQLException {
		String query = "select TITLE, URL, PRICE from book B where B.bid='"+bid+"'";	
	
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()) {
			String url = r.getString("URL");
			String title = r.getString("TITLE");
			int price = Integer.parseInt(r.getString("PRICE"));
			
			bean = new CartBean(bid, url, title, 1, price);
			System.out.println(title);

		}
		r.close();
		p.close();
		con.close();
		return bean;
	}
}
