package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.Ignore
import de.hybris.geb.page.hac.console.ImpexImportPage


public class WaitListTest extends GebReportingSpec{

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}

	def setup() {
		to LoginPage
	}

	/*def cleanup() {
		masterTemplate.doLogout()
	}*/

	def loginAndGoToPage(user) {
		login(user)

		at HomePage
		masterTemplate.waitListLink.click()
	}

	/**
	 * TC BB-629 Automated test case: BB-497 Order from wait list
	 */
	//FIXME IE Problem
	@Ignore
	def "Load Out Of Stock impex"(){
		when: "go to HAC login"
		browser.go(baseUrl +"../")

		then: "At HAC login"
		at de.hybris.geb.page.hac.LoginPage

		when: "at do login"
		doLogin("admin", "nimda")
		at de.hybris.geb.page.hac.HomePage
		browser.go(baseUrl +"../"+"console/impex/import")

		then:"At impex import page and import the impex"
		at ImpexImportPage
		importTextScript(getClass().getResource('/impex/OutOfStock.impex').text)
		//importScript(this.getClass().getResource('/impex/OutOfStock.impex').toString())
		checkNotification()
		logOut.click()
	}
	
	/**
	 * TC BB-510 Automated test: wait list should be accessible by user
	 */
	//FIXME Safari Problem
	@Ignore
	def "Test WaitList link"() {
		setup:
		login(user)

		when: "At homepage"
		at HomePage

		then: "Check waitlist link"
		!masterTemplate.waitListLink.empty

		and: "Click on link"
		masterTemplate.waitListLink.click()
		at WaitListPage
		masterTemplate.doLogout()

		where:
		user << [
			TestDataCatalog.getALevisUser(),
			TestDataCatalog.getADockersUser()
		]
	}

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder page and ProductDetail page.
	 */
	//FIXME IE Problem
	@Ignore
	def "Adding to waitlist from QuickOrder page"() {
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

		and: "Add item to waitlist"
		sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
		sizingGrid.clickAddToWaitList()

		and: "Go to waitlist page"
		masterTemplate.waitListLink.click()

		when: "At WaitList page"
		at WaitListPage

		then: "Check updated quantity of product"
		getProductQuantityRequested(productCode) == (currentQuantity+1)
		masterTemplate.doLogout()

		where:
		productCode 	| user
		"05527-0458"	| TestDataCatalog.getALevisUser()
		//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder page and ProductDetail page.
	 */
	//FIXME IE Problem
	@Ignore
	def "Adding to waitlist from ProductDetail page"() {
		setup:
		loginAndGoToPage(user)

		when: "At WaitList page"
		at WaitListPage

		then: "Check current quantity of product"
		int currentQuantity = getProductQuantityRequested(productCode)

		and: "Open waitlist grid at ProductDetail"
		openSizingGridAtProductDetailsPage(productCode)
		sizingGrid.clickNotifyMe()

		and: "Add item to waitlist"
		sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
		sizingGrid.clickAddToWaitList()

		and: "Go to waitlist page"
		masterTemplate.waitListLink.click()

		when: "At WaitList page"
		at WaitListPage

		then: "Check updated quantity of product"
		getProductQuantityRequested(productCode) == (currentQuantity+1)
		masterTemplate.doLogout()

		where:
		productCode 	| user
		"05527-0458"	| TestDataCatalog.getALevisUser()
		//			"05527-0458"	| TestDataCatalog.getADockersUser()
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
		int currentQuantity = getProductQuantityRequested(productCode)
		currentQuantity > 0

		and: "Edit product quantity"
		editProductQuantityRequested(productCode,currentQuantity+1)

		and: "Check edited product quantity"
		getProductQuantityRequested(productCode) == (currentQuantity+1)

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
