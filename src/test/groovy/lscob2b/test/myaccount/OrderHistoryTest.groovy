package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.myaccount.OrderDetailPage
import lscob2b.pages.myaccount.OrderHistoryPage
import lscob2b.pages.orderconfirmation.OrderConfirmationPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.Ignore
import spock.lang.IgnoreRest

class OrderHistoryTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}

	def placeAnOrder(String productCode) {
		//Order Detail
		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage

		//Add To Cart
		addOrderQuantity("1")
		sizingGrid.addToCart()
		masterTemplate.goToCartLink.click()

		//Cart
		at CartPage
		linkCheckout.click()

		//Checkout
		at CheckOutPage
		doPlaceOrder()

		//Order Details
		at OrderConfirmationPage
		return order.getOrder()
	}

	def goToOrderHistory() {
		browser.go(baseUrl + "my-account/orders")
		at OrderHistoryPage
	}

	def goToOrderDetail(orderNumber) {
		browser.go(baseUrl + "my-account/order/" + orderNumber)
		at OrderDetailPage
	}

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}

	/**
	 * Bug BB-604 Security Issue on "my-account/orders"
	 */
	//FIXME IE problem
	
	def "Check access to OrderHistory for [b2bcustomergroup]"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Go to my-account/orders"
		browser.go(baseUrl + "my-account/orders")
		at OrderHistoryPage

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}

	/**
	 * Bug BB-604 Security Issue on "my-account/orders"
	 */
   //FIXME IE problem
	
	def "Check denied access to OrderHistory for not [b2bcustomergroup]"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Go to my-account/orders"
		browser.go(baseUrl + "my-account/orders")
		at HomePage

		where:
		user | _
		TestDataCatalog.getUserNotInGroups([
			TestDataCatalog.CUSTOMER_GROUP,
			TestDataCatalog.ADMIN_GROUP
		]) | _
		TestDataCatalog.getUserNotInGroups([
			TestDataCatalog.CUSTOMER_GROUP,
			TestDataCatalog.FINANCE_GROUP
		]) | _
	}

	//FIXME Safari issue
	def "Test clear functionality"() {
		setup:
		login(user)
		goToOrderHistory()

		when: "At OrderHistory Page"
		at OrderHistoryPage

		then: "Click on all field"
		switchOnForm()

		and: "Clear form"
		clearButton.click()

		and: "Check form status"
		isFormClear()

		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}


	//TODO NOTE can't run last 3 tests as we shouldn't place an order
	
	//FIXME Safari issue
	def "Test order creation in history"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Create a test order"
		def currentOrder = placeAnOrder(productCode)
		goToOrderHistory()

		when: "At OrderHistory page"
		at OrderHistoryPage

		then: "Check order in history"
		searchByOrderNumber(currentOrder.number)
		checkUniqueResult()

		and: "Go to first order detail"
		clickOnFirstOrder()

		when: "At OrderDetail page"
		at OrderDetailPage

		then: "Compare Orders"
		currentOrder.compare(order.getOrder())

		where:
		productCode | user | _
		"05527-0458" |	UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}

	/**
	 * TC BB-509 Automated test: User Can reorder from history page
	 */
	//FIXME Safari issue
	def "Test re-order functionality in history"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Create a test order"
		def currentOrder = placeAnOrder(productCode)
		goToOrderDetail(currentOrder.number)

		when: "At OrderDetail page"
		at OrderDetailPage

		then: "Do a re-order"
		linkReOrder.click()

		when: "At checkout page"
		at CheckOutPage

		then: "Place the re-order"
		doPlaceOrder()

		when: "At OrderConfirmation page"
		at OrderConfirmationPage

		then: "Compare orders"
		currentOrder.compareWithoutNumber(order.getOrder())

		where:
		productCode | user | _
		"05527-0458" |	UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _

	}

	/**
	 * TC BB-601 Automated Test: Order Search
	 */
	//FIXME Safari issue
	def "Test search functionality in history"() {
		setup:
		login(user)

		when: "At HomePage"
		at HomePage

		then: "Create a test order"
		def currentOrder = placeAnOrder(productCode)
		goToOrderHistory()

		when: "At OrderHistory page"
		at OrderHistoryPage

		then: "Test search by: order number"
		searchByOrderNumber(currentOrder.number)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order source b2b"
		searchByOrderNumberAndOrderSource(currentOrder.number, true, false, false, false, false)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order source not b2b"
		searchByOrderNumberAndOrderSource(currentOrder.number, false, true, true, true, true)
		checkEmptyResult()
		clearForm()

		and: "Test search by: order number and order type at once"
		searchByOrderNumberAndOrderType(currentOrder.number, true, false)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order type pre book"
		searchByOrderNumberAndOrderType(currentOrder.number, false, true)
		checkEmptyResult()
		clearForm()

		and: "Test search by: order number and order date last 30d"
		searchByOrderNumberAndOrderDate(currentOrder.number, true, false, false)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order date last 90d"
		searchByOrderNumberAndOrderDate(currentOrder.number, false, true, false)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order date last year"
		searchByOrderNumberAndOrderDate(currentOrder.number, false, false, true)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order status submitted"
		searchByOrderNumberAndOrderStatus(currentOrder.number, true, false, false)
		checkUniqueResult()
		clearForm()

		and: "Test search by: order number and order status not submitted"
		searchByOrderNumberAndOrderStatus(currentOrder.number, false, true, true)
		checkEmptyResult()
		clearForm()

		where:
		productCode | user | _
		"05527-0458" |	UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
	}


}
