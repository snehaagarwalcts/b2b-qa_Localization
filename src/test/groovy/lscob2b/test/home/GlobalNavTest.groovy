package lscob2b.test.home

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class GlobalNavTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - GlobalNav"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
	
		then:"Verify translations at GlobalNav"
		assert masterTemplate.welcomeLink.text()- ~/\s+\w+/ == expectedValue("header.welcome").toUpperCase()
		assert masterTemplate.logoutLink.text() == expectedValue("header.link.logout").toUpperCase()
		assert masterTemplate.myAccountLink.text() == expectedValue("header.link.account").toUpperCase()
		assert masterTemplate.helpLink.text() == expectedValue("nav.text.help").toUpperCase()
		assert masterTemplate.languageSelector.text() == expectedValue("header.locale")
		assert masterTemplate.waitListLink.text() - ~/\d+/ == expectedValue("breadcrumb.waitlist").toUpperCase()	
		
//		masterTemplate.myAccountLink.jquery.mouseover() 
//		interact { moveToElement(masterTemplate.myAccountLink) }
//		assert masterTemplate.globalNavSubLinks(0).text() == expectedValue("text.account.profile").toUpperCase() //sub-links
//		assert masterTemplate.globalNavSubLinks(1).text() == expectedValue("text.account.addressBook").toUpperCase()
//		assert masterTemplate.globalNavSubLinks(2).text() == expectedValue("text.company.manageUser").toUpperCase()
//		assert masterTemplate.globalNavSubLinks(3).text() == expectedValue("text.account.orderHistory").toUpperCase()
//		assert masterTemplate.globalNavSubLinks(4).text() == expectedValue("text.account.accountBalance").toUpperCase()	
//		masterTemplate.helpLink.jquery.mouseover()
//		interact { moveToElement(masterTemplate.helpLink) }
//		assert masterTemplate.globalNavSubLinks(7).text() == expectedValue("text.contactus").toUpperCase()	
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
