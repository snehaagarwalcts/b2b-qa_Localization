package lscob2b.test.login

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProvider

class ForgotPasswordTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
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
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("account.confirmation.forgotten.password.link.sent")) 
		
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
