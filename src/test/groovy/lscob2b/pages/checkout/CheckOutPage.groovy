package lscob2b.pages.checkout

import geb.Page
import lscob2b.modules.MasterTemplate

class CheckOutPage extends Page{

	static url = "/checkout/single/summary"

	static at = { waitFor { title == "Checkout | LSCO B2B Site" } }
 
	static content = {
		masterTemplate {module MasterTemplate}
		
		placeOrderLink { $("div.cartButtons a.placeOrderButton") }
		
		//Checkout page labels
		total { $('.total span') }
		subTotal { $('.subtotal span') }
		including { $('#ajaxCart .including') } //For this to work order simulation has to be turned on
	}

	def doPlaceOrder(){
		placeOrderLink.click()
	}
	
	def checkTotalExists(){
		!total.empty
	}
	
	def checkSubTotalExists(){
		!subTotal.empty
	}
	
	def checkIncludingExists(){
		!including.empty
	}
}
