package lscob2b.test.contactus

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class ContactUsAfterLoginPageTest extends PropertProviderTest{
	
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
		assert masterTemplate.mainContainerLabel.text() == expectedValue("contactus.heading")
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.error.message")
		assert masterTemplate.introContainerLabel.text()==expectedValue("contactus.intro")
		assert masterTemplate.requiredMessageText.text()==expectedValue("login.required.message")
		assert titleLabel.text()==expectedValue("user.title").toUpperCase()
		assert firstNameLabel.text()==expectedValue("user.firstName").toUpperCase()
		assert lastNameLabel.text()==expectedValue("user.lastName").toUpperCase()
		assert emailLabel.text()==expectedValue("user.email").toUpperCase() //FAILED
		assert phoneLabel.text()==expectedValue("user.phone").toUpperCase()
		assert companyNameLabel.text()==expectedValue("user.companyName").toUpperCase()
		assert customerNumberLabelAfterLogin.text()==expectedValue("user.customerNumber").toUpperCase()
		assert countryLabelAfterLogin.text()==expectedValue("user.country").toUpperCase()
		assert commentsLabelAfterLogin.text()==expectedValue("user.comments").toUpperCase()
		//assert commentsErrorAfterLogin.text()==expectedValue("contactus.comments.invalid").toUpperCase()
		assert continueShoppingLink.text()- ~/&/==expectedValue("label.continue.shopping").toUpperCase()
		assert sendButton.text()==expectedValue("contactus.send").toUpperCase() //FAILED

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
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.msg.sent.successfully").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("contactus.success.message") //FAILED
		
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
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.serviceerror.message")
		
		where:
		user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
