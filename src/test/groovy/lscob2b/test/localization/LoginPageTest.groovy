package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class LoginPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}	
	
	/**
	 * Check Translations - Login
	 */
	def "Verify login page fields"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage
	
		then:"Verify translations at LoginPage"
		assert loginTitle.text() == expectedValue("loginPage.title")
		assert username.text()==expectedValue("loginPage.username")
		assert password.text()==expectedValue("loginPage.password")
		assert submitButton.text()==expectedValue("loginPage.button.text")	
		assert forgottenYourPasswordButton.text()==expectedValue("loginPage.link.forgottenPwd")
		assert selectlanguageLink.text()==expectedValue("loginPage.selectLanguage")
		assert contactUsMessage.text()==expectedValue("loginPage.message.contactUs")
		
		when:"do an invalid login"
		login(user)
		at LoginPage
		
		and:"a message is displayed"
		waitFor { errorMessage.displayed }
		
		then:"Verify error message translation"
		assert errorMessage.text()==expectedValue("loginPage.error.accountNotFound") //FAIL
				
		where:
		user=UserHelper.getInvalidUser()
	}
	
	/**
	 * Check Translations - Forgot Password
	 */
	def "Verify Forgot Password messages"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage
		
		then: "verify translations in forgot password pop up"
		openForgottenPasswordDialog()
		waitFor { forgottenPasswordDialog.displayed }	
		assert forgottenPwdTitle.text() == expectedValue("forgottenPwd.title")
		assert forgottenPwdRequired.text() == expectedValue("forgottenPwd.required")
		assert forgottenPwdDescription.text() == expectedValue("forgottenPwd.description")
		assert forgottenPwdEmail.text() == expectedValue("forgottenPwd.email")
		assert forgottenPwdSubmit.text() == expectedValue("forgottenPwd.submit")
		
		and:"Enter valid forgotten password email Id"	
		sendForgottenPasswordEmail(validUser.email)
		at LoginPage
		waitFor { noteMessage.displayed }
		
		and: "Verify Message Translation for 'password link sent' message"
		assert noteMessage.text()==expectedValue("loginPage.forgottenPwd.link.sent") //FAIL
		
		when:"Enter invalid forgotten password email Id"
		openForgottenPasswordDialog()
		waitFor { forgottenPasswordDialog.displayed }
		sendForgottenPasswordEmail(invalidUser.email)
		at LoginPage
		waitFor { errorMessage.displayed }
		
		then: "A Message Appears.Verify Message Translation"
		assert errorMessage.text()==expectedValue("loginPage.forgottenPwd.error") //FAIL
		
		where:
		validUser | invalidUser
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | UserHelper.getInvalidUser()		
	}
}

