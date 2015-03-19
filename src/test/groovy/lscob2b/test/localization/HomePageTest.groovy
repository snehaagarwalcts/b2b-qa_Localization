package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest

class HomePageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations - Home Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
	
		then:"Verify translations at HomePage"
		assert masterTemplate.quickOrderLink.text() == expectedValue("search.advanced.title")
		assert masterTemplate.searchText == expectedValue("search.placeholder")
		masterTemplate.menCategory.jquery.mouseover()
		assert masterTemplate.subCategory(0).text() == expectedValue("categorylandingpage.categories")
		assert masterTemplate.subCategory(1).text() == expectedValue("categorylandingpage.seasonalinitiatives")
		masterTemplate.womenCategory.jquery.mouseover()
		assert masterTemplate.subCategory(2).text() == expectedValue("categorylandingpage.categories")
		assert masterTemplate.subCategory(3).text() == expectedValue("categorylandingpage.seasonalinitiatives")
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
