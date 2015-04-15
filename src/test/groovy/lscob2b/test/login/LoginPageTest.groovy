package lscob2b.test.login

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProvider

class LoginPageTest extends PropertProvider{
	
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
		verifyTrue(pageheading,expectedValue("header.welcome").toUpperCase())
		verifyTrue(loginTitle.text(), expectedValue("account.login.title").toUpperCase())
		verifyTrue(username.text(),expectedValue("account.login.username").toUpperCase())
		verifyTrue(password.text(),expectedValue("account.login.password").toUpperCase())
		verifyTrue(submitButton.text(),expectedValue("account.login.button.text").toUpperCase())
		verifyTrue(forgottenYourPasswordButton.text(),expectedValue("login.link.forgottenPwd").toUpperCase())
		verifyTrue(selectlanguageLink.text(),expectedValue("text.language").toUpperCase())
		verifyTrue(contactUsMessage.text().replaceAll(contactUS.text(),""),expectedValue("contactus.page.content").toUpperCase()) //FAILED
		verifyTrue(contactUS.text(),expectedValue("text.contactus").toUpperCase())
		
		when:"do an invalid login"
		login(user)
		at LoginPage
		
		and:"a message is displayed"
		waitFor { masterTemplate.alertMessage.displayed }
		
		then:"Verify error message translation"		
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("login.error.account.not.found.title")) //FAILED
		verifyTestFailedOrPassed()
				
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
		verifyTrue(forgottenPwdTitle.text(), expectedValue("forgottenPwd.title").toUpperCase())
		//verifyTrue(forgottenPwdRequired.text(), expectedValue("login.required.message").toUpperCase())
		verifyTrue(forgottenPwdDescription.text(), expectedValue("forgottenPwd.description").toUpperCase())
		verifyTrue(forgottenPwdEmail.text(), expectedValue("forgottenPwd.email").toUpperCase())
		verifyTrue(forgottenPwdSubmit.text(), expectedValue("forgottenPwd.submit").toUpperCase())
		
		and:"Enter valid forgotten password email Id"	
		sendForgottenPasswordEmail(validUser.email)
		at LoginPage
		waitFor { masterTemplate.noteMessage.displayed }
		
		and: "Verify Message Translation for 'password link sent' message"
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("account.confirmation.forgotten.password.link.sent")) //FAILED
		
		when:"Enter invalid forgotten password email Id"
		openForgottenPasswordDialog()
		waitFor { forgottenPasswordDialog.displayed }
		sendForgottenPasswordEmail(invalidUser.email)
		at LoginPage
		waitFor { masterTemplate.alertMessage.displayed }
		
		then: "A Message Appears.Verify Message Translation"
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(),expectedValue("account.confirmation.forgotten.password.link.error")) //FAILED
		verifyTestFailedOrPassed()
		
		where:
		validUser | invalidUser
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | UserHelper.getInvalidUser()		
	}
}

