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
	
}