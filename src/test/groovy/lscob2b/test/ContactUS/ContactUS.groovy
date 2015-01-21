package lscob2b.test.ContactUS

import geb.spock.GebReportingSpec

import lscob2b.pages.LoginPage
import lscob2b.pages.ContactUsPage
import spock.lang.Stepwise

class ContactUS extends GebReportingSpec {

	def setup() {
		to LoginPage
		clickContactUS()
	}
	
	def "Check the content of contact us page"(){
		
		when: "At contact us page"
		at ContactUsPage
		
		then: "required content should be present"
		checkRequiredContent()
	}
	
	def "New customer requesting access"(){
		
		when: "At contact us page"
		at ContactUsPage
		
		then: "fill out the form and request access"
		def title = selectTitleOption(2)
		fillOutFirstName('abc')
		fillOutlastName('def')
		fillOutEmail('cust@unit-2')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('Levis')
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(18)
		clickSendButton()
		Thread.sleep(2000)
		//TODO check if it goes to confirmation page when its implemented
	}
}
