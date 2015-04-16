package lscob2b.test.orderconfirmation

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.orderconfirmation.OrderConfirmationPage;
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProvider

class OrderConfirmationTest extends PropertProvider{
	
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
		doPlaceOrder()
		
		when: "at OrderConfirmation Page"
		at OrderConfirmationPage
		
		then: "verify translations of OrderConfirmationPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("order.confirmation.thanks").toUpperCase())
		verifyTrue(masterTemplate.noteMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","").replaceAll("’", ""), expectedValue("checkout.orderConfirmation.saperror").replaceAll("'", ""))
		verifyTrue(order.labelOrderNumber.text(), expectedValue("text.account.orderDetail.orderNumber").toUpperCase()- ~/:/)
		verifyTrueContains(order.labelOrderDesc.text(), expectedValue("order.copy.sent").toUpperCase(), "use contains()")
		verifyTrue(order.headerorderAddress.text(), expectedValue("order.detail.deliveryAddress").toUpperCase())
		verifyTrue(order.labelPaymentDetalis.text(), expectedValue("order.detail.paymentDetails").toUpperCase())
		verifyTrue(order.labelOrderPlacedBy.text()- ~/:/, expectedValue("checkout.orderConfirmation.orderPlacedBy").toUpperCase())
		verifyTrue(order.labelPONumber.text()- ~/:/, expectedValue("checkout.orderConfirmation.purchaseOrderNumber").toUpperCase()- ~/:/)
		verifyTrue(order.labelPaymentMethod.text(), expectedValue("text.payment.method").toUpperCase())
		verifyTrue(order.labelPaymentTerms.text(), expectedValue("text.payment.terms").toUpperCase())		
		verifyTrue(orderItems[0].itemStyle.text(), expectedValue("product.variants.style").toUpperCase())
		verifyTrue(orderItems[0].itemColor.text(), expectedValue("product.variants.color").toUpperCase())
		verifyTrue(orderItems[0].itemPrice.text(), expectedValue("product.wholesale.price").toUpperCase())
		verifyTrue(orderItems[0].itemQuantity.text(), expectedValue("text.quantity").toUpperCase())
		verifyTrue(orderItems[0].itemTotal.text(), expectedValue("basket.page.total").toUpperCase())
		verifyTrue(buttonShowQuantities.text()- ~/&/, expectedValue("text.account.orderDetail.entry.show.quantity").toUpperCase())
		verifyTrue(labelQuantity.text(), expectedValue("text.quantity"))
		verifyTrue(labelSubTotal.text(), expectedValue("product.grid.subtotalText"))
		verifyTrue(labelTotal.text(), expectedValue("basket.page.total"))
		verifyTrue(labelToBeConfirmed.text().replaceAll(labelTotal.text(), ""), expectedValue("order.tobeconfirmed"))
		
		then: "click on Show Quantities"
		buttonShowQuantities.click()
		waitFor { buttonHideQuantities.displayed }
		
		and: "verify translations of Order List Table"
		verifyTrue(buttonHideQuantities.text()- ~/&/, expectedValue("product.hide.quantities").toUpperCase())
		verifyTrue(labelLine.text(), expectedValue("text.account.orderDetail.entry.Line").toUpperCase())
		verifyTrue(labelProductCode.text(), expectedValue("text.account.orderDetail.entry.productCode").toUpperCase())
		verifyTrue(labelProductSize.text(), expectedValue("text.account.orderDetail.entry.productSize").toUpperCase())
		verifyTrue(labelQuantityOrdered.text(), expectedValue("text.account.orderDetail.entry.qtyOrdered").toUpperCase())		
		verifyTestFailedOrPassed()
		
		where:
		user | productCode | link
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0] | "checkout/single/summary"
	}
}
