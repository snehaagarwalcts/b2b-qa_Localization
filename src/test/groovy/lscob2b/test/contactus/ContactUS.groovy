package lscob2b.test.contactus

import geb.spock.GebReportingSpec
import lscob2b.pages.ContactUsPage
import lscob2b.pages.LoginPage

class ContactUS extends GebReportingSpec {

	def setup() {
		to LoginPage
		clickContactUS()
	}
	
	/**
	 * TC BB-610 Automated test: BB-412 New customer requesting access - content test
	 * @return
	 */
	def "Check the content of contact us page"(){
		
		when: "At contact us page"
		at ContactUsPage
		
		then: "required content should be present"
		checkRequiredContent()
	}
	
	/**
	 * TC BB-611 Automated Test: BB-412 New customer requesting access
	 */
	def "New customer requesting access"(){
		
		when: "At contact us page"
		at ContactUsPage
		
		then: "fill out the form and request access"
		def title = selectTitleOption(2)
		fillOutFirstName('Dipen')
		fillOutlastName('Shah')
		fillOutEmail('dipen.shah@sap.com')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('SAP')
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(18)
		clickSendButton()
		checkNoteMessageExists()
	}
	
	/**
	 * TC BB-612 Automated test: BB-412 New customer requesting access
	 */
	def "New customer tried to submit form without filling out all require information"(){
		
		when: "At contact us page fill out most of the information"
		at ContactUsPage
		//def title = selectTitleOption(3)
		//fillOutFirstName('Dipen')
		fillOutlastName('Shah')
		fillOutEmail('cust@unit-2')
		fillOutPhone('111-222-3333')
		fillOutCompanyName('SAP')
		fillOutCustomerNumber('111-222-3333')
		def country = selectCountryOption(18)
		clickSendButton()
		
		then: "We should get an alert message"
		checkAlertMessageExists()
	}
}
