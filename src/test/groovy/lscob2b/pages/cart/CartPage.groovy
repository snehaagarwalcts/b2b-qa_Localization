package lscob2b.pages.cart

import geb.Page
import lscob2b.modules.CartModule
import lscob2b.modules.MasterTemplate

class CartPage extends Page{

	static url = "/cart"

	static at = { waitFor { title == "Your Shopping Cart | LSCO B2B Site" || title =="DE_Your Shopping Cart | LSCO B2B Site" } }

	static content = {
		
		masterTemplate {module MasterTemplate}
				
		cartItems { $("div.cartItem").collect { module CartModule, it  } }
		
		alertMessage1 { $(".alert-message h2") }
		
		alertMessage2 { $(".alert-message p") }
		
		continueShopping { $(".cartButtons").find('a', href: endsWith('/')) }
		
		linkCheckout(required: false) { $("a.checkout") }
		
		//Empty Cart messages
		
		emptyCart (required: false) { $("#main-container .blankSlate h2") }
		
		alertContainer { $("div.alert-container") }
		
		alertMessage { alertContainer.find("div.alert-message") }
		
		//remove cart items
		
		removeProducts { $('a.button.btn-white.default.submitRemoveProduct>p') }
		
		removePopUpHeader {$('#dialogue>h2')}
		
		removePopCancelButton {$('.button.btn-white.cancelRemoveProduct>p')}
		
		//LOCALIZATION
		
		itemLabels { $('.label', it) }
		
		addToCartForm { $('#AddToCartOrderForm') }
		
	}
	
	
	def boolean removeProduct(productID) {
		String replaced = productID.collectReplacements{ if(it == '-') { '' } else { null } }
		println(replaced)
	}
	
	def doRemove(){
		waitFor{ cartItems[0].removeProductLink.displayed }
		cartItems[0].removeProductLink.click()
		waitFor{ removeProducts.displayed }
		removeProducts.click()
	}
	
	def checkItemNameExists(){
		!cartItems[0].itemName.empty
	}
	
	def checkItemStyleExists(){
		!cartItems[0].itemStyle.empty
	}
	
	def checkItemColorExists(){
		!cartItems[0].itemColor.empty
	}

	def checkItemPriceExists(){
		!cartItems[0].itemPrice.empty
	}
	
	def checkItemQuantityExists(){
		!cartItems[0].itemQuantity.empty
	}

	def checkItemTotalExists(){
		!cartItems[0].itemTotal.empty
	}
	
	def checkContinueShoppingButtonExists(){
		!continueShopping.empty
	}
	
	def checkCheckoutButtonExists(){
		!linkCheckout.empty
	}
	
	def checkEditQuantitiesButtonExists(){
		!cartItems[0].editQuantities.empty
	}
	
	def checkRemoveProductButtonExists(){
		!cartItems[0].removeProductLink.empty
	}
	
	def editQuantitiesButtonclick(){
		cartItems[0].editQuantities.click()
	}
	
	def emptyCartMessageExists(){
		waitFor { emptyCart.displayed }		
	}
	
	def boolean removeExistingProducts()
	{
		if (!cartItems[0].removeProductLink.empty)
		{
			cartItems[0].removeProductLink.click()
			removeProducts.click()

		}
		return true
	}
}
