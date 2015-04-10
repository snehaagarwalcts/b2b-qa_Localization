package lscob2b.test.landingpage

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
		assert masterTemplate.quickOrderLink.text() == expectedValue("search.advanced").toUpperCase()
		assert masterTemplate.searchText == expectedValue("search.placeholder")
		assert newsAndTipsLabel.text() == expectedValue("landingpage.newsandtips").toUpperCase()
		assert quickOrderButton.text()- ~/</ == expectedValue("search.advanced.meta.description.title").toUpperCase()
		assert uploadOrderButton.text()- ~/</ == expectedValue("text.order.upload").toUpperCase()
		
		when: "MouseHover Men Category"	
		masterTemplate.menCategory.jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(masterTemplate.menCategory) }  //Issue with Chrome
		
		then:"verify translations of Men Category"		
		assert masterTemplate.subCategory(0).text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert masterTemplate.subCategory(1).text() == expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase()
		
		when: "MouseHover Women Category"
		masterTemplate.womenCategory.jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(masterTemplate.womenCategory) }  //Issue with Chrome
		
		then: "verify translations of Women Category"
		assert masterTemplate.subCategory(2).text() == expectedValue("categorylandingpage.categories").toUpperCase()
		assert masterTemplate.subCategory(3).text() == expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase()
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
