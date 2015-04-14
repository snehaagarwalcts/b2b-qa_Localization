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
		verifyTrue(masterTemplate.breadCrumbs.text(), expectedValue("breadcrumb.home").toUpperCase())
		verifyTrue(breadCrumbLink.text(), expectedValue("categorylandingpage.categories").toUpperCase())
		//verifyTrue(keylookHeroTitle.text(), expectedValue("").toUpperCase()) //No translation available
		for(item in keylookItems) {
			verifyTrue(item.style.text(), expectedValue("product.variants.style").toUpperCase())
			verifyTrue(item.color.text(), expectedValue("product.variants.color").toUpperCase())
			verifyTrue(item.priceWholesale.text(), expectedValue("product.wholesale.price").toUpperCase())
			verifyTrue(item.inStockLabel.text(), expectedValue("product.variants.in.stock").toUpperCase())
			verifyTrue(item.limitedStockLabel.text(), expectedValue("product.variants.limited.stock").toUpperCase())
			verifyTrue(item.outOfStockLabel.text(), expectedValue("product.variants.out.of.stock").toUpperCase())	
		}	
		verifyTrue(labelQuantity.text(), expectedValue("text.quantity"))
		verifyTrue(labelTotal.text(), expectedValue("basket.page.total"))
		verifyTrue(continueShopping.text()- ~/ &/, expectedValue("label.continue.shopping").toUpperCase())
		verifyTrue(buttonAddToCart.text()- ~/&/, expectedValue("basket.add.to.cart").toUpperCase())
		verifyTestFailedOrPassed()
			
		where:
			user | link
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | "keylook/levisKeyLook1" 
	}			
}
