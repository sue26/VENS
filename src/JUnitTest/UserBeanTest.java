package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserBean;

public class UserBeanTest {
	
	@Test
	public void testUserBeanConstructor() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		assertEquals(userBean.getUserID(), userID);
		assertEquals(userBean.getUserName(), userName);
		assertEquals(userBean.getPassword(), password);
		assertEquals(userBean.getFirstName(), firstName);
		assertEquals(userBean.getLastName(), lastName);
	
	}
	
	@Test
	public void testSetUserID() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		userBean.setUserID(3);
		assertEquals(userBean.getUserID(), 3);
		assertEquals(userBean.getUserName(), userName);
		assertEquals(userBean.getPassword(), password);
		assertEquals(userBean.getFirstName(), firstName);
		assertEquals(userBean.getLastName(), lastName);
	
	}
	
	@Test
	public void testSetUserName() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		userBean.setUserName("john");
		assertEquals(userBean.getUserID(), userID);
		assertEquals(userBean.getUserName(), "john");
		assertEquals(userBean.getPassword(), password);
		assertEquals(userBean.getFirstName(), firstName);
		assertEquals(userBean.getLastName(), lastName);
	
	}
	
	@Test
	public void testSetPassword() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		userBean.setPassword("john");
		assertEquals(userBean.getUserID(), userID);
		assertEquals(userBean.getUserName(), userName);
		assertEquals(userBean.getPassword(), "john");
		assertEquals(userBean.getFirstName(), firstName);
		assertEquals(userBean.getLastName(), lastName);
	
	}
	
	@Test
	public void testSetFirstName() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		userBean.setFirstName("Steve");
		assertEquals(userBean.getUserID(), userID);
		assertEquals(userBean.getUserName(), userName);
		assertEquals(userBean.getPassword(), password);
		assertEquals(userBean.getFirstName(), "Steve");
		assertEquals(userBean.getLastName(), lastName);
	
	}
	
	@Test
	public void testSetLastName() {
		int userID = 1;
		String userName = "johnn";
		String password = "sjohn";
		String firstName = "John";
		String lastName = "Smith";
		
		UserBean userBean = new UserBean(userID, userName, password, firstName, lastName);
		userBean.setLastName("Steve");
		assertEquals(userBean.getUserID(), userID);
		assertEquals(userBean.getUserName(), userName);
		assertEquals(userBean.getPassword(), password);
		assertEquals(userBean.getFirstName(), firstName);
		assertEquals(userBean.getLastName(), "Steve");
	
	}
}
