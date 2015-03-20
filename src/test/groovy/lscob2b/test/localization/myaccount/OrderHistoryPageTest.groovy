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
		assert orderHistoryData.text() == expectedValue("text.account.orderHistory")
		//assert ordersFoundLabel.text()==expectedValue("text.account.orderHistory.page.totalResults")
		assert clearSelection.text()- ~/&/ ==expectedValue("order.search.clear.selection")
		//assert hideSearch ==expectedValue("order.search.hide.search")		
		assert poNumberLabel.text()== expectedValue("account.order.ponum")
		assert orderNumberLabel.text()==expectedValue("account.order.number")
		assert status.text() ==expectedValue("account.order.status")
		assert date.text()==expectedValue("account.order.date")
		assert type.text()==expectedValue("account.order.type")
		assert orderSource.text()==expectedValue("account.order.source")
		assert sortByLabel.text()== expectedValue("text.account.orderHistory.page.sortTitle")
		assert datePlacedLabel.text()  == expectedValue("text.account.orderHistory.datePlaced")
		assert orderNumberLabel.text()==expectedValue("text.account.orderHistory.orderNumber")
		assert orderStatusLabel.text() == expectedValue("text.account.orderHistory.orderStatus")
		assert orderTypeLabel.text() == expectedValue("text.account.orderHistory.orderType")
		assert totalLabel.text() ==expectedValue("text.account.orderHistory.total")
		assert orderSourceLabel.text() ==expectedValue("text.account.orderHistory.orderSource")
		assert invoiceLabel.text() == expectedValue("text.account.orderHistory.invoice")	
		
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}	
}
