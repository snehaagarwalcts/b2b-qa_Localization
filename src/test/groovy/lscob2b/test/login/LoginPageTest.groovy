package lscob2b.test.login

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
		assert pageheading ==expectedValue("header.welcome").toUpperCase()
		assert loginTitle.text() == expectedValue("account.login.title").toUpperCase()
		assert username.text()==expectedValue("account.login.username").toUpperCase()
		assert password.text()==expectedValue("account.login.password").toUpperCase()
		assert submitButton.text()==expectedValue("account.login.button.text").toUpperCase()
		assert forgottenYourPasswordButton.text()==expectedValue("login.link.forgottenPwd").toUpperCase()
		assert selectlanguageLink.text()==expectedValue("text.language").toUpperCase()
		assert contactUsMessage.text().replaceAll(contactUS.text(),"") ==expectedValue("contactus.page.content").toUpperCase() //FAILED
		assert contactUS.text()==expectedValue("text.contactus").toUpperCase()
		
		when:"do an invalid login"
		login(user)
		at LoginPage
		
		and:"a message is displayed"
		waitFor { masterTemplate.alertMessage.displayed }
		
		then:"Verify error message translation"		
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("login.error.account.not.found.title") //FAILED
				
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
		assert forgottenPwdTitle.text() == expectedValue("forgottenPwd.title").toUpperCase()
		//assert forgottenPwdRequired.text() == expectedValue("login.required.message").toUpperCase()
		assert forgottenPwdDescription.text() == expectedValue("forgottenPwd.description").toUpperCase()
		assert forgottenPwdEmail.text() == expectedValue("forgottenPwd.email").toUpperCase()
		assert forgottenPwdSubmit.text() == expectedValue("forgottenPwd.submit").toUpperCase()
		
		and:"Enter valid forgotten password email Id"	
		sendForgottenPasswordEmail(validUser.email)
		at LoginPage
		waitFor { masterTemplate.noteMessage.displayed }
		
		and: "Verify Message Translation for 'password link sent' message"
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("account.confirmation.forgotten.password.link.sent") //FAILED
		
		when:"Enter invalid forgotten password email Id"
		openForgottenPasswordDialog()
		waitFor { forgottenPasswordDialog.displayed }
		sendForgottenPasswordEmail(invalidUser.email)
		at LoginPage
		waitFor { masterTemplate.alertMessage.displayed }
		
		then: "A Message Appears.Verify Message Translation"
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text()==expectedValue("account.confirmation.forgotten.password.link.error") //FAILED
		
		where:
		validUser | invalidUser
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | UserHelper.getInvalidUser()		
	}
}

