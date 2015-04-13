package lscob2b.test.category

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.pages.category.KeyLookPage
import lscob2b.test.data.PropertProviderTest

class KeyLookTest extends PropertProviderTest{

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
	}
	
	/**
	 * BB-648 Automated test case: Key Look page
	 */
	def "Test Key-Look page"() {
		setup:
		to LoginPage
		login(user)		
		browser.go(baseUrl + link)
											
		when: "At KeyLook page"
		at KeyLookPage
		
		then: "verify translations of KeyLook Page"
		assert masterTemplate.breadCrumbs.text() == expectedValue("breadcrumb.home").toUpperCase()
		assert breadCrumbLink.text() == expectedValue("categorylandingpage.categories").toUpperCase()
		//assert keylookHeroTitle.text() == expectedValue("").toUpperCase() //No translation available
		for(item in keylookItems) {
			assert item.style.text() == expectedValue("product.variants.style").toUpperCase()
			assert item.color.text() == expectedValue("product.variants.color").toUpperCase()
			assert item.priceWholesale.text() == expectedValue("product.wholesale.price").toUpperCase()
			assert item.inStockLabel.text()== expectedValue("product.variants.in.stock").toUpperCase()
			assert item.limitedStockLabel.text()== expectedValue("product.variants.limited.stock").toUpperCase()
			assert item.outOfStockLabel.text()== expectedValue("product.variants.out.of.stock").toUpperCase()	
		}	
		assert labelQuantity.text()== expectedValue("text.quantity")
		assert labelTotal.text()== expectedValue("basket.page.total")
		assert continueShopping.text()- ~/ &/ == expectedValue("label.continue.shopping").toUpperCase()
		assert buttonAddToCart.text()- ~/&/ == expectedValue("basket.add.to.cart").toUpperCase()
			
		where:
			user | link
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | "en/keylook/levisKeyLook1" 
	}			
}
