package lscob2b.test.landingPage

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
		verifyTrue(masterTemplate.welcomeLink.text()- ~/\s+\w+/, expectedValue("header.welcome").toUpperCase())
		verifyTrue(masterTemplate.logoutLink.text(), expectedValue("header.link.logout").toUpperCase())
		verifyTrue(masterTemplate.myAccountLink.text(), expectedValue("header.link.account").toUpperCase())
		verifyTrue(masterTemplate.helpLink.text(), expectedValue("nav.text.help").toUpperCase())
		//verifyTrue(masterTemplate.languageSelector.text(), expectedValue("header.locale"))
		verifyTrue(masterTemplate.waitListLink.text() - ~/\d+/, expectedValue("breadcrumb.waitlist").toUpperCase())
		
//		masterTemplate.myAccountLink.jquery.mouseover() 
//		interact { moveToElement(masterTemplate.myAccountLink) }
//		verifyTrue(masterTemplate.globalNavSubLinks(0).text(), expectedValue("text.account.profile").toUpperCase()) //sub-links
//		verifyTrue(masterTemplate.globalNavSubLinks(1).text(), expectedValue("text.account.addressBook").toUpperCase())
//		verifyTrue(masterTemplate.globalNavSubLinks(2).text(), expectedValue("text.company.manageUser").toUpperCase())
//		verifyTrue(masterTemplate.globalNavSubLinks(3).text(), expectedValue("text.account.orderHistory").toUpperCase())
//		verifyTrue(masterTemplate.globalNavSubLinks(4).text(), expectedValue("text.account.accountBalance").toUpperCase())	
//		masterTemplate.helpLink.jquery.mouseover()
//		interact { moveToElement(masterTemplate.helpLink) }
//		verifyTrue(masterTemplate.globalNavSubLinks(7).text(), expectedValue("text.contactus").toUpperCase())		
//		masterTemplate.waitListLink.jquery.mouseover()
//		interact { moveToElement(masterTemplate.waitListLink) }
//		verifyTrue(masterTemplate.waitListEmptyMessage.text(), expectedValue("popup.waitlist.empty").toUpperCase())	
//		masterTemplate.cartItemLink.jquery.mouseover()
//		interact { moveToElement(masterTemplate.cartItemLink) }
//		verifyTrue(masterTemplate.emptyCartMessage.text(), expectedValue("popup.cart.empty").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
