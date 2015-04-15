package lscob2b.test.category

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.category.ProductCategoryPage
import lscob2b.test.data.PropertProvider

class SearchPageTest extends PropertProvider{
	
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
		verifyTrueContains(masterTemplate.mainContainerLabel.text(), expectedValue("search.page.searchText").toUpperCase(), "use contains()")
		verifyTrue(masterTemplate.breadCrumbs.text(), expectedValue("breadcrumb.home").toUpperCase())
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("search.page.breadcrumb").toUpperCase())
		verifyTrue(sortByLabel.text()- ~/:/, expectedValue("search.page.sortTitle").toUpperCase())
		verifyTrue(productsFoundLabel.text() - ~/\d+\s+/, expectedValue("search.page.totalResults").toUpperCase())
		//verifyTrue(pageOfLabel.text().replaceAll("\\s+\\d+",""), expectedValue("search.page.currentPage").toUpperCase())
		verifyTrue(refinementsLabel.text(), expectedValue("search.nav.refinements").toUpperCase())		
		for(item in pdpItems) {
			verifyTrue(item.WholeSalePrice.text(), expectedValue("product.wholesale.price").toUpperCase())
		}		
		for(facetTitle in facetHeadLabels) {
			verifyTrueContains(facetTitle.text(), expectedValue("search.nav.facetTitle").toUpperCase(), "use contains()")
		}
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
