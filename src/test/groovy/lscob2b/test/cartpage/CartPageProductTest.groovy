package lscob2b.test.cartpage

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.cart.CartPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProviderTest

class CartPageProductTest extends PropertProviderTest{
	
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
		
		and: "Go to Cart Page"
		masterTemplate.doGoToCart()
		
		when: "at cart page"
		at CartPage
		
		then: "verify translations of Cart Page"
		verifyTrue(cartItems[0].itemStyle.text(), expectedValue("product.variants.style").toUpperCase())
		verifyTrue(cartItems[0].itemColor.text(), expectedValue("product.variants.color").toUpperCase())
		verifyTrue(cartItems[0].itemPrice.text(), expectedValue("product.wholesale.price").toUpperCase())
		verifyTrue(cartItems[0].itemQuantity.text(), expectedValue("text.quantity").toUpperCase())
		verifyTrue(cartItems[0].itemTotal.text(), expectedValue("basket.page.total").toUpperCase())
		verifyTrue(cartItems[0].removeProductLink.text()- ~/&/, expectedValue("text.remove").toUpperCase())
		verifyTrue(cartItems[0].editQuantities.text()- ~/&/, expectedValue("product.edit.quantities").toUpperCase())
		verifyTrue(itemLabels(5).text(), expectedValue("text.quantity"))
		verifyTrue(itemLabels(6).text(), expectedValue("basket.page.total"))
		verifyTrue(linkCheckout.text()- ~/&/ , expectedValue("checkout.checkout").toUpperCase())
		verifyTrue(continueShopping.text()- ~/&/, expectedValue("label.continue.shopping").toUpperCase())
		
		when: "at cart page"
		at CartPage
		
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
		
		when: "at cart page"
		at CartPage
		
		and: "click on remove"
		cartItems[0].removeProductLink.click()
		waitFor{ removeProducts.displayed }
		
		then: "verify translations of Remove PopUp"
		verifyTrue(removePopUpHeader.text(), expectedValue("product.remove.confirmation").toUpperCase())
		verifyTrue(removePopCancelButton.text()- ~/&/, expectedValue("cancelButton.displayName").toUpperCase())
		verifyTrue(removeProducts.text()- ~/&/, expectedValue("text.remove").toUpperCase())

		and: "Remove the product from cart"
		removeProducts.click()
		verifyTestFailedOrPassed()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0]
	}
}