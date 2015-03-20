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
import lscob2b.pages.myaccount.admin.UpdatePersonalDetailsPage
import spock.lang.IgnoreRest


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
	
	@IgnoreRest
		def "Verify error messages in UpdatePersonalDetails Page Fields"(){
		
			setup:
			to LoginPage
			at LoginPage
			login(user)
			at HomePage
			masterTemplate.clickMyAccount()
			
			when: "at MyAccountPage"
			at MyAccountPage
			
			then: "click on Profile Link"
			clickOnProfile()
			
			when: "at ProfilePage"			
			at ProfilePage
			
			then: "click on Update Personal Details Link"
			clickOnUpdatePersonalDetailsLink()
			
			when: "at UpdatePersonalDetailsPage"
			at UpdatePersonalDetailsPage
			clickSaveButton()
			
			then: "verify translations in UpdatePersonalDetailsPage"			
			
			assert alertMessage.text()	==expectedValue("text.please.note").toUpperCase()
			assert errorMessageText.text() == expectedValue("form.global.error")
			assert profileDetails.text() == expectedValue("text.account.profile.update.password.subtitle")
			assert requiredMessageText.text()== expectedValue("required")
			assert cancelButton.text()- ~/&/ == expectedValue("cancel")
			
			
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
			
			then: "verify translations in Update Password Page"
			assert updatePasswordText.text()==expectedValue("text.account.profile.updatePasswordForm")
			assert profileDetails.text() == expectedValue("text.account.profile.update.password.subtitle")
			assert currentPwdLabel.text() == expectedValue("profile.currentPassword")
			assert newPasswordLabel.text() == expectedValue("profile.newPassword")
			assert passwordHintText.text() == expectedValue("hint.update.password")
			assert confirmNewPasswordLabel.text() == expectedValue("profile.checkNewPassword")
			assert updatePasswordButton.text() == expectedValue("updatePwd.submit")
			
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
		}		
		
		def "Verify error messages in UpdatePassword Page Fields with All fields Empty"(){
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
	
			when:"at UpdatePasswordPage click on UpdatePasswordButton"
			at UpdatePasswordPage
			clickSaveButton()
			
			then: "verify error messages at UpdatePassword Page"		
			assert updatePasswordText.text()==expectedValue("text.account.profile.updatePasswordForm")
			assert alertMessage.text()	==expectedValue("text.please.note").toUpperCase()
			assert errorMessageText.text() == expectedValue("form.global.error")
			assert profileDetails.text() == expectedValue("text.account.profile.update.password.subtitle")
			assert requiredMessageText.text()== expectedValue("required")
			assert currentPwdLabel.text() == expectedValue("profile.currentPassword")
			assert currentPasswordError.text() == expectedValue("profile.currentPassword.invalid")
			assert newPasswordLabel.text() == expectedValue("profile.newPassword")
			assert passwordHintText.text()==expectedValue("hint.update.password")	
			assert confirmNewPasswordLabel.text() == expectedValue("profile.checkNewPassword")			
			assert cancelButton.text()- ~/&/ == expectedValue("cancel")					
			assert updatePasswordButton.text() == expectedValue("updatePwd.submit")			
			
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
		}
}