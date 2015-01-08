package lscob2b.test.myaccount

import static lscob2b.TestConstants.*
import spock.lang.Ignore;
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.AddressBookPage
import lscob2b.pages.MyAccount.MyAccountPage
import lscob2b.test.data.TestDataCatalog


class AddressBookTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def loginAndGoToPage(user) {
		login(user)
		
		at HomePage
		masterTemplate.clickMyAccount()
				
		at MyAccountPage
		addressBookLink.click()
	}
	
	def "Check Users Shipping Address"() {
		setup:
		loginAndGoToPage(user)
		def shippingData = TestDataCatalog.getShippingAddress(user)
		
		when: "At Address Book page"
		at AddressBookPage
	
		then: "Test Shipping Data Size"
		assert addressShipping.size() == shippingData.size();
		
		and: "Test Shipping Data Content"
		for(int i = 0; i < shippingData.size(); i++) {
			assert getShippingFirstname(i) == shippingData[i].firstname
			assert getShippingStreetname(i) == shippingData[i].streetname
			assert getShippingLastname(i) == shippingData[i].lastname
			assert getShippingTown(i) == shippingData[i].town
			assert getShippingRegion(i) == shippingData[i].region
			assert getShippingPostalcode(i) == shippingData[i].postalcode
			assert getShippingCountry(i) == shippingData[i].country
		}			
		
		where:
		user << [TestDataCatalog.getALevisUser(), TestDataCatalog.getAMultibrandUser(), TestDataCatalog.getADockersUser()]
	}
	
	def "Check Users Billing Address"() {
		setup:
		loginAndGoToPage(user)
		def billingData = TestDataCatalog.getBillingAddress(user)
		
		when: "At Address Book page"
		at AddressBookPage
	
		then: "Test Billing Data Size"
		assert addressBilling.size() == billingData.size();
		
		and: "Test Billing Data Content"
		for(int i = 0; i < billingData.size(); i++) {
			assert getBillingFirstname(i) == billingData[i].firstname
			assert getBillingStreetname(i) == billingData[i].streetname
			assert getBillingLastname(i) == billingData[i].lastname
			assert getBillingTown(i) == billingData[i].town
			assert getBillingRegion(i) == billingData[i].region
			assert getBillingPostalcode(i) == billingData[i].postalcode
			assert getBillingCountry(i) == billingData[i].country
		}
		
		where:
		user << [TestDataCatalog.getALevisUser(), TestDataCatalog.getAMultibrandUser(), TestDataCatalog.getADockersUser()]
	}
	
}
