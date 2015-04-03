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
	
	def "Verify translations - Men"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at Home Page"
		at HomePage
		
		then:"click Men category link"
		masterTemplate.menCategory.click()
	
		when:"at Category Page"
		at ProductCategoryPage
		
		then: "Verify translations at CategoryPage"
		assert masterTemplate.breadCrumbs.text() == expectedValue("breadcrumb.home").toUpperCase()
		assert masterTemplate.breadCrumbHref("/categories").text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert categoryLink.text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert seasonalInitiativeLink.text() == expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase()
		assert shopByStyleLink.text() == expectedValue("categorylandingpage.shopbystyle").toUpperCase()
		assert shopByFitLink.text() == expectedValue("categorylandingpage.shopbyfit").toUpperCase()
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
	def "Verify translations - Women"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at Home Page"
		at HomePage
		
		then:"click Men category link"
		masterTemplate.womenCategory.click()
	
		when:"at Category Page"
		at ProductCategoryPage
		
		then: "Verify translations at CategoryPage"
		assert masterTemplate.breadCrumbs.text() == expectedValue("breadcrumb.home").toUpperCase()
		assert masterTemplate.breadCrumbHref("/categories").text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert categoryLink.text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert seasonalInitiativeLink.text() == expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase()
		assert shopByStyleLink.text() == expectedValue("categorylandingpage.shopbystyle").toUpperCase()
		assert shopByFitLink.text() == expectedValue("categorylandingpage.shopbyfit").toUpperCase()
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
