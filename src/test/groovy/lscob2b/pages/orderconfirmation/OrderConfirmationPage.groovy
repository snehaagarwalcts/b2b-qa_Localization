package lscob2b.pages.orderconfirmation

import geb.Page
import lscob2b.modules.CartModule;
import lscob2b.modules.MasterTemplate
import lscob2b.modules.OrderModule;
import lscob2b.test.data.Address;
import lscob2b.test.data.Order;

class OrderConfirmationPage extends Page{
	
	static url = "/checkout/orderConfirmation/"

	static at = { waitFor { title == "Order Confirmation | LSCO B2B Site" || title == "DE_Order Confirmation | LSCO B2B Site"} }

	static content = {
		
		masterTemplate {module MasterTemplate}
	
		order {module OrderModule}
		
		orderItems { $("div.cartItem").collect { module CartModule, it  } }
		
		buttonShowQuantities { $('.showQuantitiesProduct') }
		
		buttonHideQuantities { $('.showQuantitiesProduct') }
		
		labelQuantity { $('.checkoutTotals .quantity .label') }
		
		labelSubTotal { $('.checkoutTotals .subtotal .label') }
		
		labelTotal { $('.checkoutTotals .total span') }
		
		labelToBeConfirmed { $('.checkoutTotals .total') }
		
		labelLine { $('#header1') }
		
		labelProductCode { $('#header2') }
		
		labelProductSize { $('#header3') }
		
		labelQuantityOrdered { $('#header4') }
		
	}			
}
