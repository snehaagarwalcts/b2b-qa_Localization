package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.LoginPage

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
	
		then:"check content translations at ContactUsPage"
		assert contact.text() == expectedValue("contactus.heading")
		assert introContainer.text()==expectedValue("contactus.intro")
		assert required.text()==expectedValue("contactus.required")
		assert titleLabel.text()==expectedValue("contactus.user.title")
		assert firstNameLabel.text()==expectedValue("contactus.user.firstName")
		assert lastNameLabel.text()==expectedValue("contactus.user.lastName")
		assert emailLabel.text()==expectedValue("contactus.user.email")
		assert phoneLabel.text()==expectedValue("contactus.user.phone")
		assert companyNameLabel.text()==expectedValue("contactus.user.companyName")
		assert customerNumberLabel.text()==expectedValue("contactus.user.customerNumber")
		assert countryLabel.text()==expectedValue("contactus.user.country")
		assert commentsLabel.text()==expectedValue("contactus.user.comments")
		assert sendButton.text()==expectedValue("contactus.send")
	}	
	
	def "Verify error messages- All fields Empty "() {
		setup:
		to LoginPage
		at LoginPage
		clickContactUS()
		
		when:"at Contact Us page"
		at ContactUsPage
		
		then: "click on SEND button"
		clickSendButton()
	
		and:"check translation of error messages in ContactUsPage"
		assert alertMessage.text() == expectedValue("contactus.error.message")
		assert titleError.text()==expectedValue("contactus.title.invalid")
		assert firstNameError.text()==expectedValue("contactus.firstname.invalid")
		assert lastNameError.text()==expectedValue("contactus.lastname.invalid")
		assert emailError.text()==expectedValue("contactus.email.invalid")
		assert phoneError.text()==expectedValue("contactus.phone.invalid")
		assert companyNameError.text()==expectedValue("contactus.company.invalid")
		assert customerNumberError.text()==expectedValue("contactus.customernumber.invalid")
		assert countryError.text()==expectedValue("contactus.country.invalid")
		assert commentsError.text()==expectedValue("contactus.comments.invalid")
		
	}
	
	def "Verify error messages- Invalid Email Id"() {
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
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(15)
		fillOutComments('test')
		clickSendButton()

		and:"check translation of error message"
		assert alertMessage.text() == expectedValue("contactus.serviceerror.message")	||	
		       contact.text()==expectedValue("system.error") && alertMessage.text() == expectedValue("system.error.try.again")
	}
	
	def "Verify SUCCESSFUL SENT message"() {
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
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(15)
		fillOutComments('test')
		clickSendButton()
	
		and:"check translation of message"
		assert noteMessage.text() == expectedValue("contactus.success.message") ||
	      contact.text()==expectedValue("system.error") && alertMessage.text() == expectedValue("system.error.try.again")
	}
}
