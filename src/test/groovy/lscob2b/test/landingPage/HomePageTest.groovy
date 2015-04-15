package lscob2b.test.landingPage

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProvider

class HomePageTest extends PropertProvider{
	
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
		verifyTrue(masterTemplate.quickOrderLink.text(), expectedValue("search.advanced").toUpperCase())
		verifyTrue(masterTemplate.searchText, expectedValue("search.placeholder"))
		verifyTrue(newsAndTipsLabel.text(), expectedValue("landingpage.newsandtips").toUpperCase())
		verifyTrue(quickOrderButton.text()- ~/</, expectedValue("search.advanced.meta.description.title").toUpperCase())
		verifyTrue(uploadOrderButton.text()- ~/</, expectedValue("text.order.upload").toUpperCase())
		
		when: "MouseHover Men Category"	
		masterTemplate.menCategory.jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(masterTemplate.menCategory) }  //Issue with Chrome
		
		then:"verify translations of Men Category"		
		verifyTrue(masterTemplate.subCategory(0).text(), expectedValue("categorylandingpage.categories").toUpperCase())
		verifyTrue(masterTemplate.subCategory(1).text(), expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase())
		
		when: "MouseHover Women Category"
		masterTemplate.womenCategory.jquery.mouseover()   //Issue with Firefox 35
		interact { moveToElement(masterTemplate.womenCategory) }  //Issue with Chrome
		
		then: "verify translations of Women Category"
		verifyTrue(masterTemplate.subCategory(2).text(), expectedValue("categorylandingpage.categories").toUpperCase())
		verifyTrue(masterTemplate.subCategory(3).text(), expectedValue("categorylandingpage.seasonalinitiatives").toUpperCase())
		verifyTestFailedOrPassed()
				
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
