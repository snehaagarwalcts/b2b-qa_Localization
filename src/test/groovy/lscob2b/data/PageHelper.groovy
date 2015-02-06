package lscob2b.data

class PageHelper  {

	public static final String PAGE_LOGOUT = "en/logout"
	public static final String PAGE_MANAGE_USERS = "my-account/manage-users"
	public static final String PAGE_VIEW_USER_DETAIL = "my-account/manage-users/details"
	public static final String PAGE_EDIT_USER_DETAIL = "my-account/manage-users/edit"
	
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
	
}
