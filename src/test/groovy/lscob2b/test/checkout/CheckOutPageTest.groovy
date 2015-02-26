package lscob2b.test.checkout

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.quickorder.QuickOrderPage
import spock.lang.Ignore

class CheckOutPageTest extends GebReportingSpec {

//	def static User user = UserHelper.getCreditCardOnlyUser()

	//def static String targetProductString = "Levis 501"

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage

		at LoginPage
		login(user)
		at HomePage

		masterTemplate.clickQuickOrder()
		at QuickOrderPage
	}

	/**
	 * US BB-593 A customer which on credit card payment cannot choose Invoice
	 * TC BB-834 A customer which on credit card payment cannot choose Invoice
	 * 
	 */
	@Ignore //TODO Dipen - Too much element in setup! please clean it (use where terms)
	def "B2B unit with payment term code ZCC0 should only display credit card as payment option"() {
		setup:
			doSearch(targetProductCode, true)
			at QuickOrderPage
			def int cartCount = masterTemplate.cartItemCount.text().toInteger()
			waitFor { !productSizingGrids.empty }
			addLimitedStockQuantityToCart(0,1)
			def int upCartCount = masterTemplate.cartItemCount.text().toInteger()
			upCartCount == (cartCount+1)

		when: "at QuickOrderPage"
		at QuickOrderPage
		
		and: "click checkout"
		checkOutLink.click()

		and: "at Checkout page"
		at CheckOutPage

		then: "check page elements"
		checkCreditCardPaymentButtonExists()
		invoicePayment.empty
		
		where:
			user | targetProductCode
			UserHelper.getCreditCardOnlyUser() | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS) 
	}
}
