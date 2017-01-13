function AdvancedSearch(x) {
	//Check whether AdvancedSearch is opened or closed
	//Initially it is closed
	
	var a = x.innerHTML == "";
	var text = document.getElementById("searchText");
	var search = document.getElementById("searchButton");
	var list = document.getElementById("searchList");
	
	/**table: id="advanced"
	 * input(1) : "title-advanced" (size 40, maxlength 120)?
	 * 		(2) : "author-advanced"
	 * 		(3) : "price_range"
	 */
	if (a) {
		x.innerHTML = "<table style=\"float: right;\"><tr><td id=\"upArrow\">&#9650;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</td></tr><tr><td>" +
				"<table id=\"advanced\"><tr><td>" +
				"Title: </td><td><input type=\"text\" id=\"title-advanced\" name=\"advT\"></input></td><td>" +
				"Author: </td><td><input type=\"text\" id=\"author-advanced\" name=\"advA\"></input></td>" +
				"<td rowspan=\"2\"><button id=\"advancedSearch\" name=\"advancedSearch\" type=\"submit\">" +
				"<img src=\"images/advSearch.png\" id=\"advSearchImage\" alt=\"advSearch\" title=\"Advanced Search\" /></button></td><tr><td>" +
				"Category: </td><td><select id=\"category\" name=\"advC\">" +
				"<option value=\"All\">Select a category</option>" +
				"<option value=\"Science\">Science</option>" +
				"<option value=\"Engineering\">Engineering</option>" +
				"<option value=\"Romance\">Romane</option>" +
				"<option value=\"History\">History</option>" +
				"<option value=\"Fiction\">Fiction</option></td><td>" +
				"Price Range: </td><td><select id=\"price_range\" name=\"advP\">" +
				"<option value=\"All\">Select a price range</option>" +
				"<option value=\"0-100\">Under $100</option> " +
				"<option value=\"100-125\">$100 to $125</option>" +
				"<option value=\"125-150\">$125 to $150</option>" +
				"<option value=\"150-175\">$150 to $175</option>" +
				"<option value=\"175-200\">$175 to $200</option>" +
				"<option value=\"200-\">$200 and above</option></select>" +
				"</td></tr></table></td></tr></table>";
		search.disabled = true;
	}
	else {
		x.innerHTML = "";
		search.disabled = false;
	}
	return false;
}

function closeAdvanced(x) {
	var search = document.getElementById("searchButton");
	search.disabled = false;
	
	x.innerHTML = "";

}

function logOut() {
    if (confirm("Are you sure to log out?") == true) {
        alert("Successfully logged out!")
        return true;
    } else {
    	return false;
    }
}

function errorCheckName() {
	var letter = /[\W_]/;
	var username = document.getElementById("username").value;
	
	if(letter.test(username))
		document.getElementById("error1").style.display="block";	
	else
		document.getElementById("error1").style.display="none";	
}

function errorCheckPassword() {
	var pass1 = document.getElementById("password").value;
	var letter = /[\W_]/;
	document.getElementById("error2").style.display="none";	
	document.getElementById("error3").style.display="none";	
	if(pass1.length > 9)
	{
		document.getElementById("error2").style.display="block";	
	} else {
		if(letter.test(pass1))
			document.getElementById("error3").style.display="block";	
		else
		{
			document.getElementById("error2").style.display="none";	
			document.getElementById("error3").style.display="none";	
		}
	}
}
/******* Help.jspx Functions*******/

function AboutUs(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "Mom&Pop is a brick-and-mortar company with the world's largest online bookstore. " +  
						"Our mission is to help people find and purchase books they love. " + 
						"Mom&Pop launched in March 2016 and was developed by the VENS web developers team." + "</div>";
	else
		x.innerHTML = "";
	
	return false;
}

function VENS(x) {
	
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "VENS stands for Varsha, Enxhi, Nusayba & Sue. " + 
						"Our unique web developers team is the one and only team consisting of only female web developers!" + "</div>";
	else
		x.innerHTML = "";
	return false;
}

function password(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "Don't worry! If you know your phone number we can reset your password for you. "
					+ "To reset your password, click on <a href=\"\">Forgot my password</a>"+ "</div>";
	else
		x.innerHTML = "";
	return false;
}

function cart(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "You can add a book to the shopping cart by selecting the book you want and clicking on "
					+ "\"Add to Cart\" " +  
					"To remove a book from the shopping cart, click on the shopping cart icon and remove all the books you do not wish to purchase anymore." 
					+ "</div>";
	else
		x.innerHTML = "";
	return false;
}

function credit(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "Yes. Books must be purchased using a valid credit card. " + "</div>";
	else
		x.innerHTML = "";
	return false;
}

function create(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "No. You must be a regsitered user to be able to purchase books from our website. " 
						+ "</div>";
	else
		x.innerHTML = "";
	return false;
}

function rate(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "Yes. Any user may rate or add a review for a book. " 
						+ "The review of a user who is not signed in will be anonymous." + "</div>";
	else
		x.innerHTML = "";
	return false;
}

function ship(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"padding-left: 20px;\">" + "Shipment time takes around 7-10 days on average. " 
						+ "</div>";
	else
		x.innerHTML = "";
	return false;
}

/********Error Checking (Account Page) *********/
function updatePassword(p1, p2, p3, p4, p5)
{
	document.getElementById(p1).style.display="none";
	document.getElementById(p2).style.display="block";
	document.getElementById(p3).style.display="block";
	document.getElementById(p4).style.display="block";
	document.getElementById(p5).style.display="none";
}
function passwordCheck1()
{
	var password = document.getElementById("pass");
	var error1 = document.getElementById("error1");
	var error2 = document.getElementById("error2");
	var error4 = document.getElementById("error4");
	
	var letter = /[\W_]/;
	var hasError = false;
	
	// Error 1
	if(letter.test(password.value))
	{ 
		error1.style.display="block";	
		hasError = true;
	} else {
		error1.style.display="none";
	}
	// Error 2
	if(password.value.length > 9)
	{
		error2.style.display="block";	
		hasError = true;
	} else {
		error2.style.display="none";
	}
	// Error 4
	if (password.value.length == 0)
	{
		error4.style.display="block";
		hasError = true;
	} else {
		error4.style.display="none";
	}
	
	
	if (hasError)
		password.style.border="1px solid red";
	else
		password.style.border="";
	
	//Check error3 as well (confirm password wrong, changed pass1, then error3 should disappear
	if (document.getElementById("error3").style.display == "block") {
		passwordCheck2();
	}
}
function passwordCheck2() 
{
	var password1 = document.getElementById("pass").value;
	var password2 = document.getElementById("pass2");
	var error = document.getElementById("error3");
	
	if (password1 != password2.value) {
		error.style.display="block";	
		password2.style.border="1px solid red";
	} else {
		error.style.display="none";	
		password2.style.border="";
	}
		
}
function checkPhone() {
	var phone = document.getElementById("phone");
	var error5 = document.getElementById("error5");
	var error6 = document.getElementById("error6");

	var letter = /\d{3}[-]\d{3}[-]\d{4}/;
	var hasError = false;
	
	// Error 5
	if(phone.value.length == 0)
	{ 
		error5.style.display="block";	
		hasError = true;
	} else {
		error5.style.display="none";
		// Error 6
		if (!(letter.test(phone.value))) {
			error6.style.display="block";	
			hasError = true;
		} else {
			error6.style.display="none";	
		}
	}
	if (hasError)
		phone.style.border="1px solid red";
	else
		phone.style.border="";
}
function testAddress(comp, error1, error2) {
	var hasError = false;
	var letter = /^[a-zA-Z ]+$/;

	// Error 1 of each address field
	if(comp.value.length == 0) {
		error1.style.display="block";
		hasError = true;
	} else {
		error1.style.display = "none";
		
		// Error 2 of each address field
		if (!(letter.test(comp.value))) {
			error2.style.display = "block";
			hasError = true;
		} else {
			error2.style.display = "none";
		}
	}
	
	
	if (hasError)
		comp.style.border="1px solid red";
	else
		comp.style.border="";
}
function testAddress2(comp, error1, error2) {
	var hasError = false;
	var letter = /^[a-zA-Z0-9 ]+$/;

	// Error 1 of each address field
	if(comp.value.length == 0) {
		error1.style.display="block";
		hasError = true;
	} else {
		error1.style.display = "none";
		
		// Error 2 of each address field
		if (!(letter.test(comp.value))) {
			error2.style.display = "block";
			hasError = true;
		} else {
			error2.style.display = "none";
		}
	}
	
	
	if (hasError)
		comp.style.border="1px solid red";
	else
		comp.style.border="";
}
function update(p1, p2, p3)
{
	document.getElementById(p1).style.display="none";
	document.getElementById(p2).style.display="block";
	document.getElementById(p3).style.display="none";
}
function checkBeforeSave() 
{
	var pass1 = document.getElementById("pass");
	var pass2 = document.getElementById("pass2");
	var phone = document.getElementById("phone");
	var address = document.getElementById("address");
	var city = document.getElementById("acity");
	var province = document.getElementById("aprovince");
	var country = document.getElementById("acountry");
	var zip = document.getElementById("azip");

	if (pass1.style.border != "" || pass2.style.border != "" || phone.style.border != "" || address.style.border != ""
		|| city.style.border != "" || province.style.border != "" || country.style.border != "" || zip.style.border != "") {
		alert("All input must be valid!");
		return false;
	} else {
		alert("Successfully saved!");
		return true;
	}
}
function checkOut() {
	alert("Empty Shopping Cart!");
	return false;
}
function added() {
	alert("Successfully added!");
	return true;
}
function checkLogin() {
	var x1 = document.getElementById("error1");
	var x2 = document.getElementById("error2");
	var x3 = document.getElementById("error3");
	
	if (x1.style.display == "block" || x2.style.display == "block" || x3.style.display == "block") {
		alert("Please provide valid username and password");
		return false;
	} else
		return true;
}
