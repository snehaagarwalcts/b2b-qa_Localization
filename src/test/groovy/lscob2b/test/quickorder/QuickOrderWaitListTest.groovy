package lscob2b.test.quickorder

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.PropertProvider

class QuickOrderWaitListTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	
	def "Verify translations of WaitList Pop Up from QuickOrderPage"() {
		
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		
		when: "At QuickOrder page"
		at QuickOrderPage

		then: "Search for product by id"
		doSearch(productCode,true)

		when: "at QuickOrder page"
		at QuickOrderPage
		
		then: "click on NOTIFY ME button"
		productSizingGrids[0].buttonNotifyMe.click()
		
		and: "check translations of WaitList Pop Up"
		verifyTrue(waitListHeader.text(), expectedValue("product.notify.me").toUpperCase())
		verifyTrue(overlayButtonAdd.text()- ~/&/, expectedValue("waitlist.add.to.waitlist").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
	
}
