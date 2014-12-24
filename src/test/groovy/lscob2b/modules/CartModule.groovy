package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class CartModule extends Module{

	static content = {

		//Check the content of the cart
		itemName { $("div.itemName").text() }
		itemStyle { $("div.itemAttributes .itemStyle span").text() }
		itemColor { $("div.itemAttributes .itemColor span").text() }
		itemPrice { $("div.itemAttributes .itemPrice span").text() }
		itemQuantity { $("div.itemSummary .quantity span.label").text() }
		itemTotal { $("div.itemSummary .total span.label").text() }
		cartIsEmpty { $(".content h2").text() }
	}
}
