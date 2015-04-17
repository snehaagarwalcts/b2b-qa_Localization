package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.OrderHistoryPage
import lscob2b.test.data.PropertProvider

class OrderHistoryPageTest extends PropertProvider {
		
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify OrderHistory Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on OrderHistory Link "
		clickOnOrderHistoryLink()
		
		when: "at OrderHistoryPage"
		at OrderHistoryPage
	
		then: "Verify translations at OrderHistoryPage"
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("text.account.orderHistory").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.account.orderHistory").toUpperCase())	
		verifyTrue(status.text(),expectedValue("text.account.orderHistory.orderStatus").toUpperCase())
		verifyTrue(date.text(),expectedValue("account.order.date").toUpperCase())
		verifyTrue(type.text(),expectedValue("account.order.type").toUpperCase())
		verifyTrue(orderSource.text(),expectedValue("account.order.source").toUpperCase())
		verifyTrue(poNumberLabel.text(), expectedValue("account.order.ponum").toUpperCase())
		verifyTrue(orderNumberLabel.text(),expectedValue("account.order.number").toUpperCase())		
		verifyTrue(orderSelectionLabels(0).text(),expectedValue("ordersearch.submitted").toUpperCase())
		verifyTrue(orderSelectionLabels(1).text(),expectedValue("ordersearch.completed").toUpperCase())
		verifyTrue(orderSelectionLabels(2).text(),expectedValue("ordersearch.inProgress").toUpperCase())
		verifyTrue(orderSelectionLabels(3).text(),expectedValue("ordersearch.last30Days").toUpperCase())
		verifyTrue(orderSelectionLabels(4).text(),expectedValue("ordersearch.last90Days").toUpperCase())
		verifyTrue(orderSelectionLabels(5).text(),expectedValue("ordersearch.lastYear").toUpperCase())
		verifyTrue(orderSelectionLabels(6).text(),expectedValue("ordersearch.atOnce").toUpperCase())
		verifyTrue(orderSelectionLabels(7).text(),expectedValue("ordersearch.preBook").toUpperCase())
		verifyTrue(orderSelectionLabels(8).text(),expectedValue("ordersearch.b2b").toUpperCase())
		verifyTrue(orderSelectionLabels(9).text(),expectedValue("ordersearch.edi").toUpperCase())
		verifyTrue(orderSelectionLabels(10).text(),expectedValue("ordersearch.sap").toUpperCase())
		verifyTrue(orderSelectionLabels(11).text(),expectedValue("ordersearch.sfa").toUpperCase())
		verifyTrue(orderSelectionLabels(12).text(),expectedValue("ordersearch.leo").toUpperCase())		
		verifyTrue(clearSelection.text()- ~/&/, expectedValue("order.search.clear.selection").toUpperCase())
		verifyTrue(hideSearch.text()- ~/&/, expectedValue("order.search.hide.search").toUpperCase())
		verifyTrue(searchButton.text()- ~/&/, expectedValue("search.meta.title").toUpperCase())	
		verifyTrue(ordersFoundLabel.text()- ~/(\d+,)?\d+\s+/,expectedValue("text.account.orderHistory.page.totalResults").toUpperCase())
		verifyTrue(ordersFoundLabel1.text()- ~/(\d+,)?\d+\s+/,expectedValue("text.account.orderHistory.page.totalResults").toUpperCase())
		verifyTrue(sortByLabel.text()- ~/:/, expectedValue("text.account.orderHistory.page.sortTitle").toUpperCase())
		verifyTrue(sortByLabel1.text()- ~/:/, expectedValue("text.account.orderHistory.page.sortTitle").toUpperCase())		
		verifyTrue(pageLink(0).text().replaceAll("\\s+\\d+",""), expectedValue("text.account.orderHistory.page.currentPage").toUpperCase())
		verifyTrue(pageLink(1).text().replaceAll("\\s+\\d+",""), expectedValue("text.account.orderHistory.page.currentPage").toUpperCase())		
		verifyTrue(datePlacedLabel.text() , expectedValue("text.account.orderHistory.datePlaced").toUpperCase())
		verifyTrue(orderNumberLabel.text(),expectedValue("text.account.orderHistory.orderNumber").toUpperCase())
		verifyTrue(orderStatusLabel.text(), expectedValue("text.account.orderHistory.orderStatus").toUpperCase())
		verifyTrue(orderTypeLabel.text(), expectedValue("text.account.orderHistory.orderType").toUpperCase())
		verifyTrue(totalLabel.text(),expectedValue("text.account.orderHistory.total").toUpperCase())
		verifyTrue(orderSourceLabel.text(),expectedValue("text.account.orderHistory.orderSource").toUpperCase())
		verifyTrue(invoiceLabel.text(), expectedValue("text.account.orderHistory.invoice").toUpperCase())	
		
		when: "MouseHover SortBy dropdown 1"
		sortByDropdown(0).jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(sortByDropdown(0)) } //Issue with Chrome

		then: "verify translations of Dropdown options"
		verifyTrue(sortByOptions(0).text()- ~/\s+/, expectedValue("text.account.orderHistory.page.sort.byDate"))
		verifyTrue(sortByOptions(1).text(), expectedValue("text.account.orderHistory.page.sort.byOrderNumber"))
		
		when: "MouseHover SortBy dropdown 1=2"
		sortByDropdown(1).jquery.mouseover()    //Issue with Firefox 35
		interact { moveToElement(sortByDropdown(1)) } //Issue with Chrome
		
		then: "verify translations of Dropdown options"
		verifyTrue(sortByOptions(2).text(), expectedValue("text.account.orderHistory.page.sort.byDate"))
		verifyTrue(sortByOptions(3).text(), expectedValue("text.account.orderHistory.page.sort.byOrderNumber"))
		
		when: "click on HIDE SEARCH"
		hideSearch.click()
		
		then: "verify translation of Search Order History Link"
		waitFor { searchOrderHistoryLink.displayed }
		verifyTrue(searchOrderHistoryLink.text()- ~/&/, expectedValue("search.order.history").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
