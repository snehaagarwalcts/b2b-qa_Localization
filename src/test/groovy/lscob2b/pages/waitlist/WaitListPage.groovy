package lscob2b.pages.waitlist

import static lscob2b.TestConstants.*
import geb.Page
import geb.navigator.Navigator
import lscob2b.modules.MasterTemplate


class WaitListPage extends Page {

	static url = "waitlist"

	static at = { title == "Your Shopping Waitlist | LSCO B2B Site" }

	static content = {
		masterTemplate {module MasterTemplate}
		itemLink { $(".details>.itemName>a", href: endsWith(it)) }
		quantityRequested { $(".details span.qty", id: contains(it)) }
		
		cartItems(required: false) { $("div.cartItems") }
		
		emptyItems(required: false) { $("div.blankSlate") }
		
	}

	Navigator getWaitingProductLink(String productCode){
		itemLink(productCode)
	}
	
	String getQuantityRequested(String productCode){
		quantityRequested(productCode).text()
	}
	
	int getProductQuantityRequested(String productCode) {
		if(!emptyItems.empty) return 0 //Waitlist is empty
		else {
			def item = cartItems.find("div.itemName > a", href:endsWith(productCode))
			return (item.empty) ? -1 : item.parent().parent().find("div.requested>span.qty").text().toInteger()
		}
	}
	
	def editProductQuantityRequested(String productCode, int quantity) {
		//Search for product Item
		def item = cartItems.find("div.itemName > a", href:endsWith(productCode))
		
		//Go up of 4 level to "div.cartItem"
		item = item.parent().parent().parent().parent()
		
		//Click EditQuantity
		item.find("a.updateWaitlistQuantityProduct").click()
		
		//Wait
		waitFor {
		
			//Update Quantity
			item.find("td.Red").find("input.sku-quantity",0).value(quantity)
			
			//Click Update
			item.find("a.add_to_waitlist_button").click()
			
		}
	}
	
	def removeProduct(String productCode) {
		//Search for product Item
		def item = cartItems.find("div.itemName > a", href:endsWith(productCode))
		
		//Go up of 4 level to "div.cartItem"
		item = item.parent().parent().parent().parent()
		
		//Click Remove
		item.find("a.submitWaitlistRemoveProduct").click()
		
	}
	
}