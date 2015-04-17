package lscob2b.test.productdetails

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProvider

class ProductDetailsWaitListTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	
	def "Verify translations of WaitList Pop Up from Product Details Page"() {		
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
		
		then: "go to Product Details Page"
		PageHelper.gotoPageProductDetail(browser,browser.getCurrentUrl(),targetProductCode)
		
		when: "at Product Details Page"
		at ProductDetailsPage
		
		then: "click on NOTIFY ME button"
		addToCart.buttonNotifyMe.click()
		
		and: "check translations of WaitList Pop Up"
		verifyTrue(overlayHeader.text(), expectedValue("product.notify.me").toUpperCase())
		verifyTrue(overlayButtonAdd.text()- ~/&/, expectedValue("waitlist.add.to.waitlist").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | targetProductCode 
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
	
}
