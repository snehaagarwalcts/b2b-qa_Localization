package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class KeylookModule extends Module{

	static content = {
			
		thumb { $("div.thumb") }
		
		itemName { $("div.itemName") }
		
		style { $("div.itemStyle span") }
		
		color { $("div.itemColor span") }
		
		priceWholesale { $("div.itemPrice span") }
		
		//priceRetail { $("div.recommended-retail-price") }
		
		//Localization
		
		inStockLabel { $('.available') }
		
		limitedStockLabel { $('.limited') }
		
		outOfStockLabel { $('.outofstock') }
		
	}
}
