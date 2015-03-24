package lscob2b.test.checkout

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.quickorder.QuickOrderPage

	
class SelectDeliveryAddressTest extends GebReportingSpec {

	def static String targetProductCode = ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0]

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
	}

	/**
	 * US BB-501 BB-38 Remove default delivery address
	 * TC BB-891 You should not be able to place an order without selecting an address
	 */

	def "Checkout without selecting an address"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()

		when:"at quickorder page"
		at QuickOrderPage

		then: "search for a product"
		doSearch(targetProductCode, true)
		
		when: "at quick order page"
		at QuickOrderPage

		and: "add item to cart"
		waitFor { !productSizingGrids.empty }
		addLimitedStockQuantityToCart(0,1)
		
		then: "at quick order page"
		at QuickOrderPage

		when:"click checkout"
		masterTemplate.waitForSometime()
		checkOutLink.click() //issue with firefox

		and: "at Checkout page"
		at CheckOutPage
		
		then: "click place order"
		placeOrderLink.click()
		
		and: "You should remain on the checkout page and see please select delivery address"
		at CheckOutPage
		checkNoDeliveryAddressSelectedExists()
		
		and: "remove from cart"
		masterTemplate.doGoToCart()
		at CartPage
		doRemove()

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
	}
}
