package lscob2b.pages.QuickOrder

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate
import lscob2b.modules.SizingGridModule;

class QuickOrderPage extends Page{
	static url = "/search/advanced"

	static at = { title == "Quick Order | LSCO B2B Site" }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		sizingGrid { module SizingGridModule}
		
		//Quick order page content
		keywordSearch { $("div label.control-label") }
		add { $(".add-product-ids button") }
		searchButton { $("div.searchButton button") }
		prdouctIdsOnly { $("div.idCheckbox div label") }
		quantity { $("div.cartTotals div.quantity span.label")}
		total { $("div.cartTotals div.total span.label") }
		cartButtons { $("div.cartButtons").find('a', href: endsWith('/')) }
		//checkOut { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')).text() }
		
		//To place an order
		searchInput { $("#js-product-ids") }
		searchLink { $("div.searchButton button") }
		prodcutIDs { $("div.idCheckbox div label") }
		checkOutLink { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')) }
		
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

	
	def doCheckOut(){
		checkOutLink.click()
	}
	
	def checkKeywordSearchLinkExists(){
		!keywordSearch.empty
	}
	
	def checkAddLinkExists(){
		!add.empty
	}
	
	def checkSearchButtonLinkExists(){
		!searchButton.empty
	}
	
	def checkPrdouctIdsOnlyLinkExists(){
		!prdouctIdsOnly.empty
	}

	def checkQuantityLinkExists(){
		!quantity.empty
	}
	
	def checkTotalLinkExists(){
		!total.empty
	}

	def checkCartButtonsLinkExists(){
		!cartButtons.empty
	}
	
	def checkCheckOuLinkExists(){
		!checkOutLink.empty
	}
}