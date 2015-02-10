package lscob2b.test.quickorder

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.TestHelper
import spock.lang.IgnoreRest

class QuickOrderTest extends GebReportingSpec {
	//FIXME issue with this in IE
	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}

	def setup() {
		to LoginPage  //FIXME Chrome issue
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def login(String username) {
		doLogin(username, defaultPassword)
	}

	def loginAsUserAndGoToQuickOrder(String user){
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage
	}

	def doRemoveProduct(){
		doRemove()
	}

	/**
	 * US BB-88 Be able to place a quick order 
	 * TC BB-436 Automated Test Case: Any User should see the "Quick Order" link in the header section of the Application,
	 * that should redirect the user to the Quick Order Page.
	 */

	def "Quick Order link is accessible"() {

		when: "logged in as any user"

		login(user)

		then: "Quick Order link is available"
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
	}

	/**
	 * TC BB-437 Automated Test Case: validate the content of the "Quick Order" Page for any user
	 */

	def "Check the Quick Order page content"(){

		when: "Logging in and going to Quick Order page"
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		then: "Correct sections/links should be visible"
		checkKeywordSearchLinkExists()
		checkAddLinkExists()
		checkSearchButtonLinkExists()
		checkPrdouctIdsOnlyLinkExists()
		checkQuantityLinkExists()
		checkTotalLinkExists()
		checkCartButtonsLinkExists()
		checkCheckOuLinkExists()

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
	}

	/**
	 * TC BB-456 Automated Test Case: be able to add to cart from quick order page
	 */
	//FIXME Safari issue
	def "Add to cart from Quick Order Page"(){
		when: "Logging in and going to Quick Order page"
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		then: "Add to Cart"
		doSearch('00501-1615')
		//sizingGrid.waitForSizingGridLoadedCompletely()
		sizingGrid.addOrderQuantity('10')
		sizingGrid.addToCart()

		and: "go to shopping cart page"
		masterTemplate.doGoToCart()
		at CartPage

		then: "Look at cart content"

		cartTemplate.checkItemNameExists()
		cartTemplate.checkItemStyleExists()
		cartTemplate.checkItemColorExists()
		cartTemplate.checkItemPriceExists()
		cartTemplate.checkItemQuantityExists()
		cartTemplate.checkItemTotalExists()

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
	}

	//FIXME Safari issue
	def "Remove product from cart Page"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage
		doSearch('00501-1615')
		//sizingGrid.waitForSizingGridLoadedCompletely()
		sizingGrid.addOrderQuantity('1')
		sizingGrid.addToCart()
		masterTemplate.doGoToCart()

		Thread.sleep(1000);

		when: "At cart page"//FIXME This should be check out page not cart page.
		at CartPage
		Thread.sleep(1000);

		then: "Remove the product from the page and you should get a message cart is empty"
		cartTemplate.doRemove()
		waitFor(5){
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
		}


		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}

	/** 
	 * TC BB-477 Automated Test Case: Remove product from Check Out page 
	 */
	/*//FIXME remove product from check out page instead of cart page
	 * def "Remove product from checkout Page"(){
	 setup:
	 login(user)
	 at HomePage
	 masterTemplate.clickQuickOrder()
	 at QuickOrderPage
	 doSearch('00501-1615')
	 //sizingGrid.waitForSizingGridLoadedCompletely()
	 sizingGrid.addOrderQuantity('1')
	 sizingGrid.addToCart()
	 masterTemplate.doGoToCart()
	 Thread.sleep(1000);
	 when: "At Check out page"//FIXME This should be check out page not cart page.
	 at CartPage
	 Thread.sleep(1000);
	 then: "Remove the product from the page and you should get a message cart is empty"
	 cartTemplate.doRemove()
	 waitFor(5){
	 $('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
	 }
	 where:
	 user << [levisUser]
	 }*/

	/**
	 * TC BB-438 Automated Test Case: Place an order from "Quick Order" Page.
	 */
	//FIXME Safari issue
	def "Place an order from Quick Order Page"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage
		doSearch('00501-1615')
		//sizingGrid.waitForSizingGridLoadedCompletely()
		sizingGrid.addOrderQuantity('1')
		sizingGrid.addToCart()

		when: "Checking out from quick order page"
		Thread.sleep(1000)
		checkOut.doCheckOut()

		then: "Place an order"//FIXME Change this for functionality of check out page instead of placing an order
		at CheckOutPage
		//Do not place an order as stock will be taken up
		/*doPlaceOrder()
		 at OrderConfirmationPage*/ 

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}

	//FIXME Safari issue
	def "user that does not hold customer rights tries to place an order from quick order page"(){
		setup: "Log in"
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		when: "add product to cart"
		doSearch('00501-1615')
		//sizingGrid.waitForSizingGridLoadedCompletely()
		sizingGrid.addOrderQuantity('1')
		sizingGrid.addToCart()

		then: "check out"
		Thread.sleep(1000)
		checkOut.doCheckOut()
		at CartPage
		checkAlertMessage1()
		checkAlertMessage2()

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
	}
	
	/**
	 * TC BB-448 Automated Test Case: Place an order from "Quick Order" Page with multiple product ID's
	 */
	
	//FIXME Safari issue
	def "Quick order with multiple product ID's"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()

		when: "At QuickOrder Page"
		at QuickOrderPage

		then: "Search Multiple ID's"
		doMultipleSearch(product1,product2)
		Thread.sleep(10000)
		getResultSize() == 2

		and: "Add first product to cart"
		editQuantities.click()
		productSizingGrids[0].addOrderQuantity('1')
		productSizingGrids[0].addToCart()

		and: "Add second product to cart"
		editQuantities1.click()
		productSizingGrids[1].addOrderQuantity('1')
		productSizingGrids[1].addToCart()

		and: "Go to cart page"
		Thread.sleep(1000)
		gotoCartPage()
		//FIXME popup problem on page

		where:
		product1 | product2 | user | _
		"05527-0458" | "00501-1615" | UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}

	/**
	 * TC BB-437 Automated Test Case: validate the content of the "Quick Order" Page for any user
	 */
	
	//FIXME Safari issue
	def "check content of check out page"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		when: "At Quick Order page look for product, add quantity, and go to check out page"
		doSearch('00501-1615')
		//sizingGrid.waitForSizingGridLoadedCompletely()
		sizingGrid.addOrderQuantity('1')
		sizingGrid.addToCart()
		checkOut.doCheckOut()

		then: "At check out page look for the common content"
		at CheckOutPage
		checkTotalExists()
		checkSubTotalExists()
		//checkIncludingExists() //This only works when SAP is connected

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}
}


