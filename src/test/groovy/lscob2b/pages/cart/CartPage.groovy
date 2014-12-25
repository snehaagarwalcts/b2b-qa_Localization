package lscob2b.pages.cart

import geb.Page
import lscob2b.modules.MasterTemplate
import lscob2b.modules.CartModule

class CartPage extends Page{

	static url = "/cart"

	static at = { title == "Your Shopping Cart | LSCO B2B Site" }

	static content = {
		masterTemplate {module MasterTemplate}
		cartTemplate {module CartModule}
	}
}