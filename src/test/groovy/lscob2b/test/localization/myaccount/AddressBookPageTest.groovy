package lscob2b.test.localization.myaccount

import lscob2b.data.PageHelper;
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.myaccount.AddressBookPage;
import lscob2b.pages.myaccount.MyAccountPage;
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.AddressBookPage;

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
		assert mainContainer.text()==expectedValue("text.account.addressBook")
		assert shippingHeader.text()==expectedValue("text.account.addressBook.manageYourAddresses")
		assert billingHeader.text()==expectedValue("text.account.addressBook.manageYourBillingAddresses")
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
