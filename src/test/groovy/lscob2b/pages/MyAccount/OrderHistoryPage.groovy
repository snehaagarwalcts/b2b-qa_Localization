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
		
		clearButton() { searchForm.find("a.clear") }
		
		fieldOrderNumber { searchForm.find("input", name: 'orderNum') }
	
		checkboxOrderSourceB2B { searchForm.find("input", type:'checkbox', name:'b2b',0) }
		
		checkboxOrderSourceEDI { searchForm.find("input", type:'checkbox', name:'edi',0) }
		
		checkboxOrderSourceSAP { searchForm.find("input", type:'checkbox', name:'sap',0) }
		
		checkboxOrderSourceSFA { searchForm.find("input", type:'checkbox', name:'sfa',0) }
		
		checkboxOrderSourceLEO { searchForm.find("input", type:'checkbox', name:'leo',0) }
		
		checkboxOrderTypeAO { searchForm.find("input", type:'checkbox', name:'atOnce',0) }
		
		checkboxOrderTypePB { searchForm.find("input", type:'checkbox', name:'preBook',0) }
		
		checkboxOrderDate30 { searchForm.find("input", type:'checkbox', name:'last30Days',0) }
		
		checkboxOrderDate90 { searchForm.find("input", type:'checkbox', name:'last90Days',0) }
		
		checkboxOrderDateYear { searchForm.find("input", type:'checkbox', name:'lastYear',0) }
		
		checkboxOrderStatusSubmitted { searchForm.find("input", type:'checkbox', name:'submitted',0) }
		
		checkboxOrderStatusCompleted { searchForm.find("input", type:'checkbox', name:'completed',0) }
		
		checkboxOrderStatusInProgress { searchForm.find("input", type:'checkbox', name:'inProgress',0) }
		
		/* Result */
		
		resultTable(required: false) { $("table.orderListTable") }
			
	}
	
	def checkOrderHistoryData(){
		!orderHistoryData.empty
	}

	def clearForm() {
//		clearButton.click() //FIXME not work
		if(checkboxOrderSourceB2B.value() != false) checkboxOrderSourceB2B.parent().click()
		if(checkboxOrderSourceEDI.value() != false) checkboxOrderSourceEDI.parent().click()
		if(checkboxOrderSourceSAP.value() != false) checkboxOrderSourceSAP.parent().click()
		if(checkboxOrderSourceSFA.value() != false) checkboxOrderSourceSFA.parent().click()
		if(checkboxOrderSourceLEO.value() != false) checkboxOrderSourceLEO.parent().click()
		if(checkboxOrderTypeAO.value() != false) checkboxOrderTypeAO.parent().click()
		if(checkboxOrderTypePB.value() != false) checkboxOrderTypePB.parent().click()
		if(checkboxOrderDate30.value() != false) checkboxOrderDate30.parent().click()
		if(checkboxOrderDate90.value() != false) checkboxOrderDate90.parent().click()
		if(checkboxOrderDateYear.value() != false) checkboxOrderDateYear.parent().click()
		if(checkboxOrderStatusSubmitted.value() != false) checkboxOrderStatusSubmitted.parent().click()
		if(checkboxOrderStatusCompleted.value() != false) checkboxOrderStatusCompleted.parent().click()
		if(checkboxOrderStatusInProgress.value() != false) checkboxOrderStatusInProgress.parent().click()
		
		fieldOrderNumber.value("")
	}
	
	
	def searchByOrderNumber(String orderNumber) {
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}	

	def searchByOrderNumberAndOrderSource(String orderNumber, boolean b2b, boolean edi, boolean sap, boolean sfa, boolean leo) {
		if(checkboxOrderSourceB2B.value() != b2b) checkboxOrderSourceB2B.parent().click()
		if(checkboxOrderSourceEDI.value() != edi) checkboxOrderSourceEDI.parent().click()
		if(checkboxOrderSourceSAP.value() != sap) checkboxOrderSourceSAP.parent().click()
		if(checkboxOrderSourceSFA.value() != sfa) checkboxOrderSourceSFA.parent().click()
		if(checkboxOrderSourceLEO.value() != leo) checkboxOrderSourceLEO.parent().click()
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}
	
	def searchByOrderNumberAndOrderType(String orderNumber, boolean atOnce, boolean preBook) {
		if(checkboxOrderTypeAO.value() != atOnce) checkboxOrderTypeAO.parent().click()
		if(checkboxOrderTypePB.value() != preBook) checkboxOrderTypePB.parent().click()
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}
	
	def searchByOrderNumberAndOrderDate(String orderNumber, boolean last30, boolean last90, boolean lastYear) {
		if(checkboxOrderDate30.value() != last30) checkboxOrderDate30.parent().click()
		if(checkboxOrderDate90.value() != last90) checkboxOrderDate90.parent().click()
		if(checkboxOrderDateYear.value() != lastYear) checkboxOrderDateYear.parent().click()
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}
	
	def searchByOrderNumberAndOrderStatus(String orderNumber, boolean submitted, boolean completed, boolean inProgress) {
		if(checkboxOrderStatusSubmitted.value() != submitted) checkboxOrderStatusSubmitted.parent().click()
		if(checkboxOrderStatusCompleted.value() != completed) checkboxOrderStatusCompleted.parent().click()
		if(checkboxOrderStatusInProgress.value() != inProgress) checkboxOrderStatusInProgress.parent().click()
		fieldOrderNumber.value(orderNumber)
		searchButton.click()
	}
	
	def checkUniqueResult() {
		resultTable.find("tbody>tr").size() == 1
	}
	
	def checkEmptyResult() {
		resultTable.empty
	}
			
	def clickOnFirstOrder() {
		resultTable.find("tbody>tr a",0).click()
	}
		
}
