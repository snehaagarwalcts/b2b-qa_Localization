package lscob2b.test.category;

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.pages.LoginPage
import lscob2b.pages.category.KeyLookPage
import spock.lang.Ignore


public class KeylookTest extends GebReportingSpec {

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
	}
	
	/**
	 * BB-648 Automated test case: Key Look page
	 */
	@Ignore //TODO Not testable until keylook data is ready!!!
	def "Test Key-Look page"() {
		setup:
			to LoginPage
			login(user)
			
			browser.go(baseUrl + link)
											
		when: "At KeyLook page"
			at KeyLookPage
			
		then: "Check product info data"		//TODO improve with real data?
			keylookHeroTitle.text() != ""
			keylookHeroDescription.text() != ""
		
		and: "Check related product data"
			for(item in keylookItems) {
				item.name.text() != ""
				item.style.text() != ""
				item.color.text() != ""
				item.priceWholesale.text() != ""
				item.priceRetail.text() != ""
			}	
			
		where:
			user | link
			TestDataCatalog.getALevisUser() | "en/keylook/levisKeyLook1" 
	}
		
	
}
