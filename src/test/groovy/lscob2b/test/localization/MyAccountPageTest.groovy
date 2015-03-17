package lscob2b.test.localization
import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
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
		
		when: "User is clicks on MyAccountPage "
		assert myAccount.text() == expectedValue("header.link.account")
		clickMyAccount()
				
		then: "Verify fields in My Account Page"		
		to MyAccountPage
		at MyAccountPage			
		assert profileLink.text()  == expectedValue("text.account.profile")
		assert updatePersonalDetails.text() == expectedValue("text.account.profile.updatePersonalDetails")
		assert changePassWord.text()  == expectedValue("text.account.profile.changePassword")
		assert addressBookLink.text() == expectedValue("text.account.addressBook")
		assert viewYourDeliveryAddress.text() == expectedValue("text.account.addressBook.manageDeliveryAddresses")
		assert orderHistoryLink.text() == expectedValue("text.account.orderHistory")
		assert viewOrderHistory.text() == expectedValue("text.account.viewOrderHistory")												
		
		where:		
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)
		
	}

}
