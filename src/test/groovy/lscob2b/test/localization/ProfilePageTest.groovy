package lscob2b.test.localization
import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage

class ProfilePageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify Profile Page Fields"(){		
		
		setup:
		to LoginPage
		at LoginPage
		login(user)		
		clickMyAccount()
		
		when: "User clicks on Profile Link "
		to MyAccountPage
		at MyAccountPage
		clickOnProfile()
		
		then: "Verify Fields at Profile Page"
		to ProfilePage
		at ProfilePage		
	
		assert profileDetails.text() == expectedValue("text.account.profile.details.subtitle")
		assert title.text() == expectedValue("profile.title")
		assert firstName.text() == expectedValue("profile.firstName")
		assert lastName.text() == expectedValue("profile.lastName")
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)

	}
}