package lscob2b.pages.orderconfirmation

import geb.Page
import lscob2b.modules.MasterTemplate
import lscob2b.modules.OrderModule;
import lscob2b.test.data.Address;
import lscob2b.test.data.Order;

class OrderConfirmationPage extends Page{
	
	static url = "/checkout/orderConfirmation/"

	static at = { waitFor { title == "Order Confirmation | LSCO B2B Site" } }

	static content = {
		
		masterTemplate {module MasterTemplate}
	
		order {module OrderModule}
		
	}
	
	
	
}
