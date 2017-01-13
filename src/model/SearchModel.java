package model;

import java.util.Map;

import bean.BookBean;
import bean.ReviewBean;

public class SearchModel {	
	/*********Attributes************/
	private SIS model;
	
	private static final int ALL = 0;
	private static final int TITLE = 1;
	private static final int AUTHOR = 2;
	private static final int BID = 3;
	
	private Map<String, BookBean> map;
	private Map<String, ReviewBean> reviewMap;
	private BookBean bean;
	private StringBuffer arg1;
	
	/*********Constructor************/
	public SearchModel() {
		try {
			model = new SIS();
		} catch(ClassNotFoundException e) {
			
		}
	}
	
	/*********Methods************/
	public StringBuffer getBookSearchResult(String searchBy, String keyword, String category, String priceRange, String sortBy) {
		arg1 = new StringBuffer();
		try {
			if (searchBy.equals("all")) {
				//lower case 'keyword' before executing SQL statement
				map = model.retrieveBook(ALL, keyword.toLowerCase(), category, priceRange, sortBy);
			} else if (searchBy.equals("title")) {
				//lower case 'title' before executing SQL statement
				map = model.retrieveBook(TITLE, keyword.toLowerCase(), category, priceRange, sortBy);
				
			} else if (searchBy.equals("author")) {
				//lower case 'author' before executing SQL statement
				map = model.retrieveBook(AUTHOR, keyword.toLowerCase(), category, priceRange, sortBy);
				
			} else {
				//Advanced Search
				map = model.retrieveBook(4, keyword.toLowerCase(), category, priceRange, sortBy);
			}

			arg1.append(printResult(map.size(), searchBy, keyword, category, priceRange, sortBy));

			for(int i = 0; i < map.size(); i++ ) {
				int count = i + 1;
				bean = map.get("" + count);
				
				reviewMap = model.retrieveBookReviews(bean.getBid());
				int totalRating = 0;
				int avgRating = 0;
				int counter = 0;
				for (ReviewBean rating: reviewMap.values()) {
					counter++;
					totalRating += rating.getRating();
				}
				if (counter != 0)
					avgRating = totalRating / counter;
				
				arg1.append("<tr><td>");
				arg1.append("<a href=\"/VENS/?bookID=" + bean.getBid() + "\">"); //hyperlink
				arg1.append("<img src=\"" + bean.getUrl() +"\" alt=\"book1 image\" width=\"150\" height=\"200\" style=\"float:left; padding: 10px;\">"); //image
				arg1.append("</img><h3>" + bean.getTitle() + "</h3></a>"); //title
				arg1.append("<h5><i>By: <a href=\"" + "/VENS/?page=2&searchList=author&searchText=" + bean.getAuthor() +"\">" + bean.getAuthor() +"</a></i></h5>"); //author
				arg1.append(""); //space
				arg1.append("<h5>Rating:"); //rating
				if(avgRating == 1) {
					arg1.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></h5>");

				} else if(avgRating == 2) {
					arg1.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></h5>");

				} else if(avgRating == 3) {
					arg1.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></h5>");

				} else if(avgRating == 4) {
					arg1.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></h5>");

				} else if(avgRating == 5) {
					arg1.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></h5>");

				} else {
					//avgRating == 0
					arg1.append("<img src=\"images/0.png\" width=\"90\" height=\"20\" alt=\"0 Start\"></h5>");
				}
				//////////////////
				arg1.append("");
				arg1.append("<h5>Price: $" + bean.getPrice() + "<br /></h5>");
				//////////////
				arg1.append("</td></tr>");
			}			
		} catch (Exception e) {
			System.out.println("NULL MAP RETURNED!");
			e.printStackTrace();
		}		
		return arg1;
	}
	
	
	public StringBuffer getBookDetail(String bid, boolean signedIn) {
		arg1 = new StringBuffer();
		
		try {
			map = model.retrieveBook(BID, bid, null, null, null);
			bean = map.get("1");
			
			arg1.append("<tr><td style=\"width: 30%;\" rowspan=\"7\">");
			arg1.append("<img src=\"" + bean.getUrl() + "\" class=\"bookImage2\" alt=\"book image\"></td></tr>");
			arg1.append("<tr><td><h2><b>");
			arg1.append(bean.getTitle() + "</b></h2></td></tr><tr><td><i> By: "
					+ "<a href=\"" + "/VENS/?page=2&searchList=author&searchText=" + bean.getAuthor() +"\">");
			arg1.append(bean.getAuthor() + "</a></i><br></br></td></tr>");
			arg1.append("<tr><td>" + bean.getAbout() + "<br></br></td></tr>");
			arg1.append("<tr><td><b>PRICE: $</b>");
			arg1.append("<f:formatNumber type=\"currency\">");
			arg1.append((double)bean.getPrice() + "</f:formatNumber><br></br></td></tr><tr><td>");
			arg1.append("Rating: ");
			
			reviewMap = model.retrieveBookReviews(bean.getBid());
			int totalRating = 0, counter = 0, avgRating = 0;
			for(ReviewBean rating : reviewMap.values()) {
				totalRating += rating.getRating();
				counter++;
			}
			
			if (counter != 0)
				avgRating = totalRating / counter;
			
			if(avgRating == 1) {
				arg1.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></td></tr>");

			} else if(avgRating == 2) {
				arg1.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></td></tr>");

			} else if(avgRating == 3) {
				arg1.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></td></tr>");

			} else if(avgRating == 4) {
				arg1.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></td></tr>");

			} else if(avgRating == 5) {
				arg1.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></td></tr>");

			} else {
				arg1.append("<img src=\"images/0.png\" width=\"90\" height=\"20\" alt=\"0 Star\"></td></tr>");
			}
			arg1.append("</td></tr>");		
			arg1.append("<tr><td style=\"border-bottom:1px solid #ddd;\"><form action=\"\"><input type=\"hidden\" name=\"page\" + value=\"3\" />"
					+ "<button type=\"submit\" id=\"addItem\" name=\"addItem\" "
					+ "onclick=\"added()\" style=\"background-color: #b30000; cursor: pointer; border: none; "
					+ "color: white; padding: 10px 15px; text-align: center; font-size: 15px;\">"
					+ "Add to Cart</button></form></td></tr></table>");
			
			////////////////////Rating
			arg1.append("<table><tr><td>"
					+ "<table>"
					+ "<tr><td><b>Rate the Book!</b> <br /></td></tr>"
					+ "<tr><td>");
			if (!signedIn)
				arg1.append("<font color=\"#595959\" size=\"3\"><i>Please log in to add a review.</i></font></td></tr>");
			else
				arg1.append("<form action=\"\"><tr><td>"
					+ "<input type=\"hidden\" name=\"bid\" value=\"" + bid + "\"/>"
					+ "<input type=\"hidden\" name=\"page\" value=\"3\"/>"
					+ "<input type=\"radio\" name=\"rating\" value=\"1\" checked> <img src=\"images/1.png\" width=\"46\" height=\"10\" alt=\"1 Star\"> &#160;"
					+ "<input type=\"radio\" name=\"rating\" value=\"2\"> <img src=\"images/2.png\" width=\"46\" height=\"10\" alt=\"2 Star\"> &#160;"
					+ "<input type=\"radio\" name=\"rating\" value=\"3\"> <img src=\"images/3.png\" width=\"46\" height=\"10\" alt=\"3 Star\"> &#160;"
					+ "<input type=\"radio\" name=\"rating\" value=\"4\"> <img src=\"images/4.png\" width=\"46\" height=\"10\" alt=\"4 Star\"> &#160;"
					+ "<input type=\"radio\" name=\"rating\" value=\"5\"> <img src=\"images/5.png\" width=\"46\" height=\"10\" alt=\"5 Star\"> "
					+ "</td></tr><tr><td>Write a review: </td></tr><tr><td>"
					+ "<textarea name=\"review\" rows=\"6\" cols=\"50\" maxlength=\"1000\" placeholder=\"Maxmimum 1000 characters...\"></textarea></td></tr>"
					+ "<tr><td><button type=\"submit\" name=\"submitReview\">Submit</button></td></tr></form></td></tr></table></td>"
					+ "<td><table>"
					+ "<tr><td><b>Reviews:</b></td></tr>"); 
			
			if (reviewMap.values().isEmpty())
				arg1.append("<tr><td><font color=\"#595959\" size=\"2\"><i>No Reviews... (Yet!)</i></font></td></tr>");
			else {
				for(ReviewBean review : reviewMap.values()) {
					
					arg1.append("<tr><td><font color=\"\" size=\"2\"><i>" + review.getUsername() + "</i></font>&#160;");
					if(review.getRating() == 1) {
						arg1.append("<font color=\"#595959\" size=\"2\">"
								+ "<img src=\"images/1.png\" width=\"68\" height=\"15\" alt=\"1 Star\">"
								+ "</td></tr></font>");
					} else if(review.getRating() == 2) {
						arg1.append("<font color=\"#595959\" size=\"2\">"
								+ "<img src=\"images/2.png\" width=\"68\" height=\"15\" alt=\"2 Star\">"
								+ "</td></tr></font>");
					} else if(review.getRating() == 3) {
						arg1.append("<font color=\"#595959\" size=\"2\">"
								+ "<img src=\"images/3.png\" width=\"68\" height=\"15\" alt=\"3 Star\">"
								+ "</td></tr></font>");
					} else if(review.getRating() == 4) {
						arg1.append("<font color=\"#595959\" size=\"2\">"
								+ "<img src=\"images/4.png\" width=\"68\" height=\"15\" alt=\"4 Star\">"
								+ "</td></tr></font>");
					} else if(review.getRating() == 5) {
						arg1.append("<font color=\"#595959\" size=\"2\">"
								+ "<img src=\"images/5.png\" width=\"68\" height=\"15\" alt=\"5 Star\">"
								+ "</td></tr></font>");
					} else {
					}
					arg1.append("</td></tr><tr><td><font color=\"#595959\" size=\"2\">" + review.getReview() + "</font><hr /></td></tr>");
				}
			}
			arg1.append("</table></td></tr>");
			
		} catch (Exception e) {
			
		}
		return arg1;
	}
	
	public void addBookReview(String bid, String userName, int rating, String review) {
		try {
			model.addBookReview(bid, userName, rating, review);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*********Helper Method**************/
	private StringBuffer printResult(int mapSize, String searchBy, String keyword, String category, String priceRange, String sortBy) {
		StringBuffer arg1 = new StringBuffer();
		
		if (searchBy.isEmpty()) {
			String temp[] = keyword.split("!!");
			String title = temp[0];
			String author;
			if (temp.length < 2)
				author = "";
			else
				author = temp[1];
			//Advanced Search
			arg1.append("<tr><td>" + map.size() + " results for <b>title: \"" + title + "\" AND author: \"" + author + "\"</b>");
		}
		else 
			//Normal Search
			arg1.append("<tr><td>" + map.size() + " results for <b>" + searchBy + ": \"" + keyword + "\"</b>");
		
		if (category != null && !category.equals("null"))
			arg1.append("<i>: " + category + "</i>");
		if (priceRange != null && !priceRange.equals("null")) 
			arg1.append("<i>: $ " + priceRange + "</i>");
		if (sortBy != null && !sortBy.equals("null"))
			arg1.append("<i>: (Sorted By: " + sortBy + ")</i>");
		else
			arg1.append("<i>: (Sorted By: Relevance)</i>");
		arg1.append("</td></tr>");
		
		return arg1;
	}
	public StringBuffer getCategories(String category) {
		arg1 = new StringBuffer();
		
		try {
			map = model.getCategoryBooks(category);

			arg1.append("<tr><td><b>" + category + " books:</b>");
			arg1.append("<br /><i>" + map.size() + " Results</i></td></tr>");
			
			for(int i = 0; i < map.size(); i++ ) {
				int count = i + 1;
				bean = map.get("" + count);
				int totalRating = 0;
				int avgRating = 0;
				int counter = 0;
				reviewMap = model.retrieveBookReviews(bean.getBid());
				
				for(ReviewBean rating : reviewMap.values()) {
					totalRating = totalRating + rating.getRating();
					counter++;
				}
				if(counter != 0) avgRating = (totalRating/counter);
				
				//arg1.append("<tr><td>" + map.size() + " Results</td></tr>");
					arg1.append("<tr><td>");
					arg1.append("<a href=\"/VENS/?bookID=" + bean.getBid() + "\">"); //hyperlink
					arg1.append("<img src=\"" + bean.getUrl() +"\" alt=\"book1 image\" width=\"150\" height=\"200\" style=\"float:left; padding: 10px;\">"); //image
					arg1.append("</img><h3>" + bean.getTitle() + "</h3></a>"); //title
					arg1.append("<h5><i>By: <a href=\"\">" + bean.getAuthor() +"</a></i></h5>"); //author
					arg1.append(""); //space
					arg1.append("<h5>Rating:"); //rating
					if(avgRating == 1) {
						arg1.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></h5>");
	
					} else if(avgRating == 2) {
						arg1.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></h5>");
	
					} else if(avgRating == 3) {
						arg1.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></h5>");
	
					} else if(avgRating == 4) {
						arg1.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></h5>");
	
					} else if(avgRating == 5) {
						arg1.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></h5>");
	
					} else {
						arg1.append("no rating</h5>");
					}
					//////////////////
					arg1.append("<h5>Price: $" + bean.getPrice() + "<br /></h5>");
					//////////////
					arg1.append("</td></tr>");

			}
		} catch (Exception e) {
			System.out.println("SOMETHINGS WRONG");
			e.printStackTrace();
		}		
		return arg1;
	}
}
