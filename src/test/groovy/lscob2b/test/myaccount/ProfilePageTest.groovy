package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.UpdatePasswordPage;
import lscob2b.pages.myaccount.admin.UpdatePersonalDetailsPage

class ProfilePageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify ProfilePage Fields"(){			
		setup:
		to LoginPage
		at LoginPage
		login(user)	
		at HomePage	
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on Profile Link "
		clickOnProfile()
		
		when: "at ProfilePage"
		at ProfilePage		
	
		then: "Verify translations at Profile Page"
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile").toUpperCase()	
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.profile").toUpperCase()
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.details.subtitle")
		assert titleLabel.text()- ~/:/ == expectedValue("text.company.user.title").toUpperCase()
		assert firstName.text() == expectedValue("text.company.user.name").toUpperCase()
		assert lastName.text() == expectedValue("text.company.unit.name").toUpperCase()
		assert changeYourPasswordLink.text()- ~/&/ == expectedValue("text.account.profile.changePassword").toUpperCase()
		assert updatePersonalDetailsLink.text()- ~/&/ == expectedValue("text.account.profile.updatePersonalDetails").toUpperCase()		
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}		
}