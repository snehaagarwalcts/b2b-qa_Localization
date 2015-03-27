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
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.profile")
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.details.subtitle")
		assert titleLabel.text() == expectedValue("text.company.user.title")
		assert firstName.text() == expectedValue("text.company.user.name")
		assert lastName.text() == expectedValue("text.company.unit.name")
		assert changeYourPasswordLink.text()- ~/&/ == expectedValue("text.account.profile.changePassword")
		assert updatePersonalDetailsLink.text()- ~/&/ == expectedValue("text.account.profile.updatePersonalDetails")		
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
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
		assert masterTemplate.breadCrumbActive.text()==expectedValue("text.account.profile.updatePasswordForm")
		assert masterTemplate.mainContainerLabel.text()==expectedValue("text.account.profile.updatePasswordForm")
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.update.password.subtitle")
		assert masterTemplate.requiredMessageText.text()== expectedValue("required")
		assert currentPwdLabel.text() == expectedValue("profile.currentPassword")
		assert newPasswordLabel.text() == expectedValue("profile.newPassword")
		assert passwordHintText.text()==expectedValue("hint.update.password")
		assert confirmNewPasswordLabel.text() == expectedValue("profile.checkNewPassword")
		assert cancelButton.text()- ~/&/ == expectedValue("cancel")
		assert updatePasswordButton.text() == expectedValue("updatePwd.submit")
		
		when: "click on UpdatePasswordButton - All fields empty"
		clickUpdatePasswordButton()
		
		then: "verify error messages in UpdatePassword Page"
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
		assert masterTemplate.noteMessage.text() == expectedValue("account.confirmation.password.updated")
		
		where:
		user | compliantPassword
		UserHelper.getUpdatePasswordUser() | UserHelper.COMPLIANT_PASSWORD	
	}
	
	def "Verify translations in UpdatePersonalDetails Page"(){
		
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
			
			then: "verify translations in UpdatePersonalDetailsPage"			
			assert masterTemplate.breadCrumbActive.text()==expectedValue("text.account.profile")
			assert masterTemplate.mainContainerLabel.text()==expectedValue("text.account.profile")
			assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.update.subtitle")
			assert masterTemplate.requiredMessageText.text()== expectedValue("required")
			assert updateProfileLabel(0).text() == expectedValue("profile.title")
			assert updateProfileLabel(1).text() == expectedValue("profile.firstName")
			assert updateProfileLabel(2).text() == expectedValue("profile.lastName")
			assert cancelButton.text()- ~/&/ == expectedValue("cancel")
			assert saveButton.text() == expectedValue("text.account.profile.saveUpdates")
			
			when: "click on Save button"
			clickSaveButton()
			
			then: "verify translation of PROFILE UPDATE message"
			assert masterTemplate.noteMessage.text() == expectedValue("account.confirmation.profile.updated")
			
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
	def "load impex [/impex/Users.impex]"() {
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
	
}