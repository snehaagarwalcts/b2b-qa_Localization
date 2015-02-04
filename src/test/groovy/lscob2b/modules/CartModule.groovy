package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class CartModule extends Module{

	static content = {

		//Check the content of the cart
		itemName { $("div.itemName") }
		itemStyle { $("div.itemAttributes .itemStyle span") }
		itemColor { $("div.itemAttributes .itemColor span") }
		itemPrice { $("div.itemAttributes .itemPrice span") }
		itemQuantity { $("div.itemSummary .quantity span.label")}
		itemTotal { $("div.itemSummary .total span.label") }
		
		//Remove product from cart
		removeProductLink { $("#RemoveProduct_0") }
		removeProduct { $("#RemoveProduct_0",1) }
	}
	
	def doRemove(){
		removeProductLink.click()
		removeProduct.click()
	}
	
	def checkItemNameExists(){
		!itemName.empty
	}
	
	def checkItemStyleExists(){
		!itemStyle.empty
	}
	
	def checkItemColorExists(){
		!itemColor.empty
	}

	def checkItemPriceExists(){
		!itemPrice.empty
	}
	
	def checkItemQuantityExists(){
		!itemQuantity.empty
	}

	def checkItemTotalExists(){
		!itemTotal.empty
	}
}
