package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage

class HomePageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - Home Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
	
		then:"check content translations at ContactUsPage"
		assert masterTemplate.welcomeLink.text() == expectedValue("header.welcome") //Global Nav links
		assert masterTemplate.logoutLink.text() == expectedValue("header.link.logout")
		assert masterTemplate.myAccountLink.text() == expectedValue("header.link.account")
		assert masterTemplate.helpLink.text() == expectedValue("nav.text.help")
		assert masterTemplate.languageSelector.text() == expectedValue("header.locale")
		assert masterTemplate.globalNavSubLinks(0).text() == expectedValue("text.account.profile") //sub-links
		assert masterTemplate.globalNavSubLinks(1).text() == expectedValue("text.account.addressBook")
		assert masterTemplate.globalNavSubLinks(2).text() == expectedValue("text.company.manageUser")
		assert masterTemplate.globalNavSubLinks(3).text() == expectedValue("text.account.orderHistory")
		assert masterTemplate.globalNavSubLinks(4).text() == expectedValue("text.account.accountBalance")		
		assert masterTemplate.globalNavSubLinks(7).text() == expectedValue("text.contactus")	
		assert masterTemplate.quickOrderLink.text() == expectedValue("search.advanced.title")
		assert masterTemplate.searchText == expectedValue("search.advanced.search")	
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
