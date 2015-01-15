package lscob2b.test.productdetails;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Ignore;
import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.pages.CheckOut.CheckOutPage;
import lscob2b.pages.OrderConfirmation.OrderConfirmationPage;
import lscob2b.test.data.Product;
import lscob2b.test.data.TestDataCatalog;
import lscob2b.test.data.User;
import lscob2b.test.login.LoginFailureTest;
import lscob2b.TestConstants
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;
import spock.lang.IgnoreRest
import static lscob2b.TestConstants.*

public class ProductDetailsPageTest extends GebReportingSpec {

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
	
	@Ignore
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
		addOrderQuantity('10')
		sizingGrid.addToCart()
		masterTemplate.doGoToCart()
		
		then: "check out"
		checkOut.doCheckOut()
		at CheckOutPage
		doPlaceOrder()
		at OrderConfirmationPage
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
		addOrderQuantity('10')
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
}
