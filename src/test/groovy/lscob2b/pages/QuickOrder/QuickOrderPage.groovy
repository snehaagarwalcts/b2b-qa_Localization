package lscob2b.pages.QuickOrder

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate

class QuickOrderPage extends Page{
	static url = "/advanced"

	static at = { title == "Advanced Search | LSCO B2B Site" }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		
		
		//Quick order page content
		keywordSearch { $("div label.control-label").text() }
		add { $(".add-product-ids button").text() }
		searchButton { $("div.searchButton button").text() }
		prdouctIdsOnly { $("div.idCheckbox div label").text() }
		quantity { $("div.cartTotals div.quantity span.label").text() }
		total { $("div.cartTotals div.total span.label").text() }
		cartButtons { $("div.cartButtons").find('a', href: endsWith('/')).text() }
		checkOut { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')).text() }
		
		//To place an order
		searchInput { $("#js-product-ids") }
		searchLink { $("div.searchButton button") }
		prodcutIDs { $("div.idCheckbox div label") }
		checkOutLink { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')) }
		orderQuantity { $(".cartItem").not(".waitlist_grid_group").find("tr > td > .sku-quantity",0) }
		addToCartLink { $("a.add_to_cart_button") }
		
		/*//To place an order with multiple product ids
		addQuantity { $("#AddToCartOrderForm  tr > td > .sku-quantity", 0) }
		addButton { $(".add-product-ids button") }
		addToCartButton { $("") }*/
	}
	
	def doSearch(String productID){
		searchInput = productID
		prodcutIDs.click()
		searchLink.click()
	}
	
	/*def doAdd(String productID){
		searchInput = productID
		prodcutIDs.click()
		addButton.click()
	}*/
	
	/*def doAddButton(String productID){
		searchInput = productID
		addButton.click()
	}*/
	
	def doAddToCart(){
		waitFor(1){ addToCartLink.click() }
	}
	
	def addOrderQuantity(String quantity){
		waitFor(1){ orderQuantity.value(quantity) }
	}
	
	def doCheckOut(){
		checkOutLink.click()
	}
	
}