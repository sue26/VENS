package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.BookBean;
import bean.ReviewBean;

public class ReviewBeanTest {

	@Test
	public void testReviewBeanConstructor() {
		int rating = 4;
		String review = "ABCDE";
		
		ReviewBean reviewBean = new ReviewBean(rating, review);
		assertEquals(reviewBean.getRating(), rating);
		assertEquals(reviewBean.getReview(), review);
	}
	
	@Test
	public void testSetRating() {
		int rating = 4;
		String review = "ABCDE";
		
		ReviewBean reviewBean = new ReviewBean(rating, review);
		reviewBean.setRating(5);

		assertEquals(reviewBean.getRating(), 5);
		assertEquals(reviewBean.getReview(), review);
	}
	
	@Test
	public void testSetReview() {
		int rating = 4;
		String review = "ABCDE";
		
		ReviewBean reviewBean = new ReviewBean(rating, review);
		reviewBean.setReview("FGHIJ");

		assertEquals(reviewBean.getRating(), rating);
		assertEquals(reviewBean.getReview(), "FGHIJ");
	}
	
	@Test
	public void testSetBID() {
		int rating = 4;
		String review = "ABCDE";
		String bid = "B003";
		
		ReviewBean reviewBean = new ReviewBean(rating, review);
		reviewBean.setBid("B005");

		assertEquals(reviewBean.getRating(), rating);
		assertEquals(reviewBean.getReview(), review);
		assertEquals(reviewBean.getBid(), "B005");	
	}

}
