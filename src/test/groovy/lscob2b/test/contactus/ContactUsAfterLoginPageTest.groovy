package lscob2b.test.contactus

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProvider

class ContactUsAfterLoginPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	/**
	 * Check Translations - Contact Us After Login
	 */
	def "Verify contact us page fields after Login"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.doContactUs()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then: "click on SEND button"
		clickSendButton()
	
		and:"Verify translations at ContactUsPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("contactus.heading").toUpperCase())
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("contactus.error.message"))
		verifyTrue(masterTemplate.introContainerLabel.text(),expectedValue("contactus.intro"))
		verifyTrue(masterTemplate.requiredMessageText.text(),expectedValue("form.required").replaceAll(" <span class=mandatory></span>", ""))
		verifyTrue(titleLabel.text(),expectedValue("user.title").toUpperCase())
		verifyTrue(firstNameLabel.text(),expectedValue("user.firstName").toUpperCase())
		verifyTrue(lastNameLabel.text(),expectedValue("user.lastName").toUpperCase())
		verifyTrue(emailLabel.text(),expectedValue("user.email").toUpperCase()) //FAILED
		verifyTrue(phoneLabel.text(),expectedValue("user.phone").toUpperCase())
		verifyTrue(companyNameLabel.text(),expectedValue("user.companyName").toUpperCase())
		verifyTrue(customerNumberLabelAfterLogin.text(),expectedValue("user.customerNumber").toUpperCase())
		verifyTrue(countryLabelAfterLogin.text(),expectedValue("user.country").toUpperCase())
		verifyTrue(commentsLabelAfterLogin.text(),expectedValue("user.comments").toUpperCase())
		//verifyTrue(commentsErrorAfterLogin.text(),expectedValue("contactus.comments.invalid").toUpperCase())
		verifyTrue(continueShoppingLink.text()- ~/&/,expectedValue("label.continue.shopping").toUpperCase())
		verifyTrue(sendButton.text(),expectedValue("contactus.send").toUpperCase()) //FAILED
		verifyTestFailedOrPassed()

		where:
		user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
		
	}
	
	def "Verify SUCCESSFUL SENT message after Login - ContactUsPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.doContactUs()
		
		when:"at Contact Us page"
		at ContactUsPage
			
		then: "fill out the form and request access"
		fillOutComments('test')
		clickSendButton()
	
		and: "Verify translation of message"
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.msg.sent.successfully").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("text.msg.sent.successfully").toUpperCase()) //FAILED
		verifyTestFailedOrPassed()
		
		where:
		user = UserHelper.getValidUser()
	}
		
	def "Verify translation of error message after Login - ContactUsPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.doContactUs()
		
		when:"at Contact Us page"
		at ContactUsPage
			
		then: "fill out the form and request access"
		fillOutComments('test')
		clickSendButton()
	
		and: "Verify translation of message"
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("contactus.serviceerror.message"))
		verifyTestFailedOrPassed()
		
		where:
		user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
