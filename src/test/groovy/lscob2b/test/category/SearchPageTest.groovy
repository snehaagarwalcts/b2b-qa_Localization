package lscob2b.test.category

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.category.ProductCategoryPage
import lscob2b.test.data.PropertProviderTest

class SearchPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of Search Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
		
		and: "search for a product"
		masterTemplate.searchForProduct()
		
		then:"at CategoryPage"
		at ProductCategoryPage
		
		then:"verify translations at Category Page"
		assert masterTemplate.mainContainerLabel.text().contains(expectedValue("search.page.searchText").toUpperCase())
		assert masterTemplate.breadCrumbs.text() == expectedValue("breadcrumb.home").toUpperCase()
		assert masterTemplate.breadCrumbActive.text() == expectedValue("search.page.breadcrumb").toUpperCase()
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
