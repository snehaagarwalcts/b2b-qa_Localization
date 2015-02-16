package lscob2b.pages.cart

import geb.Page
import lscob2b.modules.MasterTemplate
import lscob2b.modules.CartModule

class CartPage extends Page{

	static url = "/cart"

	static at = { waitFor { title == "Your Shopping Cart | LSCO B2B Site" } }

	static content = {
		masterTemplate {module MasterTemplate}
		cartTemplate {module CartModule} 	//TODO to remove use cartItems
		
		cartItems { $("div.cartItem").collect { module CartModule, it  } }
		
		alertMessage1 { $(".alert-message h2") }
		
		alertMessage2 { $(".alert-message p") }
		
		linkCheckout { $("a.checkout") }
		
		editQuantities { $(".itemButtons a.btn-white") }
		
		emptyCart { $("#main-container .blankSlate h2") }
	}
	
	
	def boolean removeProduct(productID) {
		String replaced = productID.collectReplacements{ if(it == '-') { '' } else { null } }
		println(replaced)
	}
	
	def editQuantitiesButtonclick(){
		editQuantities.click()
	}
	
	def emptyCartMessageExists(){
		!emptyCart.empty
	}
}
