//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class OrderHistoryPage extends Page{

	static url = "my-account/orders"

	static at = { title == "Order History | LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		//Order history page content //TODO update this as page gets developed
		orderHistoryData { $("div#main-container>h1").text() }
		orderHistoryDescription { $("div.description").text() }
		orderHistoryBar { $("div.paginationBar").text() }
		orderHistoryListTable { $("table.orderListTable thead tr").text() }
	}
}
