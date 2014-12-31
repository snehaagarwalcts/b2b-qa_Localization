package lscob2b.pages.waitlist

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate
import static lscob2b.TestConstants.*

class WaitListPage extends Page {

	static url = "waitlist"

	static at = { title == "LSCO B2B Site" }

	static content = {
		itemLink { $(".details>.itemName>a", href: endsWith(it)) }
		quantityRequested { $(".details span.qty", id: contains(it)) }
	}

	Navigator getWaitingProductLink(String productCode){
		itemLink(productCode)
	}
	
	String getQuantityRequested(String productCode){
		quantityRequested(productCode).text()
	}
}