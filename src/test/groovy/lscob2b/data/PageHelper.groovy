package lscob2b.data


class PageHelper  {

	public static final String PAGE_LOGOUT = "en/logout"
	public static final String PAGE_PROFILE = "my-account/profile"
	public static final String PAGE_ADDRESS_BOOK = "my-account/address-book"
	public static final String PAGE_ORDER_HISTORY = "my-account/orders"
	public static final String PAGE_MANAGE_USERS = "my-account/manage-users/"
	public static final String PAGE_VIEW_USER_DETAIL = "my-account/manage-users/details"
	public static final String PAGE_EDIT_USER_DETAIL = "my-account/manage-users/edit"
	public static final String PAGE_PRODUCT_DETAIL = "p"
	public static final String PAGE_QUICKORDER = "search/advanced"
	public static final String PAGE_ABOUT_US = "http://levistrauss.com/who-we-are/"
	
	static void gotoPage(browser, baseUrl, page) {
		browser.go(baseUrl + page)
	} 
	
	static void gotoPageLogout(browser, baseUrl) {
		gotoPage(browser, baseUrl, PAGE_LOGOUT)
	}
			
	static void gotoPageViewUserDetail(browser, baseUrl, email) {
		browser.go(baseUrl + PAGE_VIEW_USER_DETAIL + "?user=" + email)
	}
	
	static void gotoPageEditUserDetail(browser, baseUrl, email) {
		browser.go(baseUrl + PAGE_EDIT_USER_DETAIL + "?user=" + email)
	}
	
	static void gotoPageProductDetail(browser, baseUrl, productCode) {
		browser.go(baseUrl + PAGE_PRODUCT_DETAIL + "/" + productCode)
	}
		
}
