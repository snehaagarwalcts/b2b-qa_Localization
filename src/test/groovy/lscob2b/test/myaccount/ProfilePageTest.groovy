package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage

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
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("text.account.profile").toUpperCase())	
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.account.profile").toUpperCase())
		verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.account.profile.details.subtitle"))
		verifyTrue(titleLabel.text()- ~/:/, expectedValue("text.company.user.title").toUpperCase())
		verifyTrue(firstName.text(), expectedValue("text.company.user.name").toUpperCase())
		verifyTrue(lastName.text(), expectedValue("text.company.unit.name").toUpperCase())
		verifyTrue(changeYourPasswordLink.text()- ~/&/, expectedValue("text.account.profile.changePassword").toUpperCase())
		verifyTrue(updatePersonalDetailsLink.text()- ~/&/, expectedValue("text.account.profile.updatePersonalDetails").toUpperCase())		
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}		
}