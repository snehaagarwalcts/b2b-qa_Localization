package lscob2b.test.quickorder

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.PropertProvider

class QuickOrderPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - QuickOrderPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		
		when:"at QuickOrderPage"
		at QuickOrderPage
	
		then:"Verify translations at QuickOrderPage"
		verifyTrue(breadCrumbLink.text(), expectedValue("search.page.breadcrumb").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("search.advanced").toUpperCase())
		verifyTrue(advancedSearch(0).text(), expectedValue("search.advanced.keyword").toUpperCase())
		verifyTrue(advancedSearch(1).text(), expectedValue("search.advanced.onlyproductids").toUpperCase())
		verifyTrue(searchLink.text(), expectedValue("search.advanced.search").toUpperCase())
		verifyTrue(blankSlateHeader.text(), expectedValue("search.advanced.how.to").toUpperCase())
		verifyTrue(blankSlateContent.text() , expectedValue("search.advanced.how.to.text"))
		verifyTrue(helpLink.text(), expectedValue("quick.order.help.link.label"))
		verifyTrue(quantityAndTotal(0).text(), expectedValue("text.quantity"))
		verifyTrue(quantityAndTotal(1).text(), expectedValue("basket.page.total")+ " ")
		verifyTrue(continueshoppingLink.text()- ~/&/, expectedValue("cart.page.continue").toUpperCase())
		verifyTrue(checkOutLink.text()- ~/&/ , expectedValue("checkout.checkout").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}