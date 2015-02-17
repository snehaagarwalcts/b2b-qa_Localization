package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.User
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
public class WaitListTest extends GebReportingSpec{

	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage

		at LoginPage
		login(user)

		at HomePage
	}

	def loginAndGoToPage(user) {
		login(user)

		at HomePage
		masterTemplate.waitListLink.click()
	}

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder
	 */
	@Ignore
	def "Adding to WaitList from QuickOrder page"() {
		setup:
			PageHelper.gotoPage(browser, baseUrl, PageHelper.PAGE_QUICKORDER)

		when: "At QuickOrder page"
			at QuickOrderPage

		and: "Search for product by id"
			doSearch(productCode,true)

		then: "at QuickOrder page"
			at QuickOrderPage

		and: "check unique result"
			checkResultSize(1)

		when: "at QuickOrder page"
			at QuickOrderPage
			
		and: "get current waitlist item count"
			def int currentWL = masterTemplate.waitListItemCount.text().toInteger()
		
		and: "add item to waitlist"
			addOutOfStockQuantityToWaitList(0,1)
			
		and: "get updated waitlist item count"
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)

		where:
			productCode 	| _
			ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	| _
	}

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder page and ProductDetail page.
	 */
	@Ignore
	def "Adding to waitlist from ProductDetail page"() {
		setup:
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
			
		when: "at product detail page"
			at ProductDetailsPage
		
		and: "get current waitlist item count"
			def int currentWL = masterTemplate.waitListItemCount.text().toInteger()
		
		and: "add item to waitlist"
			addOutOfStockQuantityToWaitList(1)
			
		and: "get updated waitlist item count"
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)

		where:
			productCode 	| _
			ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	| _
			
	}

	/**
	 * TC BB-556 Automated test: User should be able to edit product quantity from wait list
	 * @return
	 */
	//FIXME IE Problem
	@Ignore
	def "Edit quantities of product in WaitList page"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		and: "Add product to waitlist"
		openSizingGridAtProductDetailsPage(productCode)
		sizingGrid.clickNotifyMe()
		sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
		sizingGrid.clickAddToWaitList()

		and: "Go to waitlist page"
		masterTemplate.waitListLink.click()

		and: "At WaitList page"
		at WaitListPage

		and: "Get current quantity"
		int currentQuantity = getProductQuantityRequested(productCode)

		then: "Check product quantity"
		currentQuantity > 0

		and: "Edit product quantity"
		editProductQuantityRequested(productCode,currentQuantity+1)

		and: "Check edited product quantity"
		getProductQuantityRequested(productCode) > currentQuantity

		where:
		productCode 	| user
		"05527-0458"	| TestDataCatalog.getALevisUser()
		//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}

	/**
	 * BB-511 Automated test: User should be able to remove product from wait list
	 */
	//FIXME IE Problem
	@Ignore
	def "Remove product from WaitList page"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Add product to waitlist"
		openSizingGridAtProductDetailsPage(productCode)
		sizingGrid.clickNotifyMe()
		sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
		sizingGrid.clickAddToWaitList()

		and: "Go to waitlist page"
		masterTemplate.waitListLink.click()

		when: "At WaitList page"
		at WaitListPage

		then: "Check product quantity"
		getProductQuantityRequested(productCode) > 0

		and: "Remove product from waitlist"
		removeProduct(productCode)

		when: "At WaitList page"
		at WaitListPage

		then: "Check absence of product"
		getProductQuantityRequested(productCode) == 0

		where:
		productCode 	| user
		"05527-0458"	| TestDataCatalog.getALevisUser()
		//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}

	@Ignore
	def "Open waitlist grid"() {
		setup:
		loginAndGoToPage(user)
		//			println "User ${user.email}"

		when: "At WaitList page"
		at WaitListPage

		then: "Check current quantity of product"
		int currentQuantity = getProductQuantityRequested(productCode)

		and: "Open waitlist grid at QuickOrderPage"
		openSizingGridAtQuickOrderPage(productCode)
		sizingGrid.clickNotifyMe()

		and: "watilist should be displayed"
		addToWaitListForm.displayed

		then: "click close so the waitlist is not displayed anymore"
		popupBoxClose.click()
		Thread.sleep(1000)
		!addToWaitListForm.displayed
		masterTemplate.doLogout()

		where:
		productCode 	| user
		"05527-0458"	| TestDataCatalog.getALevisUser()
	}

	//FIXME create a page helper
	def openSizingGridAtQuickOrderPage(String productCode){
		browser.go(baseUrl + "search/advanced")
		at QuickOrderPage
		doSearch(productCode)
	}

	//FIXME create a page helper
	def openSizingGridAtProductDetailsPage(String productCode){
		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage
	}
}
