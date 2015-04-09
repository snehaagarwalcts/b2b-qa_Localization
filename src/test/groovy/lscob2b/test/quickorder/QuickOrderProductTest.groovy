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
		
		and: "check QuickOrder page verify translations"
		assert breadCrumbLink.text() == expectedValue("search.page.breadcrumb").toUpperCase()
		assert masterTemplate.mainContainerLabel.text() == expectedValue("search.advanced").toUpperCase()
		assert advancedSearch(0).text() == expectedValue("search.advanced.keyword").toUpperCase()
		assert advancedSearch(1).text() == expectedValue("search.advanced.onlyproductids").toUpperCase()
		assert searchLink.text() == expectedValue("search.advanced.search").toUpperCase()
		assert continueshoppingLink.text()- ~/&/ == expectedValue("cart.page.continue").toUpperCase()
		assert checkOutLink.text()- ~/&/  == expectedValue("checkout.checkout").toUpperCase()
		assert buttonAdd.text() == expectedValue("search.advanced.productids.add").toUpperCase()
		assert productsFoundlabel.text() - ~/\d+\s+/ == expectedValue("search.page.totalResults").toUpperCase()
		assert itemLabels(0).text() == expectedValue("product.variants.style").toUpperCase()
		assert itemLabels(1).text() == expectedValue("product.variants.color").toUpperCase()
		assert itemLabels(2).text() == expectedValue("product.wholesale.price").toUpperCase()
		assert itemLabels(3).text() == expectedValue("text.quantity").toUpperCase()
		assert itemLabels(4).text() ==expectedValue("basket.page.total").toUpperCase()
		assert itemLabels(5).text() == expectedValue("text.quantity")
		assert itemLabels(6).text() ==expectedValue("basket.page.total")+ " "
		assert hideQuantityButton.text()- ~/&/ == expectedValue("product.hide.quantities").toUpperCase()
		assert enterSizeLabel.text() == expectedValue("product.variants.select.size").toUpperCase()
		assert sizeGuideLabel.text() == expectedValue("product.variants.size.guide")
		assert inStockLabel.text() == expectedValue("product.variants.in.stock").toUpperCase()
		assert limitedStockLabel.text() == expectedValue("product.variants.limited.stock").toUpperCase()
		assert outOfStockLabel.text() == expectedValue("product.variants.out.of.stock").toUpperCase()
		assert productSizingGrids[0].buttonNotifyMe.text()- ~/&/ == expectedValue("basket.add.to.waitlist").toUpperCase()
		assert productSizingGrids[0].buttonAddToCart.text()- ~/&/ == expectedValue("basket.add.to.basket").toUpperCase()
		
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
		assert editQuantityButton.text()- ~/&/ == expectedValue("product.edit.quantities").toUpperCase()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
	
}
