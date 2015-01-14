package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class CheckOutModule extends Module{

	static content = {
		checkOutLink { $("div.cartButtons").find('a', href: endsWith('/cart/checkout')) }
	}

	def doCheckOut(){
		checkOutLink.click()
	}
}
