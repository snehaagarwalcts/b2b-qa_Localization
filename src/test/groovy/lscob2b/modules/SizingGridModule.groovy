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
		tableSize.addLimitedStockQuantity(quantity)
		buttonAddToCart.click()
	}
	
	
//	def clickAddToWaitList(){
//		waitFor {
//			linkAddToWaitList.displayed
//		}
//		linkAddToWaitList.click()
//	}
//
//	def addToCart(){
//		addToCartLink.click()
//	}
//
//	def addOrderQuantity(String quantity){
//		$("form.add_to_cart_order_form input.sku-quantity",0).value(quantity)
//	}
//
//	def addQuantityToFirstPossibleItemInWaitListGrid(Integer value) {
//		waitFor { overlayWaitList.displayed }
//		overlayWaitList.find("form#AddToWaitListForm input.sku-quantity", 0).value(value)
//	}
//
//	def void clickNotifyMe() {
//		//Wait Quantity
//		waitFor {
//			linkNotifyMe.displayed
//		}
//		//Click Notify
//		linkNotifyMe.click();
//		
//	}
	
}
