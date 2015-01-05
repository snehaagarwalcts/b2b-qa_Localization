package lscob2b.test.myaccount

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import groovy.json.JsonSlurper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.AddressBookPage
import lscob2b.pages.MyAccount.MyAccountPage

class AddressBookTest extends GebReportingSpec {

	def static jsonData = new JsonSlurper().parseText(new File("src/test/resources/testinput/AddressBook.json").text)
	
	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def loginAndGoToPage(username) {
		doLogin(username, defaultPassword)
		
		at HomePage
		masterTemplate.clickMyAccount()
				
		at MyAccountPage
		addressBookLink.click()
	}
	
	def "Check Users Address Book"() {
		setup:
		loginAndGoToPage(user)
	
		when: "At Address Book page"
		at AddressBookPage
	
		then: "Test Book Data Size"
		assert addressBook.size() == bookData.size();
		
		and: "Test Book Data Content"
		for(int i = 0; i < bookData.size(); i++) {
			assert getBookRoad(i) == bookData[i].road
			assert getBookAddress(i) == bookData[i].address
			assert getBookCity(i) == bookData[i].city 
			assert getBookCode(i) == bookData[i].code
			assert getBookCountry(i) == bookData[i].country
		}			
		
		and: "Test Billing Data Size"
		assert addressBilling.size() == billingData.size();
			
		and: "Test Billing Data Content"
		for(int i = 0; i < billingData.size(); i++) {
			assert getBillingRoad(i) == billingData[i].road
			assert getBillingAddress(i) == billingData[i].address
			assert getBillingCity(i) == billingData[i].city
			assert getBillingCode(i) == billingData[i].code
			assert getBillingCountry(i) == billingData[i].country
		}
		
		where:
		user | bookData | billingData
		dockersUser | jsonData.dockersUser.book | jsonData.dockersUser.billing
		administrator | jsonData.administrator.book | jsonData.administrator.billing
	}
	

}
