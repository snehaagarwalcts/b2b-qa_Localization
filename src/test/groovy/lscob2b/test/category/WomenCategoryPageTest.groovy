package lscob2b.test.category

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.category.ProductCategoryPage
import lscob2b.test.data.PropertProvider

class WomenCategoryPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
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
		verifyTrue(masterTemplate.breadCrumbs.text(), expectedValue("breadcrumb.home").toUpperCase())
		verifyTrue(masterTemplate.breadCrumbHref("/categories").text(), expectedValue("categorylandingpage.categories").toUpperCase())
		verifyTrue(categoryLink.text(), expectedValue("categorylandingpage.categories").toUpperCase())
		verifyTrue(seasonalInitiativeLink.text(), expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase())
		verifyTrue(shopByStyleLink.text(), expectedValue("categorylandingpage.shopbystyle").toUpperCase())
		verifyTrue(shopByFitLink.text(), expectedValue("categorylandingpage.shopbyfit").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
