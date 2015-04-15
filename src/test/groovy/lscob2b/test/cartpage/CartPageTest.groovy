package lscob2b.test.cartpage

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.test.data.PropertProvider

class CartPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of empty Cart Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.doGoToCart()
		
		when: "at cart page"
		at CartPage
		
		then: "verify translations of empty cart"
		verifyTrue(masterTemplate.mainContainerLabel.text(),expectedValue("heading.cart.page").toUpperCase())
		verifyTrue(continueShopping.text()- ~/&/,expectedValue("label.continue.shopping").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)		
	}
}