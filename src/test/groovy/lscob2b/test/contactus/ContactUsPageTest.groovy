package lscob2b.test.contactus

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class ContactUsPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}	
	
	/**
	 * Check Translations - Contact Us
	 */
	def "Verify contact us page fields"() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
	
		then:"Verify translations at ContactUsPage"
		assert masterTemplate.mainContainerLabel.text() == expectedValue("contactus.heading")
		assert masterTemplate.introContainerLabel.text()==expectedValue("contactus.intro") //FAILED
		assert masterTemplate.requiredMessageText.text()==expectedValue("login.required.message")
		assert titleLabel.text()==expectedValue("user.title").toUpperCase()
		assert firstNameLabel.text()==expectedValue("user.firstName").toUpperCase()
		assert lastNameLabel.text()==expectedValue("user.lastName").toUpperCase()
		assert emailLabel.text()==expectedValue("user.email").toUpperCase() //FAILED
		assert phoneLabel.text()==expectedValue("user.phone").toUpperCase()
		assert companyNameLabel.text()==expectedValue("user.companyName").toUpperCase()
		assert countryLabel.text()==expectedValue("user.country").toUpperCase()
		assert commentsLabel.text()==expectedValue("user.comments").toUpperCase()
		assert sendButton.text()==expectedValue("contactus.send").toUpperCase() //FAILED
	}	
	
	def "Verify translation of error message with All fields Empty - ContactUsPage "() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then: "click on SEND button"
		clickSendButton()
	
		and:"Verify of error messages in ContactUsPage"
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.error.message")
		assert titleError.text()==expectedValue("contactus.title.invalid")
		assert firstNameError.text()==expectedValue("contactus.firstname.invalid")
		assert lastNameError.text()==expectedValue("contactus.lastname.invalid")
		assert emailError.text()==expectedValue("contactus.email.invalid")
		assert phoneError.text()==expectedValue("contactus.phone.invalid")
		assert companyNameError.text()==expectedValue("contactus.company.invalid")
		assert countryError.text()==expectedValue("contactus.country.invalid")
		//assert commentsError.text()==expectedValue("contactus.comments.invalid")
		
	}
	
	def "Verify SUCCESSFUL SENT message - ContactUsPage"() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then: "fill out the form and request access"
		def title = selectTitleOption(2)
		fillOutFirstName('Customer')
		fillOutlastName('Unit')
		fillOutEmail('sagarwal1@levi.com')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('Levis')
		def country = selectCountryOption(234)
		fillOutComments('test')
		clickSendButton()
	
		and:"Verify translation of message"
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.msg.sent.successfully").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("contactus.success.message") //FAILED   
	}
	
	def "Verify translation of error message with Invalid Email Id - ContactUsPage"() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then:"fill out the form and request access"
		def title = selectTitleOption(2)
		fillOutFirstName('test')
		fillOutlastName('test')
		fillOutEmail('test@test.com')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('test')
		def country = selectCountryOption(234)
		fillOutComments('test')
		clickSendButton()

		and:"Verify translation of error message"
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.serviceerror.message")
	}
	
	def "Verify translation of SYSTEM ERROR message - ContactUsPage"() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then:"fill out the form and request access"
		def title = selectTitleOption(2)
		fillOutFirstName('test')
		fillOutlastName('test')
		fillOutEmail('sagarwal1@levi.com')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('test')
		def country = selectCountryOption(18)
		fillOutComments('test')
		clickSendButton()

		and:"Verify translation of error message"
	    assert masterTemplate.mainContainerLabel.text()==expectedValue("system.error").toUpperCase() //FAILED
		assert masterTemplate.alertMessageHeader.text() == expectedValue("system.error.title").toUpperCase() //FAILED
		assert masterTemplate.alertMessage.text() == expectedValue("system.error.try.again") //FAILED
	}
}
