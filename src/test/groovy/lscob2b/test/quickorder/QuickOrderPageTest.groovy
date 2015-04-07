package lscob2b.test.quickorder

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.test.data.PropertProviderTest

class QuickOrderPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - QuickOrderPage"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		
		when:"at QuickOrderPage"
		at QuickOrderPage
	
		then:"Verify translations at QuickOrderPage"
		assert breadCrumbLink.text() == expectedValue("search.page.breadcrumb").toUpperCase()
		assert masterTemplate.mainContainerLabel.text() == expectedValue("search.advanced").toUpperCase()
		assert advancedSearch(0).text() == expectedValue("search.advanced.keyword").toUpperCase()
		assert advancedSearch(1).text() == expectedValue("search.advanced.onlyproductids").toUpperCase()
		assert searchLink.text() == expectedValue("search.advanced.search").toUpperCase()
		assert blankSlateHeader.text() == expectedValue("search.advanced.how.to").toUpperCase()
		assert blankSlateContent.text()  == expectedValue("search.advanced.how.to.text")
		assert helpLink.text() == expectedValue("quick.order.help.link.label")
		assert quantityAndTotal(0).text() == expectedValue("text.quantity")
		assert quantityAndTotal(1).text() == expectedValue("basket.page.total")+ " "
		assert continueshoppingLink.text()- ~/&/ == expectedValue("cart.page.continue").toUpperCase()
		assert checkOutLink.text()- ~/&/  == expectedValue("checkout.checkout").toUpperCase()
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
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
		assert quantityAndTotal(0).text() == expectedValue("product.variants.style").toUpperCase()
		assert quantityAndTotal(1).text() ==expectedValue("product.variants.color").toUpperCase()
		assert continueshoppingLink.text()- ~/&/ == expectedValue("label.continue.shopping").toUpperCase()
		assert checkOutLink.text()- ~/&/  == expectedValue("checkout.checkout").toUpperCase()
		
		where:
		user | productCode
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	
	}
}

