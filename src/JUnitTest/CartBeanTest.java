package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.AddressBean;
import bean.CartBean;

public class CartBeanTest {

	@Test
	public void testCartBeanConstructor() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		assertEquals(cartBean.getBid(), bid);
		assertEquals(cartBean.getbookPicture(), bookPicture);
		assertEquals(cartBean.getname(), title);
		assertEquals(cartBean.getQuantity(), quantity);
		assertEquals(cartBean.getPrice(), price);
	}
	
	@Test
	public void testSetBID() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		cartBean.setBid("B004");
		assertEquals(cartBean.getBid(), "B004");
		assertEquals(cartBean.getbookPicture(), bookPicture);
		assertEquals(cartBean.getname(), title);
		assertEquals(cartBean.getQuantity(), quantity);
		assertEquals(cartBean.getPrice(), price);
	}
	
	@Test
	public void testSetbookPicture() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		cartBean.setbookPicture("http://i1030.photobucket.com/albums/y363/varsharagav/512bfpmr0aL._SX379_BO1204203200__zpsyso0eikg.jpg");
		assertEquals(cartBean.getBid(), bid);
		assertEquals(cartBean.getbookPicture(), "http://i1030.photobucket.com/albums/y363/varsharagav/512bfpmr0aL._SX379_BO1204203200__zpsyso0eikg.jpg");
		assertEquals(cartBean.getname(), title);
		assertEquals(cartBean.getQuantity(), quantity);
		assertEquals(cartBean.getPrice(), price);
	}
	
	@Test
	public void testTitle() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		cartBean.setname("Mechanical Engineering for Hackers");
		assertEquals(cartBean.getBid(), bid);
		assertEquals(cartBean.getbookPicture(), bookPicture);
		assertEquals(cartBean.getname(), "Mechanical Engineering for Hackers");
		assertEquals(cartBean.getQuantity(), quantity);
		assertEquals(cartBean.getPrice(), price);
	}
	
	@Test
	public void testQuantity() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		cartBean.setQuantity(5);
		assertEquals(cartBean.getBid(), bid);
		assertEquals(cartBean.getbookPicture(), bookPicture);
		assertEquals(cartBean.getname(), title);
		assertEquals(cartBean.getQuantity(), 5);
		assertEquals(cartBean.getPrice(), price);
	}
	
	@Test
	public void testPrice() {
		String bid = "B005";
		String bookPicture = "http://i1030.photobucket.com/albums/y363/varsharagav/9780425280201_p0_v3_s192x300_zps18ezcojs.jpg";
		String title = "Morrigans Cross: Circle Trilogy";
		int quantity = 3;
		int price = 100;
		CartBean cartBean = new CartBean(bid, bookPicture, title, quantity,
				 price);
		cartBean.setPrice
		(200);
		assertEquals(cartBean.getBid(), bid);
		assertEquals(cartBean.getbookPicture(), bookPicture);
		assertEquals(cartBean.getname(), title);
		assertEquals(cartBean.getQuantity(), quantity);
		assertEquals(cartBean.getPrice(), 200);
	}
}
