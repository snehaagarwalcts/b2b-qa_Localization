package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class SizingGridModule extends Module{

	static content = {
		notifyMeWhenItemsBecomeAvailableLink {$("a.addtowaitlist")}
		addToWaitListButton {$("a.add_to_waitlist_button",1)}
		orderQuantity { $(".cartItem").not(".waitlist_grid_group").find("tr > td > .sku-quantity",0) }
		addToCartLink { $("a.add_to_cart_button") }
	}

	def clickNotifyMeWhenItemsBecomeAvailable(){
		notifyMeWhenItemsBecomeAvailableLink.click()
	}

	def clickAddToWaitList(){
		addToWaitListButton.click()
	}

	def addToCart(){
		waitFor(1){ addToCartLink.click() }
	}

	def addOrderQuantity(String quantity){
		waitFor(1){ orderQuantity.value(quantity) }
	}
}
