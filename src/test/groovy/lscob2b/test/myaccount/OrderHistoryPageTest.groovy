package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.OrderHistoryPage
import lscob2b.test.data.PropertProviderTest

class OrderHistoryPageTest extends PropertProviderTest {
		
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
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.orderHistory").toUpperCase()
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.orderHistory").toUpperCase()		
		assert status.text() ==expectedValue("text.account.orderHistory.orderStatus").toUpperCase()
		assert date.text()==expectedValue("account.order.date").toUpperCase()
		assert type.text()==expectedValue("account.order.type").toUpperCase()
		assert orderSource.text()==expectedValue("account.order.source").toUpperCase()
		assert poNumberLabel.text()== expectedValue("account.order.ponum").toUpperCase()
		assert orderNumberLabel.text()==expectedValue("account.order.number").toUpperCase()		
		assert orderSelectionLabels(0).text()==expectedValue("ordersearch.submitted").toUpperCase()
		assert orderSelectionLabels(1).text()==expectedValue("ordersearch.completed").toUpperCase()
		assert orderSelectionLabels(2).text()==expectedValue("ordersearch.inProgress").toUpperCase()
		assert orderSelectionLabels(3).text()==expectedValue("ordersearch.last30Days").toUpperCase()
		assert orderSelectionLabels(4).text()==expectedValue("ordersearch.last90Days").toUpperCase()
		assert orderSelectionLabels(5).text()==expectedValue("ordersearch.lastYear").toUpperCase()
		assert orderSelectionLabels(6).text()==expectedValue("ordersearch.atOnce").toUpperCase()
		assert orderSelectionLabels(7).text()==expectedValue("ordersearch.preBook").toUpperCase()
		assert orderSelectionLabels(8).text()==expectedValue("ordersearch.b2b").toUpperCase()
		assert orderSelectionLabels(9).text()==expectedValue("ordersearch.edi").toUpperCase()
		assert orderSelectionLabels(10).text()==expectedValue("ordersearch.sap").toUpperCase()
		assert orderSelectionLabels(11).text()==expectedValue("ordersearch.sfa").toUpperCase()
		assert orderSelectionLabels(12).text()==expectedValue("ordersearch.leo").toUpperCase()		
		assert clearSelection.text()- ~/&/ == expectedValue("order.search.clear.selection").toUpperCase()
		assert hideSearch.text()- ~/&/ == expectedValue("order.search.hide.search").toUpperCase()
		assert searchButton.text()- ~/&/ == expectedValue("search.meta.title").toUpperCase()	
		assert ordersFoundLabel.text() - ~/\d+\s+/ ==expectedValue("text.account.orderHistory.page.totalResults").toUpperCase()
		assert ordersFoundLabel1.text() - ~/\d+\s+/ ==expectedValue("text.account.orderHistory.page.totalResults").toUpperCase()
		assert sortByLabel.text()- ~/:/== expectedValue("text.account.orderHistory.page.sortTitle").toUpperCase()
		assert sortByLabel1.text()- ~/:/== expectedValue("text.account.orderHistory.page.sortTitle").toUpperCase()		
		assert pageLink(0).text().replaceAll("\\s+\\d+","") == expectedValue("text.account.orderHistory.page.currentPage").toUpperCase()
		assert pageLink(1).text().replaceAll("\\s+\\d+","")== expectedValue("text.account.orderHistory.page.currentPage").toUpperCase()		
		assert datePlacedLabel.text()  == expectedValue("text.account.orderHistory.datePlaced").toUpperCase()
		assert orderNumberLabel.text()==expectedValue("text.account.orderHistory.orderNumber").toUpperCase()
		assert orderStatusLabel.text() == expectedValue("text.account.orderHistory.orderStatus").toUpperCase()
		assert orderTypeLabel.text() == expectedValue("text.account.orderHistory.orderType").toUpperCase()
		assert totalLabel.text() ==expectedValue("text.account.orderHistory.total").toUpperCase()
		assert orderSourceLabel.text() ==expectedValue("text.account.orderHistory.orderSource").toUpperCase()
		assert invoiceLabel.text() == expectedValue("text.account.orderHistory.invoice").toUpperCase()	
		
		when: "MouseHover SortBy dropdown 1"
		sortByDropdown(0).jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(sortByDropdown(0)) } //Issue with Chrome

		then: "verify translations of Dropdown options"
		assert sortByOptions(0).text()- ~/\s+/== expectedValue("text.account.orderHistory.page.sort.byDate")
		assert sortByOptions(1).text()== expectedValue("text.account.orderHistory.page.sort.byOrderNumber")
		
		when: "MouseHover SortBy dropdown 1=2"
		sortByDropdown(1).jquery.mouseover()    //Issue with Firefox 35
		interact { moveToElement(sortByDropdown(1)) } //Issue with Chrome
		
		then: "verify translations of Dropdown options"
		assert sortByOptions(2).text()== expectedValue("text.account.orderHistory.page.sort.byDate")
		assert sortByOptions(3).text()== expectedValue("text.account.orderHistory.page.sort.byOrderNumber")
		
		when: "click on HIDE SEARCH"
		hideSearch.click()
		
		then: "verify translation of Search Order History Link"
		waitFor { searchOrderHistoryLink.displayed }
		assert searchOrderHistoryLink.text()- ~/&/== expectedValue("search.order.history").toUpperCase()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
