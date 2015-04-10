package lscob2b.modules

import geb.Module

class ProductCategoryModule extends Module{

	static content = { 
		
		productImage { $("div.thumb img") }
		
		productTitle { $("div.head") }
		
		WholeSalePrice { $("div.price span.label") }
		
	}
}
