package lscob2b.test.productdetails

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.productdetails.ProductDetailsPage
import spock.lang.IgnoreIf

class ProductDetailSecurityTest extends GebReportingSpec {
	
	def setup() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage 
	}
	
//	@IgnoreIf({ System.getProperty("geb.browser") == "safari" || System.getProperty("geb.browser") == "ie8" || System.getProperty("geb.browser") == "chrome"})
	def "User that does not hold customer rights tries to place an order from product detail page"() {
		setup:		
			at LoginPage	
			login(user)
			
			at HomePage
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
			
		when: "at product detail page"
			at ProductDetailsPage

		and: "add product to cart"
			waitFor { !sizingGrid.empty }
			sizingGrid.addLimitedStockQuantityToCart(1)
			
		and: "click cart page"
			masterTemplate.cartItemLink.click()
			
		then: "at cart page"	
			at CartPage
			
		when: "click on checkout"
			linkCheckout.click()

		then: "at cart page"
			at CartPage
		
		and: "check error message"
			waitFor { alertMessage.displayed }
			alertMessage.displayed

		where:
		user | productCode
//		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0]
//		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
//		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
	}

}


