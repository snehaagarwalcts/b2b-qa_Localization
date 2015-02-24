package lscob2b.pages.checkout

import geb.Page
import lscob2b.modules.MasterTemplate

class CheckOutPage extends Page{

	static url = "/checkout/single/summary"

	static at = { waitFor { title == "Checkout | LSCO B2B Site" } }
 
	static content = {
		
		masterTemplate {module MasterTemplate}
		
		placeOrderLink { $("div.cartButtons a.placeOrderButton") }
		
		cartItems { $("div.cartItem").collect { module CartModule, it  } }
		
		//Checkout page labels
		total { $('.total span') }
		subTotal { $('.subtotal span') }
		including { $('#ajaxCart .including') } //For this to work order simulation has to be turned on
		remove { $("#RemoveProduct_0",0) }
		removeConfirm { $("#RemoveProduct_0",1) }
		
		//Payment Terms
		creditCardPayment(required: false) { $('#PaymentTypeSelection_CARD') }
		invoicePayment(required: false) { $('#PaymentTypeSelection_ACCOUNT') }
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
	
	def checkCreditCardPaymentButtonExists(){
		!creditCardPayment.empty
	}
	
	def checkInvoicePaymentButtonExists(){
		!invoicePayment.empty
	}
		
}
