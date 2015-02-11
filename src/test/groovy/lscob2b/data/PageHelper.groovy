package lscob2b.data

import de.hybris.geb.page.hac.console.ImpexImportPage

class PageHelper  {

	public static final String PAGE_LOGOUT = "en/logout"
	public static final String PAGE_PROFILE = "my-account/profile"
	public static final String PAGE_ADDRESS_BOOK = "my-account/address-book"
	public static final String PAGE_ORDER_HISTORY = "my-account/orders"
	public static final String PAGE_MANAGE_USERS = "my-account/manage-users/"
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
	
	static void loadImpexInHAC(browser, baseUrl, impexFile) {
		browser.go(baseUrl +"../")
		at de.hybris.geb.page.hac.LoginPage
		doLogin("admin", "nimda")
		at de.hybris.geb.page.hac.HomePage
		browser.go(baseUrl +"../"+"console/impex/import")
		at ImpexImportPage
		importTextScript(getClass().getResource( impexFile ).text)
	}
	
}
