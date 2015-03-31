package lscob2b.test.login

import spock.lang.IgnoreRest;
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
		assert pageheading ==expectedValue("header.welcome")
		assert loginTitle.text() == expectedValue("account.login.title")
		assert username.text()==expectedValue("account.login.username")
		assert password.text()==expectedValue("account.login.password")
		assert submitButton.text()==expectedValue("account.login.button.text")	
		assert forgottenYourPasswordButton.text()==expectedValue("login.link.forgottenPwd")
		assert selectlanguageLink.text()==expectedValue("text.language")
		assert contactUsMessage.text()==expectedValue("contactus.page.content")
		
		when:"do an invalid login"
		login(user)
		at LoginPage
		
		and:"a message is displayed"
		waitFor { masterTemplate.alertMessage.displayed }
		
		then:"Verify error message translation"
		assert masterTemplate.alertMessage.text()==expectedValue("login.error.account.not.found.title") //FAIL
				
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
		assert forgottenPwdRequired.text() == expectedValue("login.required.message")
		assert forgottenPwdDescription.text() == expectedValue("forgottenPwd.description")
		assert forgottenPwdEmail.text() == expectedValue("forgottenPwd.email")
		assert forgottenPwdSubmit.text() == expectedValue("forgottenPwd.submit")
		
		and:"Enter valid forgotten password email Id"	
		sendForgottenPasswordEmail(validUser.email)
		at LoginPage
		waitFor { masterTemplate.noteMessage.displayed }
		
		and: "Verify Message Translation for 'password link sent' message"
		assert masterTemplate.noteMessage.text()==expectedValue("account.confirmation.forgotten.password.link.sent") //FAIL
		
		when:"Enter invalid forgotten password email Id"
		openForgottenPasswordDialog()
		waitFor { forgottenPasswordDialog.displayed }
		sendForgottenPasswordEmail(invalidUser.email)
		at LoginPage
		waitFor { masterTemplate.alertMessage.displayed }
		
		then: "A Message Appears.Verify Message Translation"
		assert masterTemplate.alertMessage.text()==expectedValue("account.confirmation.forgotten.password.link.error") //FAIL
		
		where:
		validUser | invalidUser
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | UserHelper.getInvalidUser()		
	}
}

