package JUnitTest;



import static org.junit.Assert.*;

import org.junit.Test;

import bean.AddressBean;

public class AddressBeanTest {

	@Test
	public void testAddressBeanConstructor() {
		int userID = 5;
		String street = "Eglinton St";
		String province = "Ontario";
		String city = "Toronto";
		String country = "Canada";
		String zip = "L5B3R5";
		String phone = "123-456-7890";
		AddressBean addressBean = new AddressBean(userID, street, province, city,
				 country, zip, phone);
		assertEquals(addressBean.getUserID(), userID);
		assertEquals(addressBean.getStreet(), street);
		assertEquals(addressBean.getProvince(), province);
		assertEquals(addressBean.getCity(), city);
		assertEquals(addressBean.getCountry(), country);
		assertEquals(addressBean.getZip(), zip);
		assertEquals(addressBean.getPhone(), phone);
	}
		@Test
		public void testSetUserID() {
			int userID = 4;
			String street = "Dixie Rd";
			String province = "Alberta";
			String city = "Montreal";
			String country = "Canada";
			String zip = "P1C2T9";
			String phone = "129-426-7491";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setUserID(0);
			assertEquals(addressBean.getUserID(), 0);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), phone);
	}
		
		@Test
		public void testSetStreet() {
			int userID = 0;
			String street = "Dixie Rd";
			String province = "Alberta";
			String city = "Hamilton";
			String country = "USA";
			String zip = "Q1C2W9";
			String phone = "929-422-1491";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setStreet("Loretta");;
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), "Loretta");
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), phone);
		
	}
		
		@Test
		public void testSetProvince() {
			int userID = 2;
			String street = "ABC St";
			String province = "DEF";
			String city = "GHI";
			String country = "JKL";
			String zip = "MNOPQR";
			String phone = "987-654-3210";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setProvince("Ontario");
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), "Ontario");
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetCity() {
			int userID = 9;
			String street = "River St";
			String province = "Nova Scotia";
			String city = "Halifax";
			String country = "Canada";
			String zip = "M3L9S3";
			String phone = "282-050-1216";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setCity("Ottawa");
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), "Ottawa");
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetCountry() {
			int userID = 8;
			String street = "Assiniboine Ave";
			String province = "Manitoba";
			String city = "Winnipeg";
			String country = "Canada";
			String zip = "L2C5R9";
			String phone = "447-250-0210";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setCountry("US");
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), "US");
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetZIP() {
			int userID = 6;
			String street = "Street St";
			String province = "British Columbia";
			String city = "Vancouver";
			String country = "Canada";
			String zip = "N2B2A9";
			String phone = "187-234-3311";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setZip("N2B2A4");
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), "N2B2A4");
			assertEquals(addressBean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetPhone() {
			int userID = 7;
			String street = "Burrard St";
			String province = "Alberta";
			String city = "London";
			String country = "Britian";
			String zip = "K4W3R2";
			String phone = "937-151-0230";
			AddressBean addressBean = new AddressBean(userID, street, province, city,
					 country, zip, phone);
			addressBean.setPhone("937-151-0231");
			assertEquals(addressBean.getUserID(), userID);
			assertEquals(addressBean.getStreet(), street);
			assertEquals(addressBean.getProvince(), province);
			assertEquals(addressBean.getCity(), city);
			assertEquals(addressBean.getCountry(), country);
			assertEquals(addressBean.getZip(), zip);
			assertEquals(addressBean.getPhone(), "937-151-0231");

		
	}

}
