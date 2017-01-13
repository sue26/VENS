package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.BookBean;

public class BookDAO {
	/*********Attributes************/
	private DataSource ds;
	private String query;
	private Map<String, BookBean> rv;
	private static String RANGE1 = "0-100";
	private static String RANGE2 = "100-125";
	private static String RANGE3 = "125-150";
	private static String RANGE4 = "150-175";
	private static String RANGE5 = "175-200";
	private static String RANGE6 = "200-";

	/*********Constructor************/
	public BookDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS"); 
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	/*********Methods************/
	public Map<String, BookBean> retrieveAll(String keyword, String category, String priceRange, String sortBy) throws SQLException{
		if (category == null) {
			if (priceRange == null) {
				if (sortBy == null)
				//   1. Base level Search (no, no, no)
					query = "select * from book where lower(title) like ? OR lower(author) like ?";
				else
				//   2. (no, no, yes)
					query = "select * from book where (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   3. (no, yes, no)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? OR lower(author) like ?)";			
				 else
				//   4. (no, yes, yes)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;			
			}
		} else {
			if (priceRange == null) {
				if (sortBy == null)
				//   5. (yes, no, no)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? OR lower(author) like ?)";
				else
				//   6. (yes, no, yes)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   7. (yes, yes, no)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? OR lower(author) like ?)";
				else
				//   8. (yes, yes, yes)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;
			}
		}
		rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection(); 
		PreparedStatement p = con.prepareStatement(query); 
		p.setString(1, "%" + keyword +"%"); //To prevent SQL injection
		p.setString(2, "%" + keyword +"%"); //To prevent SQL injection
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()){
			BookBean bookBean = new BookBean(r.getString("bid"), r.getString("title"), r.getString("author"), 
					r.getInt("price"), r.getString("category"), r.getString("url"), r.getString("about"));
			count++;
			rv.put("" + count, bookBean);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	

	public Map<String, BookBean> retrieveTitle(String title, String category, String priceRange, String sortBy) throws SQLException {
		if (category != null && category.equals("null"))
			category = null;
		if (priceRange != null && priceRange.equals("null"))
			category = null;
		if (sortBy != null && sortBy.equals("null"))
			sortBy = null;
		
		if (category == null) {
			if (priceRange == null) {
				if (sortBy == null)
				//   1. Base level Search (no, no, no)
					query = "select * from book where lower(title) like ?";
				else
				//   2. (no, no, yes)
					query = "select * from book where lower(title) like ? ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   3. (no, yes, no)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + "AND lower(title) like ?";			
				 else
				//   4. (no, yes, yes)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + "AND lower(title) like ? ORDER BY " + sortBy;			
			}
		} else {
			if (priceRange == null) {
				if (sortBy == null)
				//   5. (yes, no, no)
					query = "select * from book where category='" + category + "' AND lower(title) like ?";
				else
				//   6. (yes, no, yes)
					query = "select * from book where category='" + category + "' AND lower(title) like ? ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   7. (yes, yes, no)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + "AND lower(title) like ?";
				else
				//   8. (yes, yes, yes)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + "AND lower(title) like ? ORDER BY " + sortBy;
			}
		}
		
		return doSearch(query, title);
	}
	public Map<String, BookBean> retrieveAuthor(String author, String category, String priceRange, String sortBy) throws SQLException {
		if (category != null && category.equals("null"))
			category = null;
		if (priceRange != null && priceRange.equals("null"))
			category = null;
		if (sortBy != null && sortBy.equals("null"))
			sortBy = null;
		
		if (category == null) {
			if (priceRange == null) {
				if (sortBy == null)
				//   1. Base level Search (no, no, no)
					query = "select * from book where lower(author) like ?";
				else
				//   2. (no, no, yes)
					query = "select * from book where lower(author) like ? ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   3. (no, yes, no)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + "AND lower(author) like ?";			
				 else
				//   4. (no, yes, yes)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + "AND lower(author) like ? ORDER BY " + sortBy;			
			}
		} else {
			if (priceRange == null) {
				if (sortBy == null)
				//   5. (yes, no, no)
					query = "select * from book where category='" + category + "' AND lower(author) like ?";
				else
				//   6. (yes, no, yes)
					query = "select * from book where category='" + category + "' AND lower(author) like ? ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   7. (yes, yes, no)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + "AND lower(author) like ?";
				else
				//   8. (yes, yes, yes)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + "AND lower(author) like ? ORDER BY " + sortBy;
			}
		}
		
		return doSearch(query, author);
	}
	public Map<String, BookBean> retrieveBID(String bid) throws SQLException {
		query = "select * from book where bid like ?";
		return doSearch(query, bid);
	}
	public Map<String, BookBean> retrieveAdv(String title, String author, String category, String priceRange, String sortBy) throws SQLException {
		if (category == null) {
			if (priceRange == null) {
				if (sortBy == null)
				//   1. Base level Search (no, no, no)
					query = "select * from book where lower(title) like ? AND lower(author) like ?";
				else
				//   2. (no, no, yes)
					query = "select * from book where (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   3. (no, yes, no)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? AND lower(author) like ?)";			
				 else
				//   4. (no, yes, yes)
					query = "select * from book where price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;			
			}
		} else {
			if (priceRange == null) {
				if (sortBy == null)
				//   5. (yes, no, no)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? AND lower(author) like ?)";
				else
				//   6. (yes, no, yes)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;
			} else {
				int lower = getLower(priceRange);
				int upper = getUpper(priceRange);
				
				if (sortBy == null)
				//   7. (yes, yes, no)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? AND lower(author) like ?)";
				else
				//   8. (yes, yes, yes)
					query = "select * from book where category='" + category + "' AND price <=" + upper + " AND price >=" + lower + " AND (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;
			}
		}
		
		rv = new HashMap<String, BookBean>();
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, "%" + title + "%");
		p.setString(2, "%" + author + "%");
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()){
			BookBean bookBean = new BookBean(r.getString("bid"), r.getString("title"), r.getString("author"), 
					r.getInt("price"), r.getString("category"), r.getString("url"), r.getString("about"));
			count++;
			rv.put("" + count, bookBean);
		}
		
		r.close();
		p.close();
		con.close();
		return rv;
	}
	public Map<String, BookBean> getCategoryBooks(String category) throws SQLException {
		if (category.equals("all"))
			query = "select * from book";
		else
			query = "select * from book where lower(category)='" + category + "'"; 
		rv = new HashMap<String, BookBean>();

		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();

		int count = 0;
		while (r.next()){
			BookBean bookBean = new BookBean(r.getString("bid"), r.getString("title"), r.getString("author"), 
					r.getInt("price"), r.getString("category"), r.getString("url"), r.getString("about"));
			count++;
			rv.put("" + count, bookBean);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	/*********Helper Method********/
	private Map<String, BookBean> doSearch(String query, String keyword) throws SQLException {
		rv = new HashMap<String, BookBean>();

		Connection con = this.ds.getConnection(); 
		PreparedStatement p = con.prepareStatement(query); 
		p.setString(1, "%" + keyword +"%"); //To prevent SQL injection
		ResultSet r = p.executeQuery();
		
		
		int count = 0;
		while (r.next()){
			BookBean bookBean = new BookBean(r.getString("bid"), r.getString("title"), r.getString("author"), 
					r.getInt("price"), r.getString("category"), r.getString("url"), r.getString("about"));
			count++;
			rv.put("" + count, bookBean);
		}
		
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	private int getLower(String range) {
		if (range.equals(RANGE1))
			return 0;
		if (range.equals(RANGE2))
			return 100;
		if (range.equals(RANGE3))
			return 125;
		if (range.equals(RANGE4))
			return 150;
		if (range.equals(RANGE5))
			return 175;
		if (range.equals(RANGE6))
			return 200;
		
		return 0;
	}
	
	private int getUpper(String range) {
		if (range.equals(RANGE1))
			return 100;
		if (range.equals(RANGE2))
			return 125;
		if (range.equals(RANGE3))
			return 150;
		if (range.equals(RANGE4))
			return 175;
		if (range.equals(RANGE5))
			return 200;
		if (range.equals(RANGE6))
			return Integer.MAX_VALUE;
		
		return Integer.MAX_VALUE;
	}
}
