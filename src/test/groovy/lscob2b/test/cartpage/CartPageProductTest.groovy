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
		assert itemStyle.text()== expectedValue("product.variants.style").toUpperCase()
		assert itemColor.text()== expectedValue("product.variants.color").toUpperCase()
		assert itemPrice.text()== expectedValue("product.wholesale.price").toUpperCase()
		assert itemQuantity.text()== expectedValue("text.quantity").toUpperCase()
		assert itemTotal.text()== expectedValue("basket.page.total").toUpperCase()
		assert removeProductLink.text()- ~/&/== expectedValue("text.remove").toUpperCase()
		assert editQuantities.text()- ~/&/== expectedValue("product.edit.quantities").toUpperCase()
		assert itemLabels(5).text()== expectedValue("text.quantity")
		assert itemLabels(6).text()== expectedValue("basket.page.total")
		assert linkCheckout.text()- ~/&/  == expectedValue("checkout.checkout").toUpperCase()
		assert continueShopping.text()- ~/&/ == expectedValue("label.continue.shopping").toUpperCase()
		
		when: "at cart page"
		at CartPage
		
		then: "click on Edit Quantities"
		editQuantities.click()
		
		and: "verify translations of Sizing Grid"
		assert buttonHideQuantities.text()- ~/&/== expectedValue("product.hide.quantities").toUpperCase()
		waitFor { addToCartForm.displayed }
		assert inStockLabel.text()== expectedValue("product.variants.in.stock").toUpperCase()
		assert limitedStockLabel.text()== expectedValue("product.variants.limited.stock").toUpperCase()
		assert outOfStockLabel.text()== expectedValue("product.variants.out.of.stock").toUpperCase()
		assert buttonCancel.text()== expectedValue("cancelButton.displayName").toUpperCase()
		assert buttonUpdate.text()== expectedValue("product.variants.update").toUpperCase()
		
		when: "at cart page"
		at CartPage
		
		and: "click on remove"
		removeProductLink.click()
		waitFor{ removeProducts.displayed }
		
		then: "verify translations of Remove PopUp"
		assert removePopUpHeader.text() == expectedValue("product.remove.confirmation").toUpperCase()
		assert removePopCancelButton.text()- ~/&/ == expectedValue("cancelButton.displayName").toUpperCase()
		assert removeProducts.text()- ~/&/ == expectedValue("text.remove").toUpperCase()

		and: "Remove the product from cart"
		removeProducts.click()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0]
	}
}