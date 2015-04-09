package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class WaitListItemModule extends Module {

	static content = {
		
		buttonRemove { $("a", class:"button btn-warning submitWaitlistRemoveProduct") }
		
		buttonEditQuantities { $("a", class:"button btn-white toggle updateWaitlistQuantityProduct") }
		
		buttonHideQuantities { $("a", class:"button btn-white toggle updateWaitlistQuantityProduct btn-open") }
		
		sizingTable { module SizingTableModule, $("div.single_grid_three_dimensions") }
		
		buttonUpdate { $("a", class:"button update add_to_waitlist_button") }
		
		buttonCancel { $("a", class:"button btn-white cancel") }
		
	}

	def editOutOfStockQuantity(int quantityToAdd) {
		//Open Edit Quantity
		if(!buttonEditQuantities.empty) {
			buttonEditQuantities.click()
		}
		
		//Wait for table
		waitFor { sizingTable.displayed }
		
		//Update Quantity
		sizingTable.addOutOfStockStockQuantity(sizingTable.getOutOfStockQuantity() + quantityToAdd)
		
		//Update
		buttonUpdate.click()
		
	}
	
	
}
