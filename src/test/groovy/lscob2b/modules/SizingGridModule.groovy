package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class SizingGridModule extends Module {

	static content = {

		orderQuantity { $(".cartItem").not(".waitlist_grid_group").find("tr > td > .sku-quantity",0) }
		
		addToCartLink { $("a.add_to_cart_button") }
		
		linkNotifyMe(required: false) { $("a.addtowaitlist") }
		
		linkAddToWaitList(required: false) { $("a#add_to_waitlist_button",1) }
		
		overlayWaitList(required: false) { $("div#overlay") }
		
	}

	def clickAddToWaitList(){
		waitFor {
			linkAddToWaitList.displayed
		}
		linkAddToWaitList.click()
	}

	def addToCart(){
		addToCartLink.click()
	}

	def addOrderQuantity(String quantity){
		$("form.add_to_cart_order_form input.sku-quantity",0).value(quantity)
	}

	def addQuantityToFirstPossibleItemInWaitListGrid(Integer value) {
		waitFor { overlayWaitList.displayed }
		overlayWaitList.find("form#AddToWaitListForm input.sku-quantity", 0).value(value)
	}

	def void clickNotifyMe() {
		//Wait Quantity
		waitFor {
			linkNotifyMe.displayed
		}
		//Click Notify
		linkNotifyMe.click();
		
	}
	
}
