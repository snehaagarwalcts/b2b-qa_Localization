//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class OrderHistoryPage extends Page{

	static url = "my-account/orders"

	static at = { title == "Order History | LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		orderHistoryData { $("div#main-container>h1") }
		orderHistoryDescription { $("div.description").text() }
		orderHistoryBar { $("div.paginationBar").text() }
		orderHistoryListTable { $("table.orderListTable thead tr").text() }

		/* FORM */
				
		searchForm { $("form#orderSearchForm") }
		
		searchButton(to: OrderHistoryPage) { searchForm.find("button", type:'submit') }
		
		fieldOrderNumber { searchForm.find("input", name: 'orderNum') }
	
		/* Result */
		
		resultTable { $("table.orderListTable") }
			
	}
	
	def checkOrderHistoryData(){
		!orderHistoryData.empty
	}

	def searchByOrderNumber(String orderNumber) {
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}	

	def checkUniqueResult() {
		resultTable.find("tbody>tr").size() == 1
	}
			
	def clickOnFirstOrder() {
		resultTable.find("tbody>tr a",0).click()
	}
		
}
