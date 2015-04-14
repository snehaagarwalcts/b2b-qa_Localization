package lscob2b.test.orderconfirmation

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.orderconfirmation.OrderConfirmationPage;
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProviderTest

class OrderConfirmationTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of Cart Page with products in Cart"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
		
		when: "at Product Details Page"
		at ProductDetailsPage
		
		then: "Add product to cart"
		waitFor { !sizingGrid.empty }
		sizingGrid.addLimitedStockQuantityToCart(1)
		
		and: "go to checkout page"
		browser.go(baseUrl + link)
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		then: "select invoice as Payment Method"
		invoicePayment.click()
		
		and: "click on PLACE ORDER button"
		placeOrderLink.click()
		
		when: "at OrderConfirmation Page"
		at OrderConfirmationPage
		
		then: "verify translations of OrderConfirmationPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("order.confirmation.thanks").toUpperCase())
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n",""), expectedValue("checkout.orderConfirmation.saperror"))

		verifyTestFailedOrPassed()
		
		where:
		user | productCode | link
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0] | "checkout/single/summary"
	}
}
