package lscob2b.test.productdetails

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProvider

class ProductInvalidTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of ProductNotFound Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
		
		then: "go to PDP"
		PageHelper.gotoPageProductDetail(browser,browser.getCurrentUrl(),targetProductCode)
		
		when: "at product detail page"
		at ProductDetailsPage
		
		then: "verify translations of ProductNotFound Page message"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("breadcrumb.not.found").toUpperCase())
//		verifyTrue(masterTemplate.alertMessage.text(), expectedValue(""))
		verifyTrue(linkContinueShopping.text()- ~/&/, expectedValue("label.continue.shopping").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | targetProductCode 
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | "005010039"
	}
	
	def "Verify translations of ProductNotPartOfBrandAssortment Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
		
		then: "go to PDP"
		PageHelper.gotoPageProductDetail(browser,browser.getCurrentUrl(),targetProductCode)
		
		when: "at product detail page"
		at ProductDetailsPage
		
		then: "verify translations of ProductNotPartOfBrandAssortment Page message"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("breadcrumb.not.found").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("brand.assortment.error"))
		verifyTrue(linkContinueShopping.text()- ~/&/, expectedValue("label.continue.shopping").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | targetProductCode 
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getNotPartOfYourBrandAssortment(ProductHelper.BRAND_LEVIS)
	}
}
