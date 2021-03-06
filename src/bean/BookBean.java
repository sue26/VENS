package bean;

public class BookBean {
	/*********Attributes************/
	private String bid;
	private String title;
	private String author;
	private int price;
	private String category;
	private String url;
	private String about;
	
	/*********Constructor************/
	public BookBean(String bid, String title, String author, int price, String category, String url, String about) {
		this.bid = bid;
		this.title = title;
		this.author = author;
		this.price = price;
		this.category = category;
		this.url = url;
		this.about = about;
	}

	/*********Methods************/
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}	
}
