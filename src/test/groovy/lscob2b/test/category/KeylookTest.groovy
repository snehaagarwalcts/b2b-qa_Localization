package lscob2b.test.category;

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.category.KeyLookPage;
import lscob2b.pages.category.ProductCategoryPage;
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper


public class KeylookTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def "Check KeyLook page"() {
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
			TestDataCatalog.getALevisUser() | "en/keylook/levisKeyLook1" //FIXME use DataCatalog
	}
		
	
}
