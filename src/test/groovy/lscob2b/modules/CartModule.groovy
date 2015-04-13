package lscob2b.modules

import geb.Module;

class CartModule extends Module{

	static content = {
		
		itemName { $("div.itemName") }
	
		itemStyle { $("div.itemStyle span") }
		
		itemColor { $("div.itemColor span") }
		
		itemPrice { $("div.itemPrice span") }
		
		itemQuantity { $('div.quantity .label') }
		
		itemTotal { $('div.total span') }
		
		removeProductLink { $('.itemButtons a.btn-warning') }
		
		editQuantities { $('.itemButtons a.btn-white') }
		
		buttonHideQuantities { $('.button.btn-white.toggle') }
		
		inStockLabel { $('.available') }
		
		limitedStockLabel { $('.limited') }
		
		outOfStockLabel { $('.outofstock') }
		
		buttonUpdate { $("a", class:"button update add_to_cart_button") }
		
		buttonCancel { $("a", class:"button btn-txt-red cancel") }
		
	}
}
