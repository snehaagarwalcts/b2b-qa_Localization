//created by I844489 on 12/10/2014

package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class OrderHistoryPage extends Page{

	static url = "my-account/orders"

	static at = { waitFor { title == "Order History | LSCO B2B Site" } }

	static content = {
		
		masterTemplate { module MasterTemplate }

		//orderHistoryData { $("div#main-container>h1") }
		//orderHistoryDescription { $("div.description").text() }
		orderHistoryBar { $("div.paginationBar").text() }
		orderHistoryListTable { $("table.orderListTable thead tr").text()}

		/* FORM */
				
		searchForm { $("form#orderSearchForm") }
		
		searchButton(to: OrderHistoryPage) { searchForm.find("button", type:'submit') }
		
		clearButton { searchForm.find("a.clear") }
		
		fieldOrderNumber { searchForm.find("input", name: 'orderNum') }
		
		fieldPONumber { searchForm.find("input", name: 'poNum') }
	
		checkboxOrderSourceB2B { searchForm.find("input", type:'checkbox', name:'sources',0) }
		checkboxOrderSourceEDI { searchForm.find("input", type:'checkbox', name:'sources',1) }	
		checkboxOrderSourceSAP { searchForm.find("input", type:'checkbox', name:'sources',2) }		
		checkboxOrderSourceSFA { searchForm.find("input", type:'checkbox', name:'sources',3) }		
		checkboxOrderSourceLEO { searchForm.find("input", type:'checkbox', name:'sources',4) }		
		checkboxOrderTypeAO { searchForm.find("input", type:'checkbox', name:'types',0) }		
		checkboxOrderTypePB { searchForm.find("input", type:'checkbox', name:'types',1) }		
		checkboxOrderDate30 { searchForm.find("input", type:'radio', name:'duration',0) }		
		checkboxOrderDate90 { searchForm.find("input", type:'radio', name:'duration',1) }		
		checkboxOrderDateYear { searchForm.find("input", type:'radio', name:'duration',2) }	
		checkboxOrderStatusSubmitted { searchForm.find("input", type:'checkbox', name:'statuses',0) }
		checkboxOrderStatusCompleted { searchForm.find("input", type:'checkbox', name:'statuses',1) }
		checkboxOrderStatusInProgress { searchForm.find("input", type:'checkbox', name:'statuses',2) }

		//clickCheckbox  { $('.iCheck-helper', it) }
		
		/* Result */
		
		resultTable(required: false) { $("table.orderListTable") }
		
		invoiceNumber (require:false) { $("a.invoice") }
		
		/*  order history content */
		
		ordersFoundLabel { $(".totalResults",0) }
		sortByLabel { $("#sort_form1>label") }
		datePlacedLabel { $("#header1") }
		orderNumberLabel { $("#header2") }
		orderStatusLabel { $("#header3") }
		orderTypeLabel { $("#header4") }
		totalLabel { $("#header5") }
		orderSourceLabel { $("#header6") }
		invoiceLabel { $("#header7") }
		
		
		//localization
		clearSelection {$(".button.btn-txt-red.clear>p")}
		hideSearch{$(".button.btn-txt-grey.toggle>p")}				
		poNumberLabel {$(".control-label.forText:nth-child(1)")}
		orderNumberLabel {$(".control-label.forText:nth-child(3)")}		
		status {$(".control-group>h2",0)}
		date {$(".control-group>h2",1)}
		type {$(".control-group>h2",2)}
		orderSource {$(".control-group>h2",3)}
		orderSelectionLabels { $('.control-group>fieldset>div label', it) }
		searchOrderHistoryLink { $('.button.btn-txt-red.toggle>p') }
		ordersFoundLabel1 { $(".totalResults",1) }
		sortByLabel1 { $("#sort_form2>label") }
		sortByDropdown { $('.paginationBar .sortOptions', it) }
		sortByOptions { $('.paginationBar .sortOptions option', it) }
		pageLink { $('.pagination>span', it) }
	}
	
	def checkOrderHistoryData(){
		!masterTemplate.mainContainerLabel.empty
	}

	def clearForm() {
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
		fieldPONumber.value("")
		fieldOrderNumber.value("")
	}
	
	def isFormClear() {
		return !checkboxOrderSourceB2B.value() && !checkboxOrderSourceEDI.value() && !checkboxOrderSourceSAP.value() &&
		!checkboxOrderSourceSFA.value() && !checkboxOrderSourceLEO.value() && !checkboxOrderTypeAO.value() && 
		!checkboxOrderTypePB.value() && !checkboxOrderDate30.value() && !checkboxOrderDate90.value() &&
		!checkboxOrderDateYear.value() && !checkboxOrderStatusSubmitted.value() && !checkboxOrderStatusCompleted.value() &&
		!checkboxOrderStatusInProgress.value() && fieldOrderNumber.value() == "" && fieldPONumber.value() == ""
	}
	
	def switchOnForm() {
		checkboxOrderSourceB2B.parent().click()
		checkboxOrderSourceEDI.parent().click()
		checkboxOrderSourceSAP.parent().click()
		checkboxOrderSourceSFA.parent().click()
		checkboxOrderSourceLEO.parent().click()
		checkboxOrderTypeAO.parent().click()
		checkboxOrderTypePB.parent().click()
		checkboxOrderDate30.parent().click()
		checkboxOrderDate90.parent().click()
		checkboxOrderDateYear.parent().click()
		checkboxOrderStatusSubmitted.parent().click()
		checkboxOrderStatusCompleted.parent().click()
		checkboxOrderStatusInProgress.parent().click()		
		fieldPONumber.value("Test PO")
		fieldOrderNumber.value("Test Order")
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
	
//	def searchByOrderNumberAndOrderSource(String orderNumber) {
//		clickCheckbox(5).click()
//		fieldOrderNumber.value(orderNumber)
//		searchButton.click()
//	}
	
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
	
	def clickOnInvoiceNumber(){
		invoiceNumber.click()
	}
		
}
