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
		assert masterTemplate.breadCrumbActive.text() == expectedValue("header.link.account")
		assert masterTemplate.mainContainerLabel.text()  == expectedValue("header.link.account")
		assert profileLink.text()  == expectedValue("text.account.profile")
		assert updatePersonalDetails.text() == expectedValue("text.account.profile.updatePersonalDetails")
		assert changeYourPassword.text()  == expectedValue("text.account.profile.changePassword")
		assert addressBookLink.text() == expectedValue("text.account.addressBook")
		assert viewMyAddressBook.text() == expectedValue("text.account.addressBook.manageDeliveryAddresses")
		assert manageUsers.text() == expectedValue("text.company.manageUser")
		assert addNewUsers.text() == expectedValue("text.company.addNewUsers")
		assert editUsers.text() == expectedValue("text.company.editOrDisableUsers")						
		assert orderHistoryLink.text() == expectedValue("text.account.orderHistory")
		assert viewOrderHistory.text() == expectedValue("text.account.viewOrderHistory")
		assert accountBalanceLink.text() == expectedValue("text.account.accountBalance")
		assert myAccountBalanceLink.text() == expectedValue("text.account.viewAccountBalance")						
		
		where:		
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)		
	}
}
