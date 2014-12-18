package lscob2b.pages.QuickOrder

import geb.Page
import lscob2b.modules.MasterTemplate

class QuickOrderPage extends Page{
	static url = "/advanced"

	static at = { title == "Advanced Search | LSCO B2B Site" }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		
		
		//Quick order page content
		keywordSearch { $("div label.control-label").text() }
		//add { $("").text() }
		searchButton { $("div.searchButton button").text() }
		prdouctIdsOnly { $("div.idCheckbox div label").text() }
		quantity { $("div.cartTotals div.quantity span.label").text() }
		total { $("div.cartTotals div.total span.label").text() }
		cartButtons { $("div.cartButtons").find('a', href: endsWith('/')).text() }
		checkOut { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')).text() }
		//TODO figure out a way to capture the checkout element.
		
		//To place an order
		searchInput { $("#js-product-ids") }
		searchLink { $("div.searchButton button") }
		prodcutIDs { $("div.idCheckbox div label") }
		checkOutLink { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')) }
		orderQuantity { $("tr > td > .sku-quantity", 0) }
		addToCartLink { $("div > .ordergrid-buttons", 0) }
	}
	
	def doSearch(String productID){
		searchInput = productID
		prodcutIDs.click()
		searchLink.click()
	}
	
	def doCheckOut(){
		checkOutLink.click()
	}
	
	def addOrderQuantity(String quantityID){
		orderQuanity = quantityID
	}
	
	def doAddToCart(){
		addToCartLink.click()
	}
}