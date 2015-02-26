//created by I844489 on 12/10/2014

package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class AddressBookPage extends Page{

	static url = "my-account/address-book"

	static at = { waitFor { title == "Address Book | LSCO B2B Site" }  }

	static content = {
		
		masterTemplate { module MasterTemplate }
		
		mainContainer { $("#main-container > h1") }
		
		addressShipping { $("div.addressItems",0).find("div.addressItem") }
		
		addressBilling { $("div.addressItems",1).find("div.addressItem") }
		
		addressBookData { $("div strong")*.text() }

		addressItem { $("div.addressItem ul").text() }

	}
	
	//Getting Address Shipping Information
	
	def getShippingFirstname(index) {
		addressShipping[index].find("ul li",0).text().trim()	
	}
	
	def getShippingStreetname(index) {
		addressShipping[index].find("ul li",1).text()
	}
	
	def getShippingLastname(index) {
		addressShipping[index].find("ul li",2).text()	
	}
	
	def getShippingTown(index) {
		addressShipping[index].find("ul li",3).text()
	}
	
	def getShippingRegion(index) {
		addressShipping[index].find("ul li",4).text()
	}
	
	def getShippingPostalcode(index) {
		addressShipping[index].find("ul li",5).text()
	}
	
	def getShippingCountry(index) {
		addressShipping[index].find("ul li",6).text()
	}
	
	
	//Getting Address Billing Information
	
	def getBillingFirstname(index) {
		addressBilling[index].find("ul li",0).text().trim()
	}

	def getBillingStreetname(index) {
		addressBilling[index].find("ul li",1).text()
	}

	def getBillingLastname(index) {
		addressBilling[index].find("ul li",2).text()	
	}

	def getBillingTown(index) {
		addressBilling[index].find("ul li",3).text()
	}

	def getBillingRegion(index) {
		addressBilling[index].find("ul li",4).text()
	}

	def getBillingPostalcode(index) {
		addressBilling[index].find("ul li",5).text()
	}

	def getBillingCountry(index) {
		addressBilling[index].find("ul li",6).text()
	}
}
