package lscob2b.test.myaccount.admin

import de.hybris.geb.page.hac.console.ImpexImportPage
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
	
	def "load impex [/impex/UpdateUsers.impex]"() {
		setup:
		browser.go(browser.config.rawConfig.hacUrl)
		at de.hybris.geb.page.hac.LoginPage
	
		doLogin(browser.config.rawConfig.hacUsername, browser.config.rawConfig.hacPassword)
		at de.hybris.geb.page.hac.HomePage
			
		when: "at HAC home page"
		at de.hybris.geb.page.hac.HomePage
			
		and: "go to Console>ImpexImport page"
		browser.go(browser.config.rawConfig.hacUrl + "console/impex/import")
		
		and: "at ImpexImport page"
		waitFor { ImpexImportPage}
		at ImpexImportPage
		
		and: "load impex in HAC"
		setLegacyMode(true)
		importTextScript(getClass().getResource('/impex/Users.impex').text)
			
		then: "check import result"
		checkNotification()
			
		cleanup:
		menu.logout()
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
		verifyTrue(masterTemplate.breadCrumbActive.text(),expectedValue("text.account.profile.updatePasswordForm").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(),expectedValue("text.account.profile.updatePasswordForm").toUpperCase())
		verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.account.profile.update.password.subtitle"))
		verifyTrue(masterTemplate.requiredMessageText.text(), expectedValue("address.required"))
		verifyTrue(currentPwdLabel.text(), expectedValue("profile.currentPassword").toUpperCase())
		verifyTrue(newPasswordLabel.text(), expectedValue("profile.newPassword").toUpperCase())
		verifyTrue(passwordHintText.text(),expectedValue("hint.update.password"))
		verifyTrue(confirmNewPasswordLabel.text(), expectedValue("profile.checkNewPassword").toUpperCase())
		verifyTrue(cancelButton.text()- ~/&/, expectedValue("b2bcustomer.cancel").toUpperCase())
		verifyTrue(updatePasswordButton.text(), expectedValue("updatePwd.submit").toUpperCase())
		
		when: "click on UpdatePasswordButton - All fields empty"
		clickUpdatePasswordButton()
		
		then: "verify error messages in UpdatePassword Page"
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("form.global.error"))
		verifyTrue(currentPasswordError.text(), expectedValue("profile.currentPassword.invalid"))
		verifyTestFailedOrPassed()
		
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
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("account.confirmation.password.updated")) //FAILED       
		verifyTestFailedOrPassed()
		
		where:
		user | compliantPassword
		UserHelper.getUpdatePasswordUser() | UserHelper.COMPLIANT_PASSWORD
	}
	
}
