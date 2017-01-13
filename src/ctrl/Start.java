package ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.UserBean;
import bean.AddressBean;
import model.CustomerModel;
import model.ReportModel;
import model.SearchModel;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/AboutUs", "/ContactUs", "/RegisterUser", 
	"/Login", "/Help", "/ShoppingCart", "/Purchase", "/Category", "/myAccount",
	"/ForgotUserName", "/ForgotPassword"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	///////////////////
	private SearchModel searchModel;
	private CustomerModel customerModel;
	private ReportModel reportModel;
	private UserBean currentUser;
	private AddressBean currentAddress;
	private String page;
	private String bookID;
	private String currentBID; //For ADD TO CART button
	private String category;
	private String priceRange;
	private String sortBy;
	private StringBuffer result;
	
	// List of book's information that the user has added into the cart
	private ArrayList<CartBean> cartList = new ArrayList<CartBean>();
	private int numberOfItemsChosen = 0;
	private double totalCost = 0.0;
	
	//PARAMETER NAMES
	private static final String MODEL = "model";
	private static final String SEARCHBY = "searchList";
	private static final String KEYWORD = "searchText";
	private static final String ADVANCED = "advancedSearch";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String CATEGORY = "category";
	private static final String PRICERANGE = "priceRange";
	private static final String SORTBY = "sortBy";
	private static final String ADVTITLE = "advT";
	private static final String ADVAUTHOR = "advA";
	private static final String ADVCATEGORY = "advC";
	private static final String ADVPRICERANGE = "advP";
	private static final String ADVSEPERATOR = "!!";
	//	ACCOUNT PAGE RELATED FINAL STRINGS
	private static final String ACCOUNTCHANGEBUTTON = "saveAccountChange";
	private static final String ACCPASSWORD = "pass";
	private static final String ACCPHONE = "phone";
	private static final String ACCSTREET = "address";
	private static final String ACCCITY = "acity";
	private static final String ACCPROVINCE = "aprovince";
	private static final String ACCCOUNTRY = "acountry";
	private static final String ACCZIP = "azip";
	//  REVIEW UPDATE RELATED FINAL STRINGS
	private static final String REVIEWBUTTON = "submitReview";
	private static final String RATING = "rating";
	private static final String REVIEW = "review";
	
	
	// BELOW FOLLOW THE ORDER AS DEFINED IN BOOKDAO CLASS
	private static final String HOME = "1";
	private static final String SEARCH = "2";
	private static final String DETAIL = "3";
	private static final String ADMIN = "4";
	
	// SERVLET PARAMETERS
	private static final String PARAMKEY = "keyword";
	private static final String PARAMSEARCHBY = "searchBy";
	private static final String PARAMCATEGORY = "category";
	private static final String PARAMPRICERANGE = "priceRange";
	private static final String PARAMSORTBY = "sortBy";
	private static final String CURRENTUSER = "username"; //UserBean!!!!
	private static final String CURRENTADDRESS = "address"; //AddressBean!!! (Address of current user)
	private static final String PARAMISADVANCED = "advanced";


	///////////////////
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//model is a Singleton class!! Since init is executed only once
		try {
			searchModel = new SearchModel();
			customerModel = new CustomerModel();
			reportModel = new ReportModel();
		} catch (Exception e) {
			throw new ServletException();
		}
		
		this.getServletContext().setAttribute(MODEL, searchModel);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		page = request.getParameter("page");
		bookID = request.getParameter("bookID");
		category = request.getParameter(CATEGORY);
		priceRange = request.getParameter(PRICERANGE);
		sortBy = request.getParameter(SORTBY);

		if (bookID != null || category != null || priceRange != null || sortBy != null) 
			page = SEARCH;
		
		if (bookID != null) {
			session.setAttribute("currentBID", bookID);
			request.getServletContext().setAttribute("visitPage", "view");
			reportModel.updateVisitEvent(currentDate(), request.getServletContext().getAttribute("visitevent").toString(),request.getServletContext().getAttribute("visitPage").toString());
		}
		
		/*************************************************/
		/******** VENS/ForgotUserName ********************/
		/*************************************************/
		if(request.getRequestURI().equals("/VENS/ForgotUserName")){
			if (request.getParameter("Submit") != null) {
				request.getServletContext().setAttribute("fname", request.getParameter("fname"));
				request.getServletContext().setAttribute("lname", request.getParameter("lname"));
				request.getServletContext().setAttribute("postalCode", request.getParameter("postalCode"));

				currentUser = customerModel.getUserName(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("postalCode"));

				if(currentUser == null){ //the information provided does not match
					this.getServletContext().setAttribute("username1", "The information provided does not match!");
					request.getServletContext().getRequestDispatcher("/ForgotUserName.jspx").forward(request, response);
					return;
				} else {
					this.getServletContext().setAttribute("username1", "Match was successful! Here is your username: " + currentUser.getUserName());
					request.getServletContext().getRequestDispatcher("/ForgotUserName.jspx").forward(request, response);
					return;
				}
			} else if (!request.getParameterMap().isEmpty()) {
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			} else {
				this.getServletContext().setAttribute("username1", "");
				request.getServletContext().getRequestDispatcher("/ForgotUserName.jspx").forward(request, response);
				return;
			}
		}
		/*************************************************/
		/******** VENS/ForgotPassword ********************/
		/*************************************************/
		if(request.getRequestURI().equals("/VENS/ForgotPassword")){
			if (request.getParameter("Submit") != null) {
					request.getServletContext().setAttribute("postalCode", request.getParameter("postalCode"));
					request.getServletContext().setAttribute("username1", request.getParameter("username1"));
					
					currentUser = customerModel.getPassword(request.getParameter("username1"), request.getParameter("postalCode"));
					
				if(currentUser == null){ //the information provided does not match
					this.getServletContext().setAttribute("password", "The information provided does not match!");
					request.getServletContext().getRequestDispatcher("/ForgotPassword.jspx").forward(request, response);
					return;
				} else {
					this.getServletContext().setAttribute("password", "Match was successful! Here is your password: " + currentUser.getPassword());
					request.getServletContext().getRequestDispatcher("/ForgotPassword.jspx").forward(request, response);
					return;
				}
			} else if (!request.getParameterMap().isEmpty()) {
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			} else {
				this.getServletContext().setAttribute("password", "");
				request.getServletContext().getRequestDispatcher("/ForgotPassword.jspx").forward(request, response);
				return;
			}
		}
		
		/*************************************************/
		/******** VENS/Account (My Account Page)**********/
		/*************************************************/
		if (request.getRequestURI().equals("/VENS/myAccount")) {
			if (request.getParameter(ACCOUNTCHANGEBUTTON) != null) {
				//   1. Customer is updating account information
				customerModel.updateUserInfo(currentUser.getUserID(), request.getParameter(ACCPASSWORD), 
						request.getParameter(ACCSTREET), request.getParameter(ACCCITY), request.getParameter(ACCPROVINCE), 
						request.getParameter(ACCCOUNTRY), request.getParameter(ACCZIP), request.getParameter(ACCPHONE));
				request.getServletContext().getRequestDispatcher("/Account.jspx").forward(request, response);
				return;
			} else if (request.getParameter(SEARCHBY) != null) {
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			} else {
				//   3. Customer is opening account page
				currentAddress = customerModel.retrieveAddress(currentUser.getUserID());
				request.getServletContext().setAttribute(CURRENTADDRESS, currentAddress);
				request.getServletContext().getRequestDispatcher("/Account.jspx").forward(request, response);
				return;
			}
		}
		
		/*************************************************/
		/********* VENS/Login (Login Page)****************/
		/*************************************************/
		if (request.getRequestURI().equals("/VENS/Login")) {
			if (request.getParameter("a") != null) {
				//   0. For Admin
				String buttonOption = request.getParameter("a");
				String month = request.getParameter("month");
				if (month == null)
					month = "01";
				String month2 = "";
				switch (month) {
				case "01": month2 = "January";
					break;
				case "02" : month2 = "February";
					break;
				case "03" : month2 = "March";
					break;
				case "04" : month2 = "April";
					break;
				case "05" : month2 = "May";
					break;
				case "06" : month2 = "June";
					break;
				case "07" : month2 = "July";
					break;
				case "08" : month2 = "August";
					break;
				case "09" : month2 = "Septempber";
					break;
				case "10" : month2 = "October";
					break;
				case "11" : month2 = "November";
					break;
				case "12" : month2 = "December";
					break;
				}
				
				if (buttonOption.equals("1")) {
					//generate report 1
					request.setAttribute("a", "1");
					request.setAttribute("month", month2);
					StringBuffer a = reportModel.generateReport(request.getParameter("month"));
					request.getServletContext().setAttribute("report", a);

				} else if (buttonOption.equals("2")) {
					//report 2
					request.setAttribute("a", "2");
				} else if (buttonOption.equals("3")) {
					//report 3
					request.setAttribute("a", "3");
				} 
				request.getServletContext().getRequestDispatcher("/Admin.jspx").forward(request, response);;
				return;
			}
			if (request.getParameter("Login") != null) {
				//   1. Customer is trying to log in	
				currentUser = customerModel.loginUser(request.getParameter(USERNAME), request.getParameter(PASSWORD));
				if (currentUser != null) {
					// LOGIN WAS SUCCESSFUL
					request.getServletContext().setAttribute("error", "");
					if (currentUser.getUserName().equals("admin")) {
						// ADMIN
						request.getServletContext().setAttribute(CURRENTUSER, currentUser);
						request.setAttribute("a", "4");
						request.getServletContext().getRequestDispatcher("/Admin.jspx").forward(request, response);;
						return;
					} else {
						// CUSTOMER
						request.getServletContext().setAttribute(CURRENTUSER, currentUser);
						response.sendRedirect("/VENS/");
						return;
					}
				} else {
					// LOGIN WAS NOT SUCCESSFUL
					request.getServletContext().setAttribute("error", "Login not successful! Wrong username or password!");
					request.getServletContext().getRequestDispatcher("/ExistingUser.jspx").forward(request, response);
					return;
				}
			} 
			else if (request.getParameter("out") != null) {
				//   2. Customer is trying to log out
				currentUser = null;
				numberOfItemsChosen = 0;
				totalCost = 0;
				cartList.clear();
				request.getServletContext().setAttribute(CURRENTUSER, null);
				response.sendRedirect("/VENS/");

				return;
			}
			else if (!request.getParameterMap().isEmpty()) {
				//   3. Customer is searching for a book
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
			else {
				//  4.  Customer opened the log in page
				request.getServletContext().getRequestDispatcher("/ExistingUser.jspx").forward(request, response);;
				return;
			}
		}
		
		/*************************************************/
		/****** VENS/RegisterUser (Register Page)*********/
		/*************************************************/
		else if (request.getRequestURI().equals("/VENS/RegisterUser")) {
			if (request.getParameter("Register") != null) {
				//   1. Customer is trying to register (Passed all error checking) 
				currentUser = customerModel.registerUser(request.getParameter("username"), request.getParameter("fname"),
						request.getParameter("lname"), request.getParameter("phonenumber"),
						request.getParameter("password"), request.getParameter("street"),
						request.getParameter("province"), request.getParameter("city"),
						request.getParameter("country"), request.getParameter("postalCode"));
				currentUser = customerModel.loginUser(currentUser.getUserName(), currentUser.getPassword());
				this.getServletContext().setAttribute(CURRENTUSER, currentUser);
				
				response.sendRedirect("/VENS/");
				return;
			} else {
				//   2. Customer opened the register page
				request.getServletContext().getRequestDispatcher("/RegisterUser.jspx").forward(request, response);;
				return;
			}
		}
		
		/*************************************************/
		/***** VENS/ShoppingCart (ShoppingCart Page) *****/
		/*************************************************/
		if (request.getRequestURI().equals("/VENS/ShoppingCart")) {
			if (request.getParameterMap().isEmpty()) {
				//   1. User wants to see their shopping cart
				//      ---Set attributes
				request.setAttribute("item", cartList);
				request.setAttribute("cost", totalCost);
				request.setAttribute("numberofitems", numberOfItemsChosen);
				request.getServletContext().getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
				return;
			} else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/*************************************************/
		/********* VENS/Purchase (Purchase Page) *********/
		/*************************************************/

		if (request.getRequestURI().equals("/VENS/Purchase")) {
			if (request.getParameterMap().isEmpty()) {
				//   1. User wants to purchase the order
				//       --- Is user logged in? Examined in the Purchase.jspx
				request.getServletContext().getRequestDispatcher("/Purchase.jspx").forward(request, response);
				return;
			} else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		if (request.getParameter("submitOrder") != null) {
			String result = "";
			if (!cartList.isEmpty()) {
				result = customerModel.submitOrder(currentUser.getUserID(), "ordered", cartList);
				
				for (CartBean bean: cartList) {
					session.setAttribute("currentBID", bean.getBid());
					request.getServletContext().setAttribute("visitPage", "purchase");
					reportModel.updateVisitEvent(currentDate(), request.getServletContext().getAttribute("visitevent").toString(),
							request.getServletContext().getAttribute("visitPage").toString());
				}
			}
			customerModel.updateStatus();
			cartList.clear();
			numberOfItemsChosen = 0;
			totalCost = 0;
			
			
			request.setAttribute("showResult", result);
			request.getRequestDispatcher("/Purchase.jspx").forward(request, response);
			return;
		}
		
		/*************************************************/
		/********* VENS/Category (Category Page)**********/
		/*************************************************/
		if (request.getRequestURI().equals("/VENS/Category")) {
			if (request.getParameter("a") != null) {
				result = searchModel.getCategories(request.getParameter("a"));
				request.setAttribute("category", result);
				request.getServletContext().getRequestDispatcher("/Category.jspx").forward(request, response);
				return;
			}
			else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/*************************************************/
		/********* VENS/AboutUS (About Us Page)***********/
		/*************************************************/
		else if (request.getRequestURI().equals("/VENS/AboutUs")) {
			if (request.getParameterMap().isEmpty()) {
				//  1. Display the About Us Page
				request.getServletContext().getRequestDispatcher("/AboutUs.jspx").forward(request, response);
				return;
			}
			else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/*************************************************/
		/********* VENS/ContactUs (Contact Us Page)*********/
		/*************************************************/
		else if (request.getRequestURI().equals("/VENS/ContactUs")) {
			if (request.getParameterMap().isEmpty()) {
				//  1. Display the Contact Us Page
				request.getServletContext().getRequestDispatcher("/ContactUs.jspx").forward(request, response);
				return;
			}
			else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/*************************************************/
		/********* VENS/AboutUS (Help Page)***************/
		/*************************************************/
		else if (request.getRequestURI().equals("/VENS/Help")) {
			if (request.getParameterMap().isEmpty()) {
				//  1. Display the Contact Us Page
				request.getServletContext().getRequestDispatcher("/Help.jspx").forward(request, response);
				return;
			}
			else {
				//  2. Display the Book Search Result Page
				String url = "/VENS/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/**************************************************/
		/********* VENS (Book Search Operation)************/
		/**************************************************/
		else {
		if (page == null) {
			//Redirect to Home Page
			request.getServletContext().getRequestDispatcher("/Home.jspx").forward(request, response);
			return;
		}
		else if (page.equals(HOME)) {  			
			/**********Home Page!!**********/
		//   1. User searches for a book
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category, priceRange, sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);
				if (request.getParameter(PRICERANGE) == null || request.getParameter(PRICERANGE).equals("null") 
						|| request.getParameter(PRICERANGE).equals("All"))
					priceRange = null;
				else
					priceRange = request.getParameter(PRICERANGE);

				result = searchModel.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
						category, priceRange, sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			//   2. Advanced Search!
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/VENS/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
		}
		else if (page.equals(SEARCH)) {
			/*********Book Search Page********/
			//   1. User searches for a book
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category, priceRange, sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);
				if (request.getParameter(PRICERANGE) == null || request.getParameter(PRICERANGE).equals("null") 
						|| request.getParameter(PRICERANGE).equals("All"))
					priceRange = null;
				else
					priceRange = request.getParameter(PRICERANGE);
				
				result = searchModel.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
							category, priceRange, sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			//   2. Advanced Search!
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/VENS/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
			
			//   3. User wants to see the book in detail
			if (bookID != null) {
				currentBID = bookID;
				result = searchModel.getBookDetail(bookID, (currentUser != null));
				request.setAttribute("replace3", result);
				request.getServletContext().getRequestDispatcher("/BookDetail.jspx").forward(request, response);
				return;
			}
			
			//----------Helper methods --> For narrowing down or sorting the result list
			if (category != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/VENS/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + category + 
						"&priceRange=" + request.getServletContext().getAttribute(PARAMPRICERANGE) +
						"&sortBy=" + request.getServletContext().getAttribute(PARAMSORTBY);
				response.sendRedirect(url);
				return;
			}
			
			if (priceRange != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/VENS/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + request.getServletContext().getAttribute(PARAMCATEGORY) + 
						"&priceRange=" + priceRange +
						"&sortBy=" + request.getServletContext().getAttribute(PARAMSORTBY);
				response.sendRedirect(url);
				return;
			}
			if (sortBy != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/VENS/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + request.getServletContext().getAttribute(PARAMCATEGORY) + 
						"&priceRange=" + request.getServletContext().getAttribute(PARAMPRICERANGE) +
						"&sortBy=" + sortBy;
				response.sendRedirect(url);
				return;
			}
		}
		else if (page.equals(DETAIL)) {
			/*********Book Detail Page********/
			//   1. User clicked on ADD TO CART button
			if (request.getParameter("addItem") != null) {
				CartBean bean = customerModel.retrieveInformation(currentBID);
				cartList.add(bean);
				totalCost += bean.getPrice();
				numberOfItemsChosen++;
				session.setAttribute("currentBID", currentBID);
				request.getServletContext().setAttribute("visitPage", "cart");
				reportModel.updateVisitEvent(currentDate(), request.getServletContext().getAttribute("visitevent").toString(),
						request.getServletContext().getAttribute("visitPage").toString());
				
				request.getServletContext().getRequestDispatcher("/Home.jspx").forward(request, response); //TODO
				return;
			}
			//   2. User is submitting a review
			if (request.getParameter(REVIEWBUTTON) != null) {
				searchModel.addBookReview(request.getParameter("bid"), currentUser.getUserName(), 
						Integer.parseInt(request.getParameter(RATING)), request.getParameter(REVIEW));
				return;
			}
			//   3. User searches for a book
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category, priceRange, sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);
				if (request.getParameter(PRICERANGE) == null || request.getParameter(PRICERANGE).equals("null") 
						|| request.getParameter(PRICERANGE).equals("All"))
					priceRange = null;
				else
					priceRange = request.getParameter(PRICERANGE);

				result = searchModel.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
						category, priceRange, sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			
			//   4. Advanced Search!
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/VENS/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
		}
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);	
	}
	
	public String currentDate(){
		String date = new SimpleDateFormat("MMddyyyy").format(new Date());
		return date;
	}
}
