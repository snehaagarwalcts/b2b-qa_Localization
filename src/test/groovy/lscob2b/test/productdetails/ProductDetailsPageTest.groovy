package lscob2b.test.productdetails;

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.Product
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.Ignore
import spock.lang.IgnoreRest

class ProductDetailsPageTest extends GebReportingSpec {
	
	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	/**
	 * TC BB-450 Automated Test Case: Wholesale and recommended price should be displayed on PDP
	 */
	def "wholesale and recommended retail prices should be displayed"(){

		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage
		login (user)
		at HomePage

		when: "Goto product details page"

		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage

		then: "We should be at product details page"

		recommendedRetailPriceExist()
		wholesalePriceExist()
		recommendedRetailPriceValue == product.getPriceForUser(user).retailPrice
		wholesalePriceValue == product.getPriceForUser(user).wholesalePrice
		
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}
	
	//FIXME Safari Problem	
	def "user that does not hold customer rights tries to place an order from product details page"(){
		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage
		login (user)
		at HomePage
		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage
		
		when: "add product to cart"
		addOrderQuantity('1')
		sizingGrid.addToCart()
		masterTemplate.doGoToCart()
		
		then: "check out"
		checkOut.doCheckOut()
		at CartPage
		checkAlertMessage1()
		checkAlertMessage2()
		
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | _
	}
	
	/**
	 * TC BB-521 Automated test: User can add to cart from product details page
	 */
	//FIXME Safari issue
	def "Add to cart from prdouct details page"(){
		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage
		login (user)
		at HomePage
		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage
		
		when: "add product to cart"
		addOrderQuantity('1')
		sizingGrid.addToCart()
		
		then: "check product has been added to cart"
		masterTemplate.doGoToCart()
		cartTemplate.checkItemNameExists()
		cartTemplate.checkItemStyleExists()
		cartTemplate.checkItemColorExists()
		cartTemplate.checkItemPriceExists()
		cartTemplate.checkItemQuantityExists()
		cartTemplate.checkItemTotalExists()
		
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}
	
	/**
	 * TC BB-559 Automated test: be able to place an order from Product details page
	 */
	//FIXME Safari issue
	def "place an order from product details page"(){
		
		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage
		login (user)
		at HomePage
		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage
		
		when: "add product to cart"
		addOrderQuantity('1')
		sizingGrid.addToCart()
		masterTemplate.doGoToCart()
		
		then: "check out"
		checkOut.doCheckOut()
		at CheckOutPage
		//NOTE do not place an order. It'll take up stock
		/*doPlaceOrder()
		at OrderConfirmationPage*/
		
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}
	
	//FIXME Safari issue
	def "Change order quantity on cart page"(){
		
		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage //FIXME chrome issue
		login (user)
		at HomePage
		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage
		
		when: "add product to cart"
		addOrderQuantity('1')
		sizingGrid.addToCart()
		masterTemplate.doGoToCart()
		
		then: "change the product quantity on cart page"
		at CartPage
		editQuantitiesButtonclick()
		//TODO finish the test
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}
	
	/**
	 * TC BB-645 Automated test case: Cross-Selling & Up-Selling
	 */
	@Ignore
	def "Check Up-Selling and Cross-Selling"() {
		setup:
			to LoginPage
			login(user)
			browser.go(baseUrl + "p/" + productCode)
						
		when: "At ProductDetail page"	
			at ProductDetailsPage
			
		then: "Check Up-Selling product"
			for(pc in upSellingPCs) !upSelling.itemLink(pc).empty
		
		and: "Check Cross-Selling product"
			for(pc in crossSellingPCs) !crossSelling.itemLink(pc).empty
			
		where:
			user | _ | productCode | upSellingPCs | crossSellingPCs
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _  | "00501-0039" | ["00501-1964", "00501-1711", "00501-1764", "00501-1860"] | ["00501-0101", "00501-0113", "00501-0114", "00501-1307", "00501-1622"]
	}
	
	@Ignore
	def "Check Color Switch"() {
		setup:
			to LoginPage
			login(user)
			browser.go(baseUrl + "p/" + productCode)
	
		when: "At ProductDetail page"
			at ProductDetailsPage
		
		and: "Get product details"
			def title = buyStack.title.text()
			def style = buyStack.colorStyle.text()
			def colorName = buyStack.colorName.text()
			
		then: "Change color"
			buyStack.colorItem(1).click()
			
		when: "At ProductDetail page"
			at ProductDetailsPage
		
		then: "Check different style"
			title != buyStack.title.text()
		
		where:
			user | _ | productCode 
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _ | "00501-0039" 
	}
	
	
}
