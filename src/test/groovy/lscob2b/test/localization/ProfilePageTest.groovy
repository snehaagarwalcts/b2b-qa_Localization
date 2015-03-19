package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
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
		at HomePage	
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		and: "click on Profile Link "
		clickOnProfile()
		
		then: "at ProfilePage"
		at ProfilePage		
	
		and: "Verify Fields at Profile Page"
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")	
		assert profileTxt.text() == expectedValue("text.account.profile")
		assert profileDetails.text() == expectedValue("text.account.profile.details.subtitle")
		assert titleLabel.text() == expectedValue("profile.title")
		assert firstName.text() == expectedValue("profile.firstName")
		assert lastName.text() == expectedValue("profile.lastName")
		assert changeYourPasswordLink.text()- ~/&/ == expectedValue("text.account.profile.changePassword")
		assert updatePersonalDetailsLink.text()- ~/&/ == expectedValue("text.account.profile.updatePersonalDetails")		
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
		
	
		def "Verify Update Profile Page"(){
		
			setup:
			to LoginPage
			at LoginPage
			login(user)
			at HomePage
			masterTemplate.clickMyAccount()
			
			when: "at MyAccountPage"
			at MyAccountPage
			
			and: "click on Profile Link "
			clickOnProfile()
			
			then: "at ProfilePage click on Update Personal Details Link"			
			at ProfilePage
			clickOnUpdatePersonalDetailsLink()
			
			then: "verify fields in Update Profile Page"			
			assert profileDetails.text() == expectedValue("text.account.profile.update.subtitle")
			
			then: "click "				
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
		

	}
}