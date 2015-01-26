package lscob2b.test.category;

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
public class CmsCategoriesTest extends GebReportingSpec {

	@Shared
	def currentOpenMenu

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
		to LoginPage
		login (TestDataCatalog.getAMultibrandUser())
		at HomePage
	}
	
	
	@IgnoreIf({ System.getProperty("geb.browser").contains("safari") })
	def "All cms categories should be displayed in the navigation menu"(){

		when: "Switch to correct brand if not already there"

		masterTemplate.switchBrandIfNecessary(brand)

		then: "we should be at correct brand" //could not find a good way to confirm this

		when: "Open category menu"

		if (currentOpenMenu != parentCategory) {
			masterTemplate.mouseOverParentCategory(parentCategory)
			currentOpenMenu = parentCategory
		}

		then: "Check category exists in menu"

		masterTemplate.subCategoryLinkExists(subCategory)

		where:

		[brand, parentCategory, subCategory] << new File("src/test/resources/testinput/CmsCategories.txt").readLines()*.tokenize()

	}
}
