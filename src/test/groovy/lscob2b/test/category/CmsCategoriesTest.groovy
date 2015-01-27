package lscob2b.test.category;

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise


public class CmsCategoriesTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
		to LoginPage
		login (TestDataCatalog.getAMultibrandUser())
		at HomePage
	}
	
	def "All cms categories should be displayed in the navigation menu"(){

		when: "At homepage"
			at HomePage
			
		then: "Switch to correct brand if not already there"	
			masterTemplate.switchBrandIfNecessary(brand)

		when: "At homepage"
			at HomePage
			
		then: "Check Category"
			waitFor { !masterTemplate.getCategoryLink(parentCategory).empty }

		and: "Check SubCategory"	
			waitFor { !masterTemplate.getSubcategoryLink(parentCategory,subCategory).empty }
			
		where:
		[brand, parentCategory, subCategory] << new File("src/test/resources/testinput/CmsCategories.txt").readLines()*.tokenize()
	}
	
	
}
