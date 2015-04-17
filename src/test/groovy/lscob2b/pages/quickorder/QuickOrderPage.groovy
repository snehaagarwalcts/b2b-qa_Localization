package lscob2b.pages.quickorder

import geb.Page
import geb.navigator.Navigator
import lscob2b.modules.CartModule
import lscob2b.modules.MasterTemplate
import lscob2b.modules.SizingGridModule
import lscob2b.modules.SizingTableModule

class QuickOrderPage extends Page{
	
	static url = "/search/advanced"

	static at = { waitFor { title == "Quick Order | LSCO B2B Site" || title == "DE_Quick Order | LSCO B2B Site"} }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		
		productSizingGrids { $("div.cartItem").collect { module SizingGridModule, it  } }
		
		quickOrderItems { $("div.cartItem").collect { module CartModule, it  } }
		
		//FORM
		
		searchForm { $("form#advancedSearchForm") }
		
		searchInput { searchForm.find("input",name:"keywords") }
		
		searchLink { searchForm.find("button.adv_search_button") }
		
		checkboxProductIDs { searchForm.find("input", name:"onlyProductIds") }

		divCheckboxProductIDs { checkboxProductIDs.parent() }
		
		buttonAdd { searchForm.find("button#js-add-product-ids") }
		
		//PAGE
		
		spanQuantity { $("span#total-items-count") }
		
		spanPrice { $("span#total-price") }
				
		checkOutLink { $(".button-large.checkout>p") }
		
		//OverLay
		
		overlayWaitList { $("div.popup_box") }
		
		overlayTable { overlayWaitList.find("table.grid_three_dimensions") }
		
		overlayButtonAdd { overlayWaitList.find("a#add_to_waitlist_button") }
		
		overlayClosePopUp { $('#popupBoxClose')}
		
		//Localization
		
		breadCrumbLink { $('#breadcrumb>ul>li:nth-of-type(3)')}
		
		advancedSearch { $('.control-label', it)}
		
		blankSlateHeader { $('.blankSlate>h2')}
		
		blankSlateContent { $('.blankSlate>p:nth-of-type(1)')}
		
		helpLink { $('.blankSlate>p>a')}
		
		quantityAndTotal { $('.label', it)}
		
		continueshoppingLink { $('.button-large.btn-txt-red>p')}
		
		productsFoundlabel { $('.totalResults') }
		
		itemLabels { $('.label', it) }
		
		enterSizeLabel { $('.product-grid-header>h2') }
		
		sizeGuideLabel { $('.product-grid-header>p>a') }
		
		waitListHeader { $('div.popup_box h2') }

	}
	
	def doSearch(String toSearch, boolean onlyProductIds){
		searchInput = toSearch
		if(checkboxProductIDs.value() != onlyProductIds) {
			divCheckboxProductIDs.click()
		}
		searchLink.click()
	}
	
	def doMultipleSearch(String productID1, String productID2) {
		searchInput.value(productID1 + "," + productID2)
		if(checkboxProductIDs.value() != 'true') {
			divCheckboxProductIDs.click()
		}
		searchLink.click()
	}
	
	
	def addLimitedStockQuantityToCart(int index, int quantity) {
		productSizingGrids[index].addLimitedStockQuantityToCart(quantity)
	}
	
	def addOutOfStockQuantityToWaitList(int index, int quantity) {
		waitFor { productSizingGrids[index].displayed }
		waitFor { productSizingGrids[index].buttonNotifyMe.displayed }
		
		productSizingGrids[index].buttonNotifyMe.click()
		
		waitFor { overlayWaitList.displayed }
		
		waitFor { overlayTable.displayed }
		
		overlayTable.find("td.Red",0).find("input.sku-quantity").value(quantity)
		
		overlayButtonAdd.click()
		
		overlayClosePopUp.click()
		
	}
	
	def int getResultCount() {
		waitFor {
			!productSizingGrids.empty
		}
		productSizingGrids.size()
	}
	
	def boolean checkResultSize(int size) {
		waitFor { productSizingGrids.size() == size }
	}
	
}