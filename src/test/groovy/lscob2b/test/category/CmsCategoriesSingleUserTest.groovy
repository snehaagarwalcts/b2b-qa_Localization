package lscob2b.test.category

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestHelper
import spock.lang.Shared

class CmsCategoriesSingleUserTest extends GebReportingSpec {

	@Shared
	def currentOpenMenu
	
	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def setup(){
		to LoginPage
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Login with Dockers User, all Dockers categories should be displayed"(){
		setup:
		login(dockersUser)
		
		when: "At HomePage"
		at HomePage
		
		then: "We should see dockers specific categories"
		
		if (currentOpenMenu != parentCategory) {
			masterTemplate.mouseOverParentCategory(parentCategory)
			currentOpenMenu = parentCategory
		}
		
		masterTemplate.subCategoryLinkExists(subCategory)
		
		where:
		
		[brand, parentCategory, subCategory] << new File("src/test/resources/testinput/DockersCmsCategories.txt").readLines()*.tokenize()
	}
	
	def "Login with Levis User, all Levis categories should be displayed"(){
		setup:
		login(levisUser)
		
		when: "At HomePage"
		at HomePage
		
		then: "We should see dockers specific categories"
		
		if (currentOpenMenu != parentCategory) {
			masterTemplate.mouseOverParentCategory(parentCategory)
			currentOpenMenu = parentCategory
		}
		
		masterTemplate.subCategoryLinkExists(subCategory)
		
		where:
		
		[brand, parentCategory, subCategory] << new File("src/test/resources/testinput/LevisCmsCategories.txt").readLines()*.tokenize()
	}
}
