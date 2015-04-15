package lscob2b.modules

import geb.Module
import lscob2b.test.data.Address
import lscob2b.test.data.Order

class OrderModule extends Module {

	static content = {
		orderInfo { $("div.info") }
		
		orderAddress { $("div.address") }
		
		paymentInfo { $("div.payment") }
		
		labelOrderNumber {orderInfo.find('h2')}
		
		labelOrderDesc {orderInfo.find('p',1)}
		
		headerorderAddress {orderAddress.find('h2')}		
		
		labelPaymentDetalis { paymentInfo.find('h2')}
		
		labelOrderPlacedBy { paymentInfo.find('p span',0) }
		
		labelPONumber { paymentInfo.find('p span',1) }
		
		labelPaymentMethod { paymentInfo.find('p span',2) }
		
		labelPaymentTerms { paymentInfo.find('p span',3) }
		
	}

	//Order
	
	def String getOrderNumber() {
		return orderInfo.find("p.ordernumber",0).text()
	}

	//Address
		
	def String getAddressStreetname() {
		return orderAddress.find("p",0)
	}
	
	def String getAddressTown() {
		return orderAddress.find("p",2)
	}
	
	def String getAddressRegion() {
		return orderAddress.find("p",3)
	}
	
	def String getAddressPostalcode() {
		return orderAddress.find("p",4)
	}
	
	def String getAddressCountry() {
		return orderAddress.find("p",5)
	}
	
	//Product
	
	def String getProductCode() {
		
	}
	
	//Bean
	
	def getOrder() {
		Order order = new Order()
		order.number = getOrderNumber()
		order.address = getAddress()
		return order
	}
	
	def getAddress() {
		Address address = new Address()
//		address.firstname
//		address.lastname
		address.streetname = getAddressStreetname()
		address.town = getAddressTown()
		address.region = getAddressRegion()
		address.postalcode = getAddressPostalcode()
		address.country = getAddressCountry()
		return address
	}
		
}
