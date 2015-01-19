package lscob2b.test.category

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Shared
import spock.lang.Stepwise
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productcategory.ProductCategoryPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.TestDataCatalog;
import lscob2b.test.data.User
import lscob2b.test.login.LoginFailureTest
import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import static lscob2b.TestConstants.*

class CmsCategoriesSingleUserTest extends GebReportingSpec {

	@Shared
	def currentOpenMenu
	
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
