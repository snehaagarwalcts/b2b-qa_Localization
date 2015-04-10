package lscob2b.test.category

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.category.ProductCategoryPage
import lscob2b.test.data.PropertProviderTest

class CategoryPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of Category Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		PageHelper.gotoPageCategory(browser, baseUrl)
		
		when:"at CategoryPage"
		at ProductCategoryPage
		
		then:"verify translations at Category Page"
		assert masterTemplate.breadCrumbs.text() == expectedValue("breadcrumb.home").toUpperCase()
		assert breadCrumbLink.text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert sortByLabel.text()- ~/:/ == expectedValue("search.page.sortTitle").toUpperCase()
		assert productsFoundLabel.text() - ~/\d+\s+/ == expectedValue("search.page.totalResults").toUpperCase()
		//assert pageOfLabel.text().replaceAll("\\s+\\d+","") == expectedValue("search.page.currentPage").toUpperCase()
		assert refinementsLabel.text() == expectedValue("search.nav.refinements").toUpperCase()
		
		for(item in pdpItems) {
			assert item.WholeSalePrice.text() == expectedValue("product.wholesale.price").toUpperCase()
		}	
		
		for(facetTitle in facetHeadLabels) {
			assert facetTitle.text().contains(expectedValue("search.nav.facetTitle").toUpperCase())
		}
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
