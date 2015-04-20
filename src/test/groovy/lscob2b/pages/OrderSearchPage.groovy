package lscob2b.pages

import geb.Page
import lscob2b.modules.CheckOutModule
import lscob2b.modules.MasterTemplate
import lscob2b.modules.ProductCategoryModule
import lscob2b.modules.SizingGridModule

class OrderSearchPage extends Page {

	static String numberRegex = "\\d+(\\.|,)\\d+((\\.|,)\\d+)?"

	static at = {
		waitFor { title.endsWith("LSCO B2B Site")  || browser.currentUrl.contains("search")  }
	}

	static content = {
		
		masterTemplate { module MasterTemplate }
		
		sizingGrid { module SizingGridModule}
		
		checkOut { module CheckOutModule}
				
		messageText { $('div.content h2') }
		
		paginationBar { $("div.paginationBar") }
		
		sortOptions { $("select#sortOptions1") }
		
		//Localization
		
		pdpItems { $("div.productListItem").collect { module ProductCategoryModule, it } }
		
		productsFoundLabel { $('.paginationBar .totalResults') }
		
		sortByLabel { $('.paginationBar .sortForm label') }
		
		pageOfLabel { $('.paginationBar .pagination span') }
		
		refinementsLabel { $('.headline') }
		
		facetHeadLabels {$('.refinementToggle')}
		
	}
	
	def checkMessageTextExists(){
		messageText
	}
}