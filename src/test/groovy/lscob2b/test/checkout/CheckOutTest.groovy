package lscob2b.test.checkout

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProvider

class CheckOutTest extends PropertProvider{
	
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
		
		then: "verify translations of CheckOut Page"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("checkout.checkout").toUpperCase())
		//verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		//verifyTrue(masterTemplate.alertMessage.text(), expectedValue("checkout.orderConfirmation.saperror"))	
		verifyTrue(paymentMethodHeader.text(), expectedValue("checkout.summary.select.payment.method").toUpperCase())
		verifyTrue(labelInvoice.text(), expectedValue("text.account.orderHistory.invoice").toUpperCase())
		//verifyTrue(labelCardPayment.text(), expectedValue("").toUpperCase()) //Not present in Excel
		verifyTrue(labelPONumber.text(), expectedValue("checkout.summary.purchaseOrderNumber").toUpperCase())		
		verifyTrue(labelSelectAddress.text(), expectedValue("select.delivery.address").toUpperCase())
		//verifyTrue(labelNoneSelected.text(), expectedValue("checkout.summary.deliveryAddress.noneSelected").toUpperCase())
		verifyTrue(alternateAddressLink.text()- ~/ &/, expectedValue("checkout.summary.deliveryAddress.editDeliveryAddressButton").toUpperCase())		
		verifyTrue(labelToBeConfirmed(0).text().replaceAll(labelTotal(0).text()+"\n", ""), expectedValue("order.tobeconfirmed").toUpperCase())
		verifyTrue(labelToBeConfirmed(2).text().replaceAll(labelTotal(2).text()+" ", ""), expectedValue("order.tobeconfirmed"))
		verifyTrue(labelSubTotal(0).text(), expectedValue("product.grid.subtotalText").toUpperCase())
		verifyTrue(labelSubTotal(1).text(), expectedValue("text.quantity"))
		verifyTrue(labelSubTotal(2).text(), expectedValue("product.grid.subtotalText"))
		verifyTrue(labelTotal(0).text(), expectedValue("basket.page.total").toUpperCase())
		verifyTrue(labelTotal(2).text(), expectedValue("basket.page.total"))
		verifyTrue(continueShopping.text()- ~/&/, expectedValue("label.continue.shopping").toUpperCase())
		verifyTrue(placeOrderLink.text()- ~/&/, expectedValue("checkout.summary.placeOrder").toUpperCase())
		verifyTrue(placeOrderLink1.text()- ~/&/, expectedValue("checkout.summary.placeOrder").toUpperCase())
		verifyTrue(cartItems[0].itemStyle.text(), expectedValue("product.variants.style").toUpperCase())
		verifyTrue(cartItems[0].itemColor.text(), expectedValue("product.variants.color").toUpperCase())
		verifyTrue(cartItems[0].itemPrice.text(), expectedValue("product.wholesale.price").toUpperCase())
		verifyTrue(cartItems[0].itemQuantity.text(), expectedValue("text.quantity").toUpperCase())
		verifyTrue(cartItems[0].itemTotal.text(), expectedValue("basket.page.total").toUpperCase())
		verifyTrue(cartItems[0].removeProductLink.text()- ~/&/, expectedValue("text.remove").toUpperCase())
		verifyTrue(cartItems[0].editQuantities.text()- ~/&/, expectedValue("product.edit.quantities").toUpperCase())
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		then: "click on Select Delivery Address"
		alternateAddressLink.click()
		
		and: "verify translations of Delivery Address pop up"
		waitFor { headerAddressBook.displayed }
		//verifyTrue(headerAddressBook.text(), expectedValue("text.account.addressBook").toUpperCase()) //FAILED
		verifyTrue(buttonUseThisAddress(0).text(), expectedValue("checkout.summary.deliveryAddress.useThisAddress").toUpperCase())
		verifyTrue(buttonUseThisAddress(1).text(), expectedValue("checkout.summary.deliveryAddress.useThisAddress").toUpperCase())
						
		when: "at CheckOut Page"
		at CheckOutPage
		
		then: "click on Edit Quantities"
		cartItems[0].editQuantities.click()
		
		and: "verify translations of Sizing Grid"
		verifyTrue(cartItems[0].buttonHideQuantities.text()- ~/&/, expectedValue("product.hide.quantities").toUpperCase())
		waitFor { addToCartForm.displayed }
		verifyTrue(cartItems[0].inStockLabel.text(), expectedValue("product.variants.in.stock").toUpperCase())
		verifyTrue(cartItems[0].limitedStockLabel.text(), expectedValue("product.variants.limited.stock").toUpperCase())
		verifyTrue(cartItems[0].outOfStockLabel.text(), expectedValue("product.variants.out.of.stock").toUpperCase())
		verifyTrue(cartItems[0].buttonCancel.text(), expectedValue("cancelButton.displayName").toUpperCase())
		verifyTrue(cartItems[0].buttonUpdate.text(), expectedValue("product.variants.update").toUpperCase())
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		and: "click on place order with payment type as CARD PAYMENT"
		placeOrderLink.click()
		
		then: "verify translations of alert message"
		verifyTrue(masterTemplate.alertMessageHeader.text(), expectedValue("text.please.note").toUpperCase())
		verifyTrue(masterTemplate.alertMessage.text(), expectedValue("checkout.error.payment.cybersource"))
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		and: "click on remove"
		cartItems[0].removeProductLink.click()
		
		then: "verify translations of Remove PopUp"
		waitFor{ removePopUpHeader.displayed }
		verifyTrue(removePopUpHeader.text(), expectedValue("product.remove.confirmation").toUpperCase())
		verifyTrue(removePopCancelButton.text()- ~/&/, expectedValue("cancelButton.displayName").toUpperCase())
		verifyTrue(removeProducts.text()- ~/&/, expectedValue("text.remove").toUpperCase())

		and: "Remove product from cart"
		removeProducts.click()
		verifyTestFailedOrPassed()
		
		where:
		user | productCode | link
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0] | "checkout/single/summary"
		
	}
}
