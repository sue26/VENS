package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.AddressBean;
import bean.BookBean;

public class BookBeanTest {

	@Test
	public void testBookBeanConstructor() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetBID() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setBid("B002");

		assertEquals(bookBean.getBid(), "B002");
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetTitle() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setTitle("Little Prince");

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), "Little Prince");
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetAuthor() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setAuthor("Antoine de Saint-Exupery");

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), "Antoine de Saint-Exupery");
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetPrice() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setPrice(200);

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), 200);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetCategory() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setCategory("Fiction");

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(),"Fiction");
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetURL() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setUrl("http://i1030.photobucket.com/albums/y363/varsharagav/157993_zps4gontp3p.jpg");

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), "http://i1030.photobucket.com/albums/y363/varsharagav/157993_zps4gontp3p.jpg");
		assertEquals(bookBean.getAbout(), about);
	}
	
	@Test
	public void testSetAbout() {
		String bid = "B003";
		String title = "A Brief History of Canada";
		String author = "Roger Riendeau";
		int price = 100;
		String category = "History";
		String url = "http://i1030.photobucket.com/albums/y363/varsharagav/51YztJpxKoL._SX333_BO1204203200__zpsoj6abfgm.jpg";
		String about = "hihihi";
		
		BookBean bookBean = new BookBean(bid, title, author, price,
				 category, url, about);
		bookBean.setAbout("hellohello");

		assertEquals(bookBean.getBid(), bid);
		assertEquals(bookBean.getTitle(), title);
		assertEquals(bookBean.getAuthor(), author);
		assertEquals(bookBean.getPrice(), price);
		assertEquals(bookBean.getCategory(), category);
		assertEquals(bookBean.getUrl(), url);
		assertEquals(bookBean.getAbout(), "hellohello");
	}

}
