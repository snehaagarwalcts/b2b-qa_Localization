package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class SellModule extends Module{

	static content = {
		
		title { $("h2.section-title") }
		
		items { $("ul li") }
		
		itemLink { productCode -> items.find("a", href: endsWith(productCode)) }
		
	}

}
