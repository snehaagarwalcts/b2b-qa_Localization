package lscob2b.test.localization.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.myaccount.MyAccountPage;
import lscob2b.pages.myaccount.OrderHistoryPage;
import lscob2b.test.data.PropertProviderTest

class OrderHistoryPageTest extends PropertProviderTest {
		
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify OrderHistory Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on OrderHistory Link "
		
		when: "at OrderHistoryPage"
		at OrderHistoryPage
	
		then: "Verify Fields at OrderHistoryPage"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
