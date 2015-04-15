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
	def "Verify login page translations"() {
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
	
	def "Verify translations for blocked B2B unit login"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		and: "We should still be at Login page"
		at LoginPage

		then: "verify translation of error message"
		waitFor { masterTemplate.alertMessage.displayed }
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrueContains(masterTemplate.alertMessage.text().replaceAll("’", ""), expectedValue("login.error.account.block.title").replaceAll("'", ""),"use contains()")
		verifyTestFailedOrPassed()
		
		where:
		user = UserHelper.getBlcokedUserWithCode01()
	}
	
}

