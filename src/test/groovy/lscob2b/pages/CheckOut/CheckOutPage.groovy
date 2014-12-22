package lscob2b.pages.CheckOut

import geb.Page
import lscob2b.modules.MasterTemplate

class CheckOutPage extends Page{

	static url = "/checkout/single/summary"

	static at = { title == "Checkout | LSCO B2B Site" }

	static content = {
		masterTemplate {module MasterTemplate}
		placeOrderLink { $("div.cartButtons a.placeOrderButton") }
		removeProductLink { $("div.itemButtons #RemoveProduct_0") }
		removeProduct { $("div.dialogueButtons #RemoveProduct_0") }
	}

	def doPlaceOrder(){
		placeOrderLink.click()
	}
	
	def doRemove(){
		removeProductLink.click()
		removeProduct.click()
	}
}
