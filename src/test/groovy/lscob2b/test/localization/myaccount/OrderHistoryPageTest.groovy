package lscob2b.test.localization.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.myaccount.MyAccountPage;
import lscob2b.pages.myaccount.OrderHistoryPage;
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
		assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.orderHistory")
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.account.orderHistory")		
		assert status.text() ==expectedValue("account.order.status")
		assert date.text()==expectedValue("account.order.date")
		assert type.text()==expectedValue("account.order.type")
		assert orderSource.text()==expectedValue("account.order.source")
		assert poNumberLabel.text()== expectedValue("account.order.ponum")
		assert orderNumberLabel.text()==expectedValue("account.order.number")		
		assert orderSelectionLabels(0).text()==expectedValue("ordersearch.submitted")
		assert orderSelectionLabels(1).text()==expectedValue("ordersearch.completed")
		assert orderSelectionLabels(2).text()==expectedValue("ordersearch.inProgress")
		assert orderSelectionLabels(3).text()==expectedValue("ordersearch.last30Days")
		assert orderSelectionLabels(4).text()==expectedValue("ordersearch.last90Days")
		assert orderSelectionLabels(5).text()==expectedValue("ordersearch.lastYear")
		assert orderSelectionLabels(6).text()==expectedValue("ordersearch.atOnce")
		assert orderSelectionLabels(7).text()==expectedValue("ordersearch.preBook")
		assert orderSelectionLabels(8).text()==expectedValue("ordersearch.b2b")
		assert orderSelectionLabels(9).text()==expectedValue("ordersearch.edi")
		assert orderSelectionLabels(10).text()==expectedValue("ordersearch.sap")
		assert orderSelectionLabels(11).text()==expectedValue("ordersearch.sfa")
		assert orderSelectionLabels(12).text()==expectedValue("ordersearch.leo")		
		assert clearSelection.text()- ~/&/ == expectedValue("order.search.clear.selection")
		assert hideSearch.text()- ~/&/ == expectedValue("order.search.hide.search")
		assert searchButton.text()- ~/&/ == expectedValue("search.meta.title")	
		assert ordersFoundLabel.text() - ~/\d+\s+/ ==expectedValue("text.account.orderHistory.page.totalResults")
		assert ordersFoundLabel1.text() - ~/\d+\s+/ ==expectedValue("text.account.orderHistory.page.totalResults")
		assert sortByLabel.text()== expectedValue("text.account.orderHistory.page.sortTitle")
		assert sortByLabel1.text()== expectedValue("text.account.orderHistory.page.sortTitle")		
		sortByDropdown(0).jquery.mouseover()
		assert sortByOptions(0).text()- ~/\s+/== expectedValue("text.account.orderHistory.page.sort.byDate")
		assert sortByOptions(1).text()== expectedValue("text.account.orderHistory.page.sort.byOrderNumber")
		sortByDropdown(1).jquery.mouseover()
		assert sortByOptions(2).text()== expectedValue("text.account.orderHistory.page.sort.byDate")
		assert sortByOptions(3).text()== expectedValue("text.account.orderHistory.page.sort.byOrderNumber")	
		assert pageLink(0).text().replaceAll("\\s+\\d+","") == expectedValue("text.account.orderHistory.page.currentPage")
		assert pageLink(1).text().replaceAll("\\s+\\d+","")== expectedValue("text.account.orderHistory.page.currentPage")		
		assert datePlacedLabel.text()  == expectedValue("text.account.orderHistory.datePlaced")
		assert orderNumberLabel.text()==expectedValue("text.account.orderHistory.orderNumber")
		assert orderStatusLabel.text() == expectedValue("text.account.orderHistory.orderStatus")
		assert orderTypeLabel.text() == expectedValue("text.account.orderHistory.orderType")
		assert totalLabel.text() ==expectedValue("text.account.orderHistory.total")
		assert orderSourceLabel.text() ==expectedValue("text.account.orderHistory.orderSource")
		assert invoiceLabel.text() == expectedValue("text.account.orderHistory.invoice")	
		
		when: "click on HIDE SEARCH"
		hideSearch.click()
		
		then: "verify translation of Search Order History Link"
		waitFor { searchOrderHistoryLink.displayed }
		assert searchOrderHistoryLink.text()- ~/&/== expectedValue("search.order.history")
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
