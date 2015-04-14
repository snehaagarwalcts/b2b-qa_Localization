package lscob2b.test.quickorder

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.PropertProviderTest

class QuickOrderProductTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	def "Verify translations after product search at QuickOrderPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		
		when: "At QuickOrder page"
		at QuickOrderPage

		and: "Search for product by id"
		doSearch(productCode,true)

		then: "at QuickOrder page"
		at QuickOrderPage
		
		and: "verify translations of QuickOrder Page "
		verifyTrue(breadCrumbLink.text(), expectedValue("search.page.breadcrumb").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("search.advanced").toUpperCase())
		verifyTrue(advancedSearch(0).text(), expectedValue("search.advanced.keyword").toUpperCase())
		verifyTrue(advancedSearch(1).text(), expectedValue("search.advanced.onlyproductids").toUpperCase())
		verifyTrue(searchLink.text(), expectedValue("search.advanced.search").toUpperCase())
		verifyTrue(continueshoppingLink.text()- ~/&/, expectedValue("cart.page.continue").toUpperCase())
		verifyTrue(checkOutLink.text()- ~/&/ , expectedValue("checkout.checkout").toUpperCase())
		verifyTrue(buttonAdd.text(), expectedValue("search.advanced.productids.add").toUpperCase())
		verifyTrue(productsFoundlabel.text() - ~/\d+\s+/, expectedValue("search.page.totalResults").toUpperCase())
		verifyTrue(itemLabels(0).text(), expectedValue("product.variants.style").toUpperCase())
		verifyTrue(itemLabels(1).text(), expectedValue("product.variants.color").toUpperCase())
		verifyTrue(itemLabels(2).text(), expectedValue("product.wholesale.price").toUpperCase())
		verifyTrue(itemLabels(3).text(), expectedValue("text.quantity").toUpperCase())
		verifyTrue(itemLabels(4).text(),expectedValue("basket.page.total").toUpperCase())
		verifyTrue(itemLabels(5).text(), expectedValue("text.quantity"))
		verifyTrue(itemLabels(6).text(),expectedValue("basket.page.total")+ " ")
		verifyTrue(hideQuantityButton.text()- ~/&/, expectedValue("product.hide.quantities").toUpperCase())
		verifyTrue(enterSizeLabel.text(), expectedValue("product.variants.select.size").toUpperCase())
		verifyTrue(sizeGuideLabel.text(), expectedValue("product.variants.size.guide"))
		verifyTrue(inStockLabel.text(), expectedValue("product.variants.in.stock").toUpperCase())
		verifyTrue(limitedStockLabel.text(), expectedValue("product.variants.limited.stock").toUpperCase())
		verifyTrue(outOfStockLabel.text(), expectedValue("product.variants.out.of.stock").toUpperCase())
		verifyTrue(productSizingGrids[0].buttonNotifyMe.text()- ~/&/, expectedValue("basket.add.to.waitlist").toUpperCase())
		verifyTrue(productSizingGrids[0].buttonAddToCart.text()- ~/&/, expectedValue("basket.add.to.cart").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
	
	def "Verify translation for Edit Quantity button at QuickOrderPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		
		when: "At QuickOrder page"
		at QuickOrderPage

		then: "Search for product by id"
		doSearch(productCode,true)

		when: "at QuickOrder page"
		at QuickOrderPage
		
		then: "click on HIDE QUANTITIES button"
		hideQuantityButton.click()
		
		and: "check translation of EDIT QUANTITIES button"
		verifyTrue(editQuantityButton.text()- ~/&/, expectedValue("product.edit.quantities").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
	
}
