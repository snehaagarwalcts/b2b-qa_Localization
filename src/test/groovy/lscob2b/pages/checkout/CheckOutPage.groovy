package lscob2b.pages.checkout

import geb.Page
import lscob2b.modules.CartModule
import lscob2b.modules.MasterTemplate

class CheckOutPage extends Page{

	static url = "/checkout/single/summary"

	static at = { waitFor { title == "Checkout | LSCO B2B Site" || title == "DE_Checkout | LSCO B2B Site"} }
 
	static content = {
		
		masterTemplate {module MasterTemplate}
		
		placeOrderLink { $('form#placeOrderForm1 a.button-large.placeOrderButton') }
		
		cartItems { $("div.cartItem").collect { module CartModule, it } }
		
		//Checkout page labels
		
		total { $('.total span') }
		
		subTotal { $('.subtotal span') }
		
		including { $('#ajaxCart .including') } //For this to work order simulation has to be turned on
		
		remove { $("#RemoveProduct_0>p") }
		
		removeConfirm { $("#RemoveProduct_0>p",1) }
		
		//Payment Terms
		
		creditCardPayment(required: false) { $('#PaymentTypeSelection_CARD') }
		
		invoicePayment(required: false) { $('#PaymentTypeSelection_ACCOUNT') }
		
		creditCardDefault { $('#PaymentTypeSelection_CARD').attr('checked')}
		
		//Select Delivery address
		
		alternateDeliveryAddressSelect(required: false) { $('.button.editButton>p')}
		
		useAddressSelect{ $('div.addressEntry:nth-child(1) button.useAddress')}
		
		//No delivery address selected
		
		noDeliveryAddressSelected { $('#globalMessages .alert-message') }
		
		//remove cart items
		
		removeProducts { $('a.button.btn-white.default.submitRemoveProduct>p') }
		
		removePopUpHeader {$('#dialogue>h2')}
		
		removePopCancelButton {$('.button.btn-white.cancelRemoveProduct>p')}
		
		//Localization	
		
		addToCartForm { $('#AddToCartOrderForm') }

		labelSubTotal { $('div.subtotal span', it) }
		
		labelTotal { $('div.total span', it) }
		
		placeOrderLink1 { $('.button-large.checkout.placeOrderButton') }
		
		paymentMethodHeader { $('.payment.summarySection>h2') }
		
		labelCardPayment { $('label[for="PaymentTypeSelection_CARD"]') }
		
		labelInvoice { $('label[for="PaymentTypeSelection_ACCOUNT"]') }
		
		labelPONumber { $('.payment.summarySection>div>label') }
		
		labelSelectAddress { $('.contentSection>h2') }
		
		alternateAddressLink { $('.button.editButton>p') }
		
		labelNoneSelected { $('.contentSection') }
		
		labelToBeConfirmed { $('div.total', it) }
		
		headerAddressBook { $('.headline') }
		
		buttonUseThisAddress { $('.positive.useAddress', it) }
		
		continueShopping { $('.button-large.btn-txt-red>p') }
	}

	def doPlaceOrder(){
		waitFor { alternateDeliveryAddressSelect.displayed }
		alternateDeliveryAddressSelect.click()
		waitFor { useAddressSelect.displayed }
		useAddressSelect.click()
		waitFor { placeOrderLink.displayed }
		Thread.sleep(4000)
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
	
	def checkNoDeliveryAddressSelectedExists(){
		!noDeliveryAddressSelected.empty
	}
		
}
