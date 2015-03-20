package lscob2b.test.localization

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
		assert masterTemplate.introContainerLabel.text()==expectedValue("contactus.intro")
		assert masterTemplate.requiredMessageText.text()==expectedValue("required")
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
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.error.message")
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
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(234)
		fillOutComments('test')
		clickSendButton()
	
		and:"Verify translation of message"
		assert masterTemplate.noteMessage.text() == expectedValue("contactus.success.message")
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
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(234)
		fillOutComments('test')
		clickSendButton()

		and:"Verify translation of error message"
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
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(18)
		fillOutComments('test')
		clickSendButton()

		and:"Verify translation of error message"
	    assert masterTemplate.mainContainerLabel.text()==expectedValue("system.error") && masterTemplate.alertMessage.text() == expectedValue("system.error.try.again")
	}
	
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
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.error.message")
		assert masterTemplate.introContainerLabel.text()==expectedValue("contactus.intro")
		assert masterTemplate.requiredMessageText.text()==expectedValue("required")
		assert titleLabel.text()==expectedValue("contactus.user.title")
		assert firstNameLabel.text()==expectedValue("contactus.user.firstName")
		assert lastNameLabel.text()==expectedValue("contactus.user.lastName")
		assert emailLabel.text()==expectedValue("contactus.user.email")
		assert phoneLabel.text()==expectedValue("contactus.user.phone")
		assert companyNameLabel.text()==expectedValue("contactus.user.companyName")
		assert customerNumberLabel.text()==expectedValue("contactus.user.customerNumber")
		assert countryLabel.text()==expectedValue("contactus.user.country")
		assert commentsLabel.text()==expectedValue("contactus.user.comments")
		assert commentsErrorAfterLogin.text()==expectedValue("contactus.comments.invalid")
		assert continueShoppingLink.text()- ~/&/==expectedValue("label.continue.shopping")
		assert sendButton.text()==expectedValue("contactus.send")

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
		assert masterTemplate.noteMessage.text() == expectedValue("contactus.success.message")
		
		where:
		user = UserHelper.getUpdatePasswordUser()
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
		assert masterTemplate.alertMessage.text() == expectedValue("contactus.serviceerror.message")
		
		where:
		user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
