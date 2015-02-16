package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class SizingModule extends Module {

	//ROOT ELEMENT IS div.single_grid_three_dimensions
	
	static content = {
		
		table { $("table.grid_three_dimensions") }
		
	}

	
	def void addLimitedStockQuantity(int quantity) {
		waitFor { table.displayed }
		table.find("td.Yellow",0).find("input.sku-quantity").value(quantity)
	}
	
}
