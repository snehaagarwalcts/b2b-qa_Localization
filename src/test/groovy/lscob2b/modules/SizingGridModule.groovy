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

	def waitForSizingGridLoadedCompletely(){
		Thread.sleep(1000)  //TODO this is a workaround solution until we find how to make sure that sizing grid is completely loaded/prepared before we click on notify_me
		return true
//		waitFor(1){
//			! $("a#add_to_waitlist_button").empty
//		}
	}

	def clickNotifyMeWhenItemsBecomeAvailable(){
		waitForSizingGridLoadedCompletely() //if we click notifyMeWhenItemsBecomeAvailableLink quickly waitlist is not opened properly. 
		waitFor(1){ notifyMeWhenItemsBecomeAvailableLink.click() }
	}

	def void checkIfWaitListGridPoppedUp(){
		waitFor(1){
			$("div#overlay form#AddToWaitListForm input.sku-quantity", 0).displayed
		}
	}

	def clickAddToWaitList(){
		addToWaitListButton.click()
	}

	def addToCart(){
		addToCartLink.click()
	}

	def addOrderQuantity(String quantity){
		orderQuantity.value(quantity)
	}

	def addQuantityToFirstPossibleItemInWaitListGrid(Integer value){
		$("div#overlay form#AddToWaitListForm input.sku-quantity", 0).value(value)
	}

	def void clickNotifyMe() {
		notifyMeWhenItemsBecomeAvailableLink.click()
		waitFor {
			$("div#overlay").displayed
		}
	}
	
}
