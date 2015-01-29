package lscob2b.test.productdetails;

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.CheckOut.CheckOutPage
import lscob2b.pages.OrderConfirmation.OrderConfirmationPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.Product
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import lscob2b.test.data.User
import spock.lang.IgnoreIf
import spock.lang.IgnoreRest;

class ProductDetailsPageTest extends GebReportingSpec {
	
	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}

	def "wholesale and recommended retail prices should be displayed"(){

		User user = TestDataCatalog.getALevisUser()
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
	}
	
	def "Add to cart from prdouct details page"(){
		User user = TestDataCatalog.getACustomerUser()
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
	}
	
//	@IgnoreIf({ System.getProperty("geb.browser").contains("safari") })
	def "place an order from product details page"(){
		
		User user = TestDataCatalog.getACustomerUser()
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
		doPlaceOrder()
		at OrderConfirmationPage
	}
	
//	@IgnoreIf({ System.getProperty("geb.browser").contains("safari") })
	def "user that does not hold customer rights tries to place an order from product details page"(){
		User user = TestDataCatalog.getALevisUser()
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
	}
	
	def "Check Up-Selling and Cross-Selling"() {
		setup:
			to LoginPage
			login(user)
			browser.go(baseUrl + "p/" + productCode)
						
		when: "At ProductDetail page"	
			at ProductDetailsPage
			
		then: "Check Up-Selling product"
			for(pc in upSellingPCs) !upSelling.getItemLink(pc).empty
		
		and: "Check Cross-Selling product"
			for(pc in crossSellingPCs) !crossSelling.getItemLink(pc).empty
			
		where:
			user | productCode | upSellingPCs | crossSellingPCs
			TestDataCatalog.getALevisUser() | "00501-0039" | ["00501-1964", "00501-1711", "00501-1764", "00501-1860"] | ["00501-0101", "00501-0113", "00501-0114", "00501-1307", "00501-1622"]
	}
	
	
}
