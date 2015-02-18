package lscob2b.test.productdetails;

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.Product
import lscob2b.test.data.User;
import spock.lang.Ignore
import spock.lang.Stepwise;


@Stepwise
class ProductDetailTest extends GebReportingSpec {
	
	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)
	
	def static String targetProductCode = ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	
	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		
		at LoginPage
		login(user)
		
		at HomePage
		
	}
	
	/**
	 * TC BB-521 Automated test: User can add to cart from product details page
	 */
	def "Add to cart from product details page"(){
		setup:
			PageHelper.gotoPageProductDetail(browser,baseUrl,targetProductCode)
				
		when: "at product detail page"
			at ProductDetailsPage
			
		and: "get current cart count"
			def int cartCount = masterTemplate.cartItemCount.text().toInteger()
		
		and: "add product to cart"
			waitFor { !sizingGrid.empty }
			sizingGrid.addLimitedStockQuantityToCart(1)
			
		and: "get updated cart item"
			def int upCartCount = masterTemplate.cartItemCount.text().toInteger()
			
		then: "check updated cart count"
			upCartCount == (cartCount+1)
			
	}
	
	def "Check cartpage status"(){
		setup:
			PageHelper.gotoPageProductDetail(browser,baseUrl,targetProductCode)
	
		when: "at product detail page"
			at ProductDetailsPage
		
		and: "click on cart link"	
			masterTemplate.cartItemLink.click()
			
		then: "at cart page"
			at CartPage
			
		and: "check product has been added to cart"		
			cartTemplate.checkItemNameExists()
			cartTemplate.checkItemStyleExists()
			cartTemplate.checkItemColorExists()
			cartTemplate.checkItemPriceExists()
			cartTemplate.checkItemQuantityExists()
			cartTemplate.checkItemTotalExists()
		
	}
	
	/**
	 * TC BB-559 Automated test: be able to place an order from Product details page
	 */
	@Ignore //FIXME not possible to place an order
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
	
}