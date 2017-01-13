package model;

import java.util.ArrayList;
import java.util.Map;

import bean.AddressBean;
import bean.BookBean;
import bean.CartBean;
import bean.ReviewBean;
import bean.UserBean;
import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.RegisterDAO;
import DAO.LoginDAO;
import DAO.CartDAO;
import DAO.PurchaseDAO;
import DAO.ReportDAO;
import DAO.ReviewDAO;

public class SIS {
		/*********Attributes************/
		private BookDAO bookDAO;
		private RegisterDAO registerDAO;
		private LoginDAO loginDAO;
		private CartDAO cartDAO;
		private PurchaseDAO purchaseDAO;
		private AddressDAO addressDAO;
		private ReviewDAO reviewDAO;
		private ReportDAO reportDAO;
		
		/*********Constructor************/
		public SIS() throws ClassNotFoundException {
			//Initializing StudentDAO and EnrollmentDAO might throw ClassNotFoundExcpetion
			bookDAO = new BookDAO();
			registerDAO = new RegisterDAO();
			loginDAO = new LoginDAO();
			cartDAO = new CartDAO();
			purchaseDAO = new PurchaseDAO();
			addressDAO = new AddressDAO();
			reviewDAO = new ReviewDAO();
			reportDAO = new ReportDAO();
		}
		
		/*********Methods************/
		public Map<String, BookBean> retrieveBook(int searchBy, String keyword, String category, String priceRange, String sortBy) throws Exception {
			// 0 = all; 1 = title; 2 = author; 3 = BID; 4 = AdvancedSearch;
			if (searchBy == 0) {
				return bookDAO.retrieveAll(keyword, category, priceRange, sortBy);
			} else if (searchBy == 1) {
				return bookDAO.retrieveTitle(keyword, category, priceRange, sortBy);
			} else if (searchBy == 2) {			
				return bookDAO.retrieveAuthor(keyword, category, priceRange, sortBy);
			} else if (searchBy == 3) {
				return bookDAO.retrieveBID(keyword);
			} else if (searchBy == 4) {
				String temp[] = keyword.split("!!");
				String title = temp[0];
				
				String author;
				if (temp.length < 2)
					author = "";
				else
					author = temp[1];
				return bookDAO.retrieveAdv(title, author, category, priceRange, sortBy);
			} else
				return null;
		}

		public UserBean addNewUser(String username, String pwd, String fName, String lName, String street, String province,
				String city, String country, String zip, String phoneNumber) throws Exception {
			return registerDAO.addNewUser(username, pwd, fName, lName, street, province, city, country, zip, phoneNumber);
		}
		public UserBean loginUser(String userName, String password) throws Exception {
			return loginDAO.loginUser(userName, password);
		}
		public CartBean retrieveInformation(String bid) throws Exception {
			return cartDAO.retrieveInformation(bid);
		}
		public String submitOrder(int uid, String status, ArrayList<CartBean> list) throws Exception{
			return purchaseDAO.submitOrder(uid, status, list);
		}
		public void updateStatus() throws Exception{
			purchaseDAO.updateStatus();
		}
		public AddressBean getAddress(int userID) throws Exception{
			return addressDAO.getAddress(userID);
		}
		public void updateUserInfo(int userID, String password, String street, String city, String province, String country,
				String zip, String phone) throws Exception {
			addressDAO.setAddress(userID, password, street, city, province, country, zip, phone);
		}
		public Map<String, ReviewBean> retrieveBookReviews(String bid) throws Exception {
			return reviewDAO.BookReviews(bid);
		}
		public void addBookReview(String bid, String userName, int rating, String review) throws Exception {
			reviewDAO.addReview(bid, userName, rating, review);
		}
		public ArrayList<CartBean> generateReport(String month) throws Exception {
			return reportDAO.generateReport(month);
		}
		public void updateVisitEvent(String date, String bid, String page) throws Exception {
			reportDAO.updateVisitEvent(date, bid, page);
		}
		public UserBean getPassword(String userName, String zip) throws Exception {
			return loginDAO.getPassword(userName.toLowerCase(), zip.toLowerCase());
		}
		public UserBean getUserName(String fname, String lname, String zip) throws Exception {
			return loginDAO.getUserName(fname.toLowerCase(), lname.toLowerCase(), zip.toLowerCase());
		}
		public Map<String, BookBean> getCategoryBooks(String category) throws Exception {
			return bookDAO.getCategoryBooks(category);
		}
}
