package lscob2b.test.quickorder

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.PropertProviderTest

class QuickOrderPageTest extends PropertProviderTest{
	
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
		assert breadCrumbLink.text() == expectedValue("search.page.breadcrumb")
		assert masterTemplate.mainContainerLabel.text() == expectedValue("search.advanced")
		assert advancedSearch(0).text() == expectedValue("search.advanced.keyword")
		assert advancedSearch(1).text() == expectedValue("search.advanced.onlyproductids")		
		assert searchLink.text() == expectedValue("search.advanced.search")
		assert blankSlateHeader.text() == expectedValue("search.advanced.how.to")
		assert blankSlateContent.text()  == expectedValue("search.advanced.how.to.text")
		assert helpLink.text() == expectedValue("quick.order.help.link.label")
		assert quantityAndTotal(0).text() == expectedValue("order.quantity")
		assert quantityAndTotal(1).text() == expectedValue("order.total")
		assert continueshoppingLink.text()- ~/&/ == expectedValue("cart.page.continue")
		assert checkOutLink.text()- ~/&/  == expectedValue("cart.checkout")
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}

