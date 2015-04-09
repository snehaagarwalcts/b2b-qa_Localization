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
		//assert
		
		and: "Remove the product from cart"
		doRemove()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getQuickOrderProduct(ProductHelper.BRAND_LEVIS)[0]
	}
}