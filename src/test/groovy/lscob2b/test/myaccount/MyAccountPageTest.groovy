package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProvider

class MyAccountPageTest extends PropertProvider {	
	
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
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("header.link.account").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text() , expectedValue("header.link.account").toUpperCase())
		verifyTrue(profileLink.text() , expectedValue("text.account.profile").toUpperCase())
		verifyTrue(updatePersonalDetails.text(), expectedValue("text.account.profile.updatePersonalDetails").toUpperCase())
		verifyTrue(changeYourPassword.text() , expectedValue("text.account.profile.changePassword").toUpperCase())
		verifyTrue(addressBookLink.text(), expectedValue("text.account.addressBook").toUpperCase())
		verifyTrue(viewMyAddressBook.text(), expectedValue("text.account.addressBook.manageDeliveryAddresses").toUpperCase())
		verifyTrue(manageUsers.text(), expectedValue("text.company.manageUser").toUpperCase())
		verifyTrue(addNewUsers.text(), expectedValue("text.company.addNewUsers").toUpperCase())
		verifyTrue(editUsers.text(), expectedValue("text.company.editOrDisableUsers").toUpperCase())					
		verifyTrue(orderHistoryLink.text(), expectedValue("text.account.orderHistory").toUpperCase())
		verifyTrue(viewOrderHistory.text(), expectedValue("text.account.viewOrderHistory").toUpperCase())
		verifyTrue(accountBalanceLink.text(), expectedValue("text.account.accountBalance").toUpperCase())
		verifyTrue(myAccountBalanceLink.text(), expectedValue("text.account.viewAccountBalance").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:		
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)		
	}
}
