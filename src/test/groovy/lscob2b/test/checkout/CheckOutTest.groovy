package lscob2b.test.checkout

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.checkout.CheckOutPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProviderTest

class CheckOutTest extends PropertProviderTest{
	
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
		assert masterTemplate.mainContainerLabel.text()- ~/&/  == expectedValue("checkout.checkout").toUpperCase()
		assert masterTemplate.alertMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.alertMessage.text() == expectedValue("")
		
		assert paymentMethodHeader.text() == expectedValue("").toUpperCase()
		assert labelInvoice.text() == expectedValue("").toUpperCase()
		assert labelCardPayment.text() == expectedValue("").toUpperCase()
		assert labelPONumber.text() == expectedValue("").toUpperCase()
		
		assert labelSelectAddress.text() == expectedValue("").toUpperCase()
		assert labelAlternateAddress.text() == expectedValue("").toUpperCase()
		
		assert labelToBeConfirmed.text() == expectedValue("").toUpperCase()
		assert labelNoneSelected.text() == expectedValue("").toUpperCase()
		
		assert labelSubTotal(0).text()== expectedValue("")
		assert labelSubTotal(1).text()== expectedValue("text.quantity")
		assert labelSubTotal(2).text()== expectedValue("")
		assert labelTotal(0).text()== expectedValue("basket.page.total")
		assert labelTotal(1).text()== expectedValue("basket.page.total")
		assert continueShopping.text()- ~/&/ == expectedValue("label.continue.shopping").toUpperCase()
		assert placeOrderLink.text()- ~/&/ == expectedValue("").toUpperCase()
		assert placeOrderLink1.text()- ~/&/ == expectedValue("").toUpperCase()
		assert cartItems[0].itemStyle.text()== expectedValue("product.variants.style").toUpperCase()
		assert cartItems[0].itemColor.text()== expectedValue("product.variants.color").toUpperCase()
		assert cartItems[0].itemPrice.text()== expectedValue("product.wholesale.price").toUpperCase()
		assert cartItems[0].itemQuantity.text()== expectedValue("text.quantity").toUpperCase()
		assert cartItems[0].itemTotal.text()== expectedValue("basket.page.total").toUpperCase()
		assert cartItems[0].removeProductLink.text()- ~/&/== expectedValue("text.remove").toUpperCase()
		assert cartItems[0].editQuantities.text()- ~/&/== expectedValue("product.edit.quantities").toUpperCase()
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		then: "click on Edit Quantities"
		cartItems[0].editQuantities.click()
		
		and: "verify translations of Sizing Grid"
		assert cartItems[0].buttonHideQuantities.text()- ~/&/== expectedValue("product.hide.quantities").toUpperCase()
		waitFor { addToCartForm.displayed }
		assert cartItems[0].inStockLabel.text()== expectedValue("product.variants.in.stock").toUpperCase()
		assert cartItems[0].limitedStockLabel.text()== expectedValue("product.variants.limited.stock").toUpperCase()
		assert cartItems[0].outOfStockLabel.text()== expectedValue("product.variants.out.of.stock").toUpperCase()
		assert cartItems[0].buttonCancel.text()== expectedValue("cancelButton.displayName").toUpperCase()
		assert cartItems[0].buttonUpdate.text()== expectedValue("product.variants.update").toUpperCase()
		
		when: "at CheckOut Page"
		at CheckOutPage
		
		and: "click on remove"
		cartItems[0].removeProductLink.click()
		waitFor{ removeProducts.displayed }
		
		then: "verify translations of Remove PopUp"
		assert removePopUpHeader.text() == expectedValue("product.remove.confirmation").toUpperCase()
		assert removePopCancelButton.text()- ~/&/ == expectedValue("cancelButton.displayName").toUpperCase()
		assert removeProducts.text()- ~/&/ == expectedValue("text.remove").toUpperCase()

		and: "Remove product from cart"
		removeProducts.click()
		
		where:
		user | productCode | link
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0] | "checkout/single/summary"
		
	}
}
