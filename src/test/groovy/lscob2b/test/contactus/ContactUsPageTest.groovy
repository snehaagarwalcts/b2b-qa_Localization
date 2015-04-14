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
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("contactus.heading"))
		verifyTrue(masterTemplate.introContainerLabel.text(),expectedValue("contactus.intro")) //FAILED
		verifyTrue(masterTemplate.requiredMessageText.text(),expectedValue("login.required.message"))
		verifyTrue(titleLabel.text(),expectedValue("user.title").toUpperCase())
		verifyTrue(firstNameLabel.text(),expectedValue("user.firstName").toUpperCase())
		verifyTrue(lastNameLabel.text(),expectedValue("user.lastName").toUpperCase())
		verifyTrue(emailLabel.text(),expectedValue("user.email").toUpperCase()) //FAILED
		verifyTrue(phoneLabel.text(),expectedValue("user.phone").toUpperCase())
		verifyTrue(companyNameLabel.text(),expectedValue("user.companyName").toUpperCase())
		verifyTrue(countryLabel.text(),expectedValue("user.country").toUpperCase())
		verifyTrue(commentsLabel.text(),expectedValue("user.comments").toUpperCase())
		verifyTrue(sendButton.text(),expectedValue("contactus.send").toUpperCase()) //FAILED
		verifyTestFailedOrPassed()
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
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("contactus.error.message"))
		verifyTrue(titleError.text(),expectedValue("contactus.title.invalid"))
		verifyTrue(firstNameError.text(),expectedValue("contactus.firstname.invalid"))
		verifyTrue(lastNameError.text(),expectedValue("contactus.lastname.invalid"))
		verifyTrue(emailError.text(),expectedValue("contactus.email.invalid"))
		verifyTrue(phoneError.text(),expectedValue("contactus.phone.invalid"))
		verifyTrue(companyNameError.text(),expectedValue("contactus.company.invalid"))
		verifyTrue(countryError.text(),expectedValue("contactus.country.invalid"))
		//verifyTrue(commentsError.text(),expectedValue("contactus.comments.invalid"))
		verifyTestFailedOrPassed()
		
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
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.msg.sent.successfully").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("contactus.success.message")) //FAILED   
		verifyTestFailedOrPassed()
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
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("contactus.serviceerror.message"))
		verifyTestFailedOrPassed()
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
	    verifyTrue(masterTemplate.mainContainerLabel.text(),expectedValue("system.error").toUpperCase()) //FAILED
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("system.error.title").toUpperCase()) //FAILED
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("system.error.try.again")) //FAILED
		verifyTestFailedOrPassed()
	}
}
