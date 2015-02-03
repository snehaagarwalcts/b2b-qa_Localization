package lscob2b.pages.QuickOrder

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate
import lscob2b.modules.SizingGridModule;
import lscob2b.modules.CheckOutModule

class QuickOrderPage extends Page{
	static url = "/search/advanced"

	static at = { waitFor { title == "Quick Order | LSCO B2B Site" } }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		sizingGrid { module SizingGridModule}
		checkOut { module CheckOutModule}
		
		productSizingGrids { $("div.cartItem").collect { module SizingGridModule, it  } }
		
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
		checkOutLink { $("a.checkout") }
		
		addToWaitListForm (required: false) { $("#AddToWaitListForm",1) }
		popupBoxClose { $("#popupBoxClose") }
	}
	
	def doSearch(String productID){
		searchInput = productID
		prodcutIDs.click()
		searchLink.click()
	}
	
	def doMultipleSearch(String productID1, String productID2) {
		searchInput.value(productID1 + ", " + productID2)
		prodcutIDs.click()
		searchLink.click()
	}
	
	def int getResultSize() {
		$("div.cartItem").size()
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
	
	def gotoCartPage() {
		waitFor {
			js.exec("return document.readyState").equals("complete")
		}
		checkOutLink.click()
	}
	
	
	
}