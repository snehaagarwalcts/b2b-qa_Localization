package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.AddressBookPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.AddressBookPage

class AddressBookPageTest extends PropertProviderTest {	
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify AddressBook Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on AddressBook Link "
		clickOnAddressBookLink()
		
		when: "at AddressBookPage"
		at AddressBookPage
	
		then: "Verify translations at AddressBookPage"
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("text.account.addressBook").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.account.addressBook").toUpperCase())
		verifyTrue(shippingHeader.text(), expectedValue("text.account.addressBook.manageYourAddresses").toUpperCase())  //FAILED
		verifyTrue(billingHeader.text(), expectedValue("text.account.addressBook.manageYourBillingAddresses").toUpperCase())
		verifyTestFailedOrPassed()
			
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
