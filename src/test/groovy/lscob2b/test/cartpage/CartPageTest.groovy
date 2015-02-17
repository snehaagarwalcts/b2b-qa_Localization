package lscob2b.test.cartpage

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage

class CartPageTest extends GebReportingSpec {
	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
	}

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}

	/**
	 * US BB-555 Text for empty cart 
	 * TC BB-779 Text for empty cart
	 */
	def "Check for empty cart message"(){
		setup:
		login(user)

		when:"at home page go to cart page"
		at HomePage
		masterTemplate.doGoToCart()
		
		then: "you'll be at cart page"
		at CartPage

		and: "Check empty cart message is displayed"
		emptyCartMessageExists()
		
		where:
		user |_
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
	}
}
