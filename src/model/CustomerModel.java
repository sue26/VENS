package model;

import java.util.ArrayList;

import bean.AddressBean;
import bean.CartBean;
import bean.UserBean;

public class CustomerModel {
	/*******Attributes******/
	private SIS model;
	private UserBean customer;
	private CartBean cartBean;
	private AddressBean addressBean;
	String result;

	/*******Constructors*****/
	public CustomerModel() {
		try {
			model = new SIS();
		} catch(ClassNotFoundException e) {
			
		}
	}
	
	/*******Methods*********/
	public UserBean registerUser(String userName, String fName, String lName, String phone, String password,
			String street, String province, String city, String country, String zip) {
		
		try {
			customer = model.addNewUser(userName, password, fName, lName, street, province, city, country, zip, phone);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;

	}
	
	public UserBean loginUser (String userName, String password) {
		try {
			customer = model.loginUser(userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public CartBean retrieveInformation(String bid) {
		try {
			cartBean = model.retrieveInformation(bid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartBean;
	}
	
	public String submitOrder(int uid, String status, ArrayList<CartBean> list) {
		try {
			result = model.submitOrder(uid, status, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	public void updateStatus()  {
		try {
			model.updateStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public AddressBean retrieveAddress(int userID) {
		try {
			addressBean = model.getAddress(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressBean;
	}
	
	public void updateUserInfo(int userID, String password, String street, String city, String province, String country, 
			String zip, String phone) {
		//Updates user password and address
		try {
			model.updateUserInfo(userID, password, street, city, province, country, zip, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public UserBean getPassword (String userName, String zip) {
		try {
			customer = model.getPassword(userName, zip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public UserBean getUserName (String fname, String lname, String zip) {
		try {
			customer = model.getUserName(fname, lname, zip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
}
