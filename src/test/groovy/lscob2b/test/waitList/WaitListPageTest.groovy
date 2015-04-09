package lscob2b.test.waitList

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.PropertProviderTest

class WaitListPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of empty WaitList Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.waitListLink.click()
		
		when:"at WaitListPage"
		at WaitListPage
	
		then:"Verify translations at empty WaitList Page"
		assert masterTemplate.mainContainerLabel.text() == expectedValue("label.your.waitlist").toUpperCase()
		assert continueToShoppingLink.text()- ~/&/ == expectedValue("label.continue.shopping").toUpperCase()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
