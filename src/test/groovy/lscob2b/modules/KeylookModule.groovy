package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class KeylookModule extends Module{

	static content = {
			
		thumb { $("div.thumb") }
		
		name { $("div.itemName") }
		
		style { $("div.itemStyle") }
		
		color { $("div.itemColor") }
		
		priceWholesale { $("div.wholesale-price") }
		
		priceRetail { $("div.recommended-retail-price") }
		
	}

}
