package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class SizingGridModule extends Module {

	static content = {

		form(wait:true) { $("form#AddToCartOrderForm") }
		
		tableSize(wait:true) { module SizingTableModule, $("div.single_grid_three_dimensions") }

		buttonAddToCart(wait:true) { $("a", class:"button-large add_to_cart_button") }	
		
		buttonNotifyMe { $("a", class:"button btn-white addtowaitlist") }
	}

	def addLimitedStockQuantityToCart(int quantity) {
		waitFor { tableSize.displayed }
		waitFor { buttonAddToCart.displayed }
		tableSize.addLimitedStockQuantity(quantity)
		buttonAddToCart.click()
	}
	
	
}
