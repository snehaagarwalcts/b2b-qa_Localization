package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class MyAccountPageTest extends PropertProviderTest {	
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify My Account Page Fields"(){
		
		setup:
		to LoginPage
		at LoginPage		
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountpage"
		at MyAccountPage
				
		then: "Verify translations in My Account Page"	
		assert masterTemplate.breadCrumbActive.text() == expectedValue("header.link.account").toUpperCase()
		assert masterTemplate.mainContainerLabel.text()  == expectedValue("header.link.account").toUpperCase()
		assert profileLink.text()  == expectedValue("text.account.profile").toUpperCase()
		assert updatePersonalDetails.text() == expectedValue("text.account.profile.updatePersonalDetails").toUpperCase()
		assert changeYourPassword.text()  == expectedValue("text.account.profile.changePassword").toUpperCase()
		assert addressBookLink.text() == expectedValue("text.account.addressBook").toUpperCase()
		assert viewMyAddressBook.text() == expectedValue("text.account.addressBook.manageDeliveryAddresses").toUpperCase()
		assert manageUsers.text() == expectedValue("text.company.manageUser").toUpperCase()
		assert addNewUsers.text() == expectedValue("text.company.addNewUsers").toUpperCase()
		assert editUsers.text() == expectedValue("text.company.editOrDisableUsers").toUpperCase()						
		assert orderHistoryLink.text() == expectedValue("text.account.orderHistory").toUpperCase()
		assert viewOrderHistory.text() == expectedValue("text.account.viewOrderHistory").toUpperCase()
		assert accountBalanceLink.text() == expectedValue("text.account.accountBalance").toUpperCase()
		assert myAccountBalanceLink.text() == expectedValue("text.account.viewAccountBalance").toUpperCase()						
		
		where:		
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)		
	}
}
