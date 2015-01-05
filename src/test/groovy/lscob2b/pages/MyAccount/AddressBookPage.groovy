//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class AddressBookPage extends Page{

	static url = "my-account/address-book"

	static at = { title == "Address Book | LSCO B2B Site" }

	static content = {
		
		masterTemplate { module MasterTemplate }
		
		mainContainer { $("#main-container > h1") }
		
		addressBook { $("div.addressItems",0).find("div.addressItem") }
		
		addressBilling { $("div.addressItems",1).find("div.addressItem") }
		
		//Address book page content //TODO add more content as page gets developed
		addressBookData { $("div strong")*.text() }

		addressItem { $("div.addressItem ul").text() }

	}
	
	//Getting Address Book Information
	
	def getBookRoad(index) {
		addressBook[index].find("ul li",1).text()
	}
	
	def getBookAddress(index) {
		addressBook[index].find("ul li",2).text()
	}
	
	def getBookCity(index) {
		addressBook[index].find("ul li",3).text()
	}
	
	def getBookCode(index) {
		addressBook[index].find("ul li",5).text()
	}
	
	def getBookCountry(index) {
		addressBook[index].find("ul li",6).text()
	}
	
	//Getting Address Billing Information
	
	def getBillingRoad(index) {
		addressBilling[index].find("ul li",1).text()
	}
	
	def getBillingAddress(index) {
		addressBilling[index].find("ul li",2).text()
	}
	
	def getBillingCity(index) {
		addressBilling[index].find("ul li",3).text()
	}
	
	def getBillingCode(index) {
		addressBilling[index].find("ul li",5).text()
	}
	
	def getBillingCountry(index) {
		addressBilling[index].find("ul li",6).text()
	}
}
