package lscob2b.test.localization.myaccount

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.UpdatePasswordPage;
import lscob2b.pages.myaccount.admin.UpdatePersonalDetailsPage;

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
	
		then: "Verify Fields at Profile Page"
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
	
		def "Verify UpdatePersonalDetails Page Fields"(){
		
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
			
			then: "click on Update Personal Details Link"
			clickOnUpdatePersonalDetailsLink()
			
			when: "at UpdatePersonalDetailsPage"
			at UpdatePersonalDetailsPage
			
			then: "verify fields in UpdatePersonalDetailsPage"			
			//assert profileDetails.text() == expectedValue("text.account.profile.update.subtitle")
						
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)		

	}
		
		def "Verify UpdatePassword Page Fields"(){
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
			
			when:"at ProfilePage"
			at ProfilePage
			
			then: "click on Update Password Link"
			clickOnChangeYourPasswordLink()
	
			when:"at UpdatePasswordPage"
			at UpdatePasswordPage
			
			then: "verify fields in Update Password Page"
			assert updatePasswordTxt.text()==expectedValue("text.account.profile.updatePasswordForm")
			assert profileDetails.text() == expectedValue("text.account.profile.update.password.subtitle")
			assert currentPwdTxt.text() == expectedValue("profile.currentPassword")
			assert newPasswordTxt.text() == expectedValue("profile.newPassword")
			assert passwordHintText.text() == expectedValue("hint.update.password")
			assert confirmNewPasswordTxt.text() == expectedValue("profile.checkNewPassword")
			assert updatePasswordButton.text() == expectedValue("updatePwd.submit")
			
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
		}
}