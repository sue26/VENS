package bean;

public class CartBean {

	/*
	 * <td>${item.pic}</td> 
	 * <td>${item.name}</td> 
	 * <td>${item.quanity}</td>
	 * <td>${item.price}</td>
	 */
	private String bid;
	private String bookPicture;
	private String title;
	private int quantity;
	private int price;
	
	public CartBean(String bid, String bookPicture, String title, int quantity, int price){
		this.bid = bid;
		this.bookPicture = bookPicture;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getBid() {
		return bid;
	}
	

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getbookPicture() {
		return bookPicture;
	}
	

	public void setbookPicture(String bookPicture) {
		this.bookPicture = bookPicture;
	}

	public String getname() {
		return title;
	}

	public void setname(String name) {
		this.title = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
