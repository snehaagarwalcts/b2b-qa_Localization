package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.UpdatePasswordPage
import lscob2b.test.data.PropertProviderTest

class UpdatePasswordPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations in UpdatePassword Page"(){
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
		
		then: "verify translations in UpdatePassword Page"
		assert masterTemplate.breadCrumbActive.text()==expectedValue("text.account.profile.updatePasswordForm").toUpperCase()
		assert masterTemplate.mainContainerLabel.text()==expectedValue("text.account.profile.updatePasswordForm").toUpperCase()
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.update.password.subtitle")
		assert masterTemplate.requiredMessageText.text()== expectedValue("address.required")
		assert currentPwdLabel.text() == expectedValue("profile.currentPassword").toUpperCase()
		assert newPasswordLabel.text() == expectedValue("profile.newPassword").toUpperCase()
		assert passwordHintText.text()==expectedValue("hint.update.password")
		assert confirmNewPasswordLabel.text() == expectedValue("profile.checkNewPassword").toUpperCase()
		assert cancelButton.text()- ~/&/ == expectedValue("cancelButton.displayName").toUpperCase()
		assert updatePasswordButton.text() == expectedValue("updatePwd.submit").toUpperCase()
		
		when: "click on UpdatePasswordButton - All fields empty"
		clickUpdatePasswordButton()
		
		then: "verify error messages in UpdatePassword Page"
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("form.global.error")
		assert currentPasswordError.text() == expectedValue("profile.currentPassword.invalid")
		
		where:
		user | _
		UserHelper.getUpdatePasswordUser() | _
	}

	def "Verify SUCCESSFUL PASSWORD CHANGE message in UpdatePassword Page"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()

		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on change password link"
		changeYourPassword.click()
		
		when: "at update Password"
		at UpdatePasswordPage
		
		and: "Try to update password "
		doUpdatePassword(user.password,compliantPassword)
		
		then: "verify translation of SUCCESSFUL PASSWORD CHANGE message"
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("account.confirmation.password.updated") //FAILED       
		
		where:
		user | compliantPassword
		UserHelper.getUpdatePasswordUser() | UserHelper.COMPLIANT_PASSWORD
	}
	
}
